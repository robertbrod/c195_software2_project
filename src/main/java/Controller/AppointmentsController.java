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

/**
 * AppointmentsController is the controller class for the <code>appointments.fxml</code> scene.
 *
 * @author Robert Brod
 */
public class AppointmentsController implements Initializable {

    /**
     * Appointment object used to maintain which Appointment object was clicked on last in <code>appointmentsTable</code> TableView
     */
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

    /**
     * Calls method that sends user to <code>reports.fxml</code> scene. Methods separated for consistency and clarity.
     *
     * @param actionEvent reports button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void reportsBtnAction(ActionEvent actionEvent) throws IOException{
        goToReportsForm(actionEvent);
    }

    /**
     * Calls method that sends user to <code>appointment_modification.fxml</code> scene and ensures that no data is passed as this
     * would be a new appointment
     *
     * @param actionEvent add button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void addBtnAction(ActionEvent actionEvent) throws IOException{
        highlightedAppointment = null;

        goToAppointmentModificationForm(actionEvent);
    }

    /**
     * Validates that an appointment has been selected/highlighted and then calls the method to send user to <code>appointment_modification.fxml</code>
     * scene.
     *
     * @param actionEvent modify button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
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

    /**
     * Validates that an appointment has been selected/highlighted and removes that appointment from database. TableView is then refreshed
     * to show these changes.
     *
     * @param actionEvent delete button fire, used to reference primary stage
     */
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

    /**
     * Calls method that sends user to <code>customer_data.fxml</code> scene. Methods separated for consistency and clarity.
     *
     * @param actionEvent customers button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void customersBtnAction(ActionEvent actionEvent)throws IOException{
        goToCustomersForm(actionEvent);
    }

    /**
     * Changes appointments ot be shown to only those that occur within +30 days of current local time. Ensures that <code>weeklyRadio</code>
     * is unselected so both radio buttons are not selected at once.
     *
     * @param actionEvent monthly radio selection
     */
    public void monthlyRadioAction(ActionEvent actionEvent){
        appointmentsTable.setItems(DBAppointments.getMonthlyAppointments());
        weeklyRadio.setSelected(false);
    }

    /**
     * Changes appointments ot be shown to only those that occur within +7 days of current local time. Ensures that <code>monthlyRadio</code>
     * is unselected so both radio buttons are not selected at once.
     *
     * @param actionEvent weekly radio selection
     */
    public void weeklyRadioAction(ActionEvent actionEvent){
        appointmentsTable.setItems(DBAppointments.getWeeklyAppointments());
        monthlyRadio.setSelected(false);
    }

    /**
     * Sends user to <code>appointment_modification.fxml</code> scene.
     *
     * @param actionEvent add/modify button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToAppointmentModificationForm(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("appointment_modification.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sends user to <code>reports.fxml</code> scene.
     *
     * @param actionEvent reports button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToReportsForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("reports.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sends user to <code>customer_data.fxml</code> scene.
     *
     * @param actionEvent customers button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToCustomersForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets all scene labels to English.
     */
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

    /**
     * Sets all scene labels to French.
     */
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

    /**
     * Sets appropriate label language, populates <code>appointmentsTable</code> TableView. Handles user input via mouse click to
     * select rows in TableView and populates <code>highlightedAppointment</code>.
     *
     * Lambda expressions are used in two ways here. One is for setting cell value factories in our TableView which provides a more concise and readable
     * code compared to creating a separate class for the callback function. THe other way is to handle mouse events for our TableView by defining a callback function
     * for the event handler without the need to create a separate class.
     *
     * @param url location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
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
