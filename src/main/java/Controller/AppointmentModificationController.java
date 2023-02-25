package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentModificationController implements Initializable {
    @FXML
    private Label headerLabel;
    @FXML
    private Label idLabel;
    @FXML
    private TextField idField;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField titleField;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextField descriptionField;
    @FXML
    private Label locationLabel;
    @FXML
    private TextField locationField;
    @FXML
    private Label typeLabel;
    @FXML
    private TextField typeField;
    @FXML
    private Label startLabel;
    @FXML
    private TextField startField;
    @FXML
    private Label endLabel;
    @FXML
    private TextField endField;
    @FXML
    private Label contactLabel;
    @FXML
    private ComboBox<String> contactCombo;
    @FXML
    private Label customerIdLabel;
    @FXML
    private ComboBox<String> customerIdCombo;
    @FXML
    private Label userIdLabel;
    @FXML
    private ComboBox<String> userIdCombo;

    public void saveBtnAction(ActionEvent actionEvent){}

    public void cancelBtnAction(ActionEvent actionEvent){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
