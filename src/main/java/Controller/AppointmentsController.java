package Controller;

import DBAccess.DBAppointments;
import Helper.TimeHelper;
import Model.Appointment;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rbrod.scheduleapp.ScheduleApplication;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    private Appointment highlightedAppointment = null;
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
    @FXML
    private Button reportsBtn;

    public void reportsBtnAction(ActionEvent actionEvent) throws IOException{
        goToReportsForm(actionEvent);
    }

    public void addBtnAction(ActionEvent actionEvent) throws IOException{
        highlightedAppointment = null;

        goToAppointmentModificationForm(actionEvent);
    }

    public void modifyBtnAction(ActionEvent actionEvent) throws IOException{
        Alert noAppointmentSelected = new Alert(Alert.AlertType.ERROR, "No appointment selected!");

        Alert noAppointmentSelectedFrench = new Alert(Alert.AlertType.ERROR, "Aucun rendez-vous sélectionné !");
        noAppointmentSelectedFrench.setHeaderText("Erreur!");
        noAppointmentSelectedFrench.setTitle("Erreur!");

        if(highlightedAppointment != null){
            AppointmentModificationController.passedAppointment = highlightedAppointment;
            goToAppointmentModificationForm(actionEvent);
        }else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                noAppointmentSelected.showAndWait();
            }else{
                noAppointmentSelectedFrench.showAndWait();
            }
        }
    }

    public void deleteBtnAction(ActionEvent actionEvent){
        Alert noAppointmentSelected = new Alert(Alert.AlertType.ERROR, "No appointment selected!");

        Alert noAppointmentSelectedFrench = new Alert(Alert.AlertType.ERROR, "Aucun rendez-vous sélectionné !");
        noAppointmentSelectedFrench.setHeaderText("Erreur!");
        noAppointmentSelectedFrench.setTitle("Erreur!");

        if(highlightedAppointment != null){
            DBAppointments.removeAppointment(highlightedAppointment);
            int id = highlightedAppointment.getId().getValue();
            String type = highlightedAppointment.getType().getValue();
            highlightedAppointment = null;

            Alert appointmentCanceled = new Alert(Alert.AlertType.INFORMATION, "ID: " + id + "\nType: " + type);
            appointmentCanceled.setHeaderText("Appointment Canceled!");
            appointmentCanceled.setTitle("Cancellation!");

            Alert appointmentCanceledFrench = new Alert(Alert.AlertType.INFORMATION, "ID: " + id + "\nTaper: " + type);
            appointmentCanceledFrench.setHeaderText("Rendez-vous annulé !");
            appointmentCanceledFrench.setTitle("Annulation!");

            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                appointmentCanceled.showAndWait();
            }else{
                appointmentCanceledFrench.showAndWait();
            }
        }else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                noAppointmentSelected.showAndWait();
            }else{
                noAppointmentSelectedFrench.showAndWait();
            }
        }

        appointmentsTable.setItems(DBAppointments.getAllAppointments());
    }

    public void customersBtnAction(ActionEvent actionEvent)throws IOException{
        goToCustomersForm(actionEvent);
    }

    public void monthlyRadioAction(ActionEvent actionEvent){
        appointmentsTable.setItems(DBAppointments.getMonthlyAppointments());
        weeklyRadio.setSelected(false);
    }

    public void weeklyRadioAction(ActionEvent actionEvent){
        appointmentsTable.setItems(DBAppointments.getWeeklyAppointments());
        monthlyRadio.setSelected(false);
    }

    public void goToAppointmentModificationForm(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("appointment_modification.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void goToReportsForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("reports.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void goToCustomersForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void setEnglishLabels(){
        headerLabel.setText("Appointments");

        monthlyRadio.setText("Monthly View");
        weeklyRadio.setText("Weekly View");

        idCol.setText("ID");
        titleCol.setText("Title");
        descriptionCol.setText("Description");
        locationCol.setText("Location");
        contactCol.setText("Contact");
        typeCol.setText("Type");
        startCol.setText("Start");
        endCol.setText("End");
        customerIdCol.setText("Cust ID");
        userIdCol.setText("User ID");

        addBtn.setText("Add");
        modifyBtn.setText("Modify");
        deleteBtn.setText("Delete");
        customersBtn.setText("Customers");
    }

    public void setFrenchLabels(){
        headerLabel.setText("Rendez-vous");

        monthlyRadio.setText("Vue mensuelle");
        weeklyRadio.setText("Vue hebdomadaire");

        idCol.setText("ID");
        titleCol.setText("Titre");
        descriptionCol.setText("Description");
        locationCol.setText("Emplacement");
        contactCol.setText("Contact");
        typeCol.setText("Taper");
        startCol.setText("Commencer");
        endCol.setText("Fin");
        customerIdCol.setText("Cliente ID");
        userIdCol.setText("Util ID");

        addBtn.setText("Ajouter");
        modifyBtn.setText("Modifier");
        deleteBtn.setText("Supprimer");
        customersBtn.setText("Clients");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
            setEnglishLabels();
        }else{
            setFrenchLabels();
        }

        monthlyRadio.setSelected(true);

        idCol.setCellValueFactory(data -> data.getValue().getId());
        titleCol.setCellValueFactory(data -> data.getValue().getTitle());
        descriptionCol.setCellValueFactory(data -> data.getValue().getDescription());
        locationCol.setCellValueFactory(data -> data.getValue().getLocation());
        contactCol.setCellValueFactory(data -> data.getValue().getContact().getName());
        typeCol.setCellValueFactory(data -> data.getValue().getType());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a MM/dd");

        startCol.setCellValueFactory(data -> {
            ZonedDateTime startUTC = data.getValue().getStart();
            ZonedDateTime localStart = TimeHelper.convertUTCToLocal(startUTC);
            return new SimpleStringProperty(localStart.format(formatter));
        });

        endCol.setCellValueFactory(data -> {
            ZonedDateTime endUTC = data.getValue().getEnd();
            ZonedDateTime localEnd = TimeHelper.convertUTCToLocal(endUTC);
            return new SimpleStringProperty(localEnd.format(formatter));
        });

        customerIdCol.setCellValueFactory(data -> data.getValue().getCustomerId());
        userIdCol.setCellValueFactory(data -> data.getValue().getUserId());

        appointmentsTable.setItems(DBAppointments.getMonthlyAppointments());

        appointmentsTable.setOnMouseClicked(mouseEvent ->{
            try{

                Node node = ((Node)mouseEvent.getTarget()).getParent();
                TableRow row;

                if(node instanceof TableRow){
                    row = (TableRow)node;
                }else{
                    row = (TableRow)node.getParent();
                }

                if(row.getItem() != null){
                    highlightedAppointment = (Appointment)row.getItem();
                }
            }catch(Exception ignored){}
        });
    }
}
