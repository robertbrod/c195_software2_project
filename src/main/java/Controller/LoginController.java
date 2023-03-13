package Controller;

import DBAccess.DBUsers;
import Helper.TimeHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rbrod.scheduleapp.ScheduleApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * LoginController is the controller class for the <code>login.fxml</code> scene.
 *
 * @author Robert Brod
 */
public class LoginController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Label zoneLabel;

    /**
     * Validates username and password fields, logs login attempts in <code>login_activity.txt</code>, and calls method that
     * sends user to <code>customer_data.fxml</code> scene.
     *
     * @param actionEvent login button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void loginBtnAction(ActionEvent actionEvent) throws IOException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("z yyyy/MM/dd hh/mm a");

        Alert incorrectPassword = new Alert(AlertType.ERROR, "Incorrect password entered!");
        Alert noUserFound = new Alert(AlertType.ERROR, "No user found!");

        Alert incorrectPasswordFrench = new Alert(AlertType.ERROR, "Mot de passe saisi incorrect !");
        incorrectPasswordFrench.setHeaderText("Erreur!");
        incorrectPasswordFrench.setTitle("Erreur!");

        Alert noUserFoundFrench = new Alert(AlertType.ERROR, "Aucun utilisateur trouvé !");
        noUserFoundFrench.setHeaderText("Erreur!");
        noUserFoundFrench.setTitle("Erreur!");

        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!DBUsers.checkForUser(username)){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                noUserFound.showAndWait();
            }else{
                noUserFoundFrench.showAndWait();
            }

            try(FileWriter writer = new FileWriter("src/login_activity.txt", true)){
                writer.write("Failed Login - Reason: Incorrect username - Time: " + TimeHelper.getLocalToUTCConversion().format(formatter) + "\n");
            }catch(IOException e){
                e.printStackTrace();
            }

            return;
        }

        if(password.equals(DBUsers.getPassword(username))){
            try(FileWriter writer = new FileWriter("src/login_activity.txt", true)){
                writer.write("Successful Login - User: " + username + " - Time: " + TimeHelper.getLocalToUTCConversion().format(formatter) + "\n");
            }catch(IOException e){
                e.printStackTrace();
            }

            goToCustomerForm(actionEvent);
        }else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                incorrectPassword.showAndWait();
            }else{
                incorrectPasswordFrench.showAndWait();
            }

            try(FileWriter writer = new FileWriter("src/login_activity.txt", true)){
                writer.write("Failed Login - Reason: Incorrect password - Time: " + TimeHelper.getLocalToUTCConversion().format(formatter) + "\n");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        ScheduleApplication.user = DBUsers.getUser(username);
    }

    /**
     * Sends user to <code>customer_data.fxml</code> scene.
     *
     * @param actionEvent login button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToCustomerForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets labels in scene to English
     */
    public void setEnglishLabels(){
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        loginBtn.setText("Login");
    }

    /**
     * Sets labels in scene to French
     */
    public void setFrenchLabels(){
        usernameField.setPromptText("Nom d'utilisateur");
        passwordField.setPromptText("Mot de passe");

        loginBtn.setText("Connexion");
    }

    /**
     * Sets label for Zone ID and sets appropriate label language.
     *
     * @param url location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneLabel.setText("Zone ID: " + TimeHelper.getLocalZoneId());

        if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
            setEnglishLabels();
        }else{
            setFrenchLabels();
        }
    }
}
