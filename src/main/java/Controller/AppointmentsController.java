package Controller;

import DBAccess.DBAppointments;
import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    @FXML
    private Label headerLabel;
    @FXML
    private RadioButton monthlyRadio;
    @FXML
    private RadioButton weeklyRadio;
    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, Number> idCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, String> startCol;
    @FXML
    private TableColumn<Appointment, String> endCol;
    @FXML
    private TableColumn<Appointment, Number> customerIdCol;
    @FXML
    private TableColumn<Appointment, Number> userIdCol;
    @FXML
    private Button addBtn;
    @FXML
    private Button modifyBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button customersBtn;

    public void addBtnAction(ActionEvent actionEvent){}

    public void modifyBtnAction(ActionEvent actionEvent){}

    public void deleteBtnAction(ActionEvent actionEvent){}

    public void customersBtnAction(ActionEvent actionEvent){}

    public void monthlyRadioAction(ActionEvent actionEvent){
        weeklyRadio.setSelected(false);
    }

    public void weeklyRadioAction(ActionEvent actionEvent){
        monthlyRadio.setSelected(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthlyRadio.setSelected(true);

        idCol.setCellValueFactory(data -> data.getValue().getId());
        titleCol.setCellValueFactory(data -> data.getValue().getTitle());
        descriptionCol.setCellValueFactory(data -> data.getValue().getDescription());
        locationCol.setCellValueFactory(data -> data.getValue().getLocation());
        contactCol.setCellValueFactory(data -> data.getValue().getContact().getName());
        typeCol.setCellValueFactory(data -> data.getValue().getType());
        startCol.setCellValueFactory(data -> data.getValue().getStart());
        endCol.setCellValueFactory(data -> data.getValue().getEnd());
        customerIdCol.setCellValueFactory(data -> data.getValue().getCustomerId());
        userIdCol.setCellValueFactory(data -> data.getValue().getUserId());

        appointmentsTable.setItems(DBAppointments.getAllAppointments());
    }
}
