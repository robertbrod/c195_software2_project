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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Label zoneLabel;

    public void loginBtnAction(ActionEvent actionEvent) throws IOException{
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

            return;
        }

        if(password.equals(DBUsers.getPassword(username))){
            goToCustomerForm(actionEvent);
        }else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                incorrectPassword.showAndWait();
            }else{
                incorrectPasswordFrench.showAndWait();
            }
        }

        ScheduleApplication.user = DBUsers.getUser(username);
    }

    public void goToCustomerForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void setEnglishLabels(){
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        loginBtn.setText("Login");
    }

    public void setFrenchLabels(){
        usernameField.setPromptText("Nom d'utilisateur");
        passwordField.setPromptText("Mot de passe");

        loginBtn.setText("Connexion");
    }

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
