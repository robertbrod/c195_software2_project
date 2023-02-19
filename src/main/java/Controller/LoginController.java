package Controller;

import DBAccess.DBUsers;
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

    public void loginBtnAction(ActionEvent actionEvent) throws IOException{
        Alert incorrectPassword = new Alert(AlertType.ERROR, "Incorrect password entered!");
        Alert noUserFound = new Alert(AlertType.ERROR, "No user found!");

        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!DBUsers.checkForUser(username)){
            noUserFound.showAndWait();
            return;
        }

        if(password.equals(DBUsers.getPassword(username))){
            goToCustomerForm(actionEvent);
        }else{
            incorrectPassword.showAndWait();
        }
    }

    public void goToCustomerForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
