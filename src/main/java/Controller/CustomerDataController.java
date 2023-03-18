package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Helper.TimeHelper;
import Model.Appointment;
import Model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import rbrod.scheduleapp.ScheduleApplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;


/**
 * CustomerDataController is the controller class for the <code>customer_data.fxml</code> scene.
 *
 * @author Robert Brod
 */
public class CustomerDataController implements Initializable {

    /**
     * Customer object used to maintain which Customer object was clicked on last in <code>customerDataTable</code> TableView
     */
    private Customer highlightedCustomer = null;

    @FXML
    private TableColumn<Customer, Number> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> postalCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private TableView<Customer> customerDataTable;
    @FXML
    private Button addBtn;
    @FXML
    private Button modifyBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button appointmentsBtn;
    @FXML
    private Label headerLabel;

    /**
     * Validates that a customer has been selected/highlighted and then calls method to send user to <code>customer-modification.fxml</code>
     * scene.
     *
     * @param actionEvent modify button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void modifyBtnAction(ActionEvent actionEvent) throws IOException {
        Alert noCustomerSelected = new Alert(AlertType.ERROR, "No customer selected!");

        Alert noCustomerSelectedFrench = new Alert(AlertType.ERROR, "Aucun client sélectionné !");
        noCustomerSelectedFrench.setHeaderText("Erreur!");
        noCustomerSelectedFrench.setTitle("Erreur!");

        if(highlightedCustomer != null){
            CustomerModificationController.passedCustomer = highlightedCustomer;
            goToCustomerModificationForm(actionEvent);
        }else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                noCustomerSelected.showAndWait();
            }else{
                noCustomerSelectedFrench.showAndWait();
            }
        }
    }

    /**
     * Calls method that sends user to <code>customer-modification.fxml</code> scene and ensures that no data is passed as this
     * would be a new customer.
     *
     * @param actionEvent add button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void addBtnAction(ActionEvent actionEvent) throws IOException{
        highlightedCustomer = null;

        goToCustomerModificationForm(actionEvent);
    }

    /**
     * Ensures that a customer has been selected/highlighted and removes that customer, as well as that customer's appointments, from database.
     * TableView is then refreshed to show these changes.
     *
     * @param actionEvent delete button fire, used to reference primary stage
     */
    public void deleteBtnAction(ActionEvent actionEvent){
        Alert noCustomerSelected = new Alert(AlertType.ERROR, "No customer selected!");

        Alert noCustomerSelectedFrench = new Alert(AlertType.ERROR, "Aucun client sélectionné !");
        noCustomerSelectedFrench.setHeaderText("Erreur!");
        noCustomerSelectedFrench.setTitle("Erreur!");

        if(highlightedCustomer != null){
            //Remover customer appointments
            ObservableList<Appointment> customerAppointments = DBAppointments.getAllCustomerAppointments(highlightedCustomer.getId().getValue());
            for(Appointment customerAppointment : customerAppointments){
                DBAppointments.removeAppointment(customerAppointment);
            }

            DBCustomers.removeCustomer(highlightedCustomer);
            int id = highlightedCustomer.getId().getValue();
            String name = highlightedCustomer.getName().getValue();
            highlightedCustomer = null;

            Alert customerDeleted = new Alert(AlertType.INFORMATION, "ID: " + id + "\nName: " + name);
            customerDeleted.setHeaderText("Customer Removed!");
            customerDeleted.setTitle("Removed!");

            Alert customerDeletedFrench = new Alert(AlertType.INFORMATION, "ID: " + id + "\nNom: " + name);
            customerDeletedFrench.setHeaderText("Client supprimé !");
            customerDeletedFrench.setTitle("Supprimé!");

            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                customerDeleted.showAndWait();
            }else{
                customerDeletedFrench.showAndWait();
            }
        } else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                noCustomerSelected.showAndWait();
            }else{
                noCustomerSelectedFrench.showAndWait();
            }
        }

        customerDataTable.setItems(DBCustomers.getAllCustomers());
    }

    /**
     * Calls method that sends user to <code>appointments.fxml</code> scene. Methods separated for consistency.
     *
     * @param actionEvent appointments button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void appointmentsBtnAction(ActionEvent actionEvent) throws IOException{
        goToAppointmentsForm(actionEvent);
    }

    /**
     * Sends user to <code>appointments.fxml</code> scene.
     *
     * @param actionEvent appointments button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToAppointmentsForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("appointments.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sends user to <code>customer_modification.fxml</code> scene.
     *
     * @param actionEvent add/modify button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToCustomerModificationForm(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_modification.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets all scene labels to English.
     */
    public void setEnglishLabels(){
        headerLabel.setText("Customer Data");

        nameCol.setText("Name");
        addressCol.setText("Address");
        phoneCol.setText("Phone");

        addBtn.setText("Add");
        modifyBtn.setText("Modify");
        deleteBtn.setText("Delete");
        appointmentsBtn.setText("Appointments");
    }

    /**
     * Sets all scene labels to French.
     */
    public void setFrenchLabels(){
        headerLabel.setText("Données Client");

        nameCol.setText("Nom");
        addressCol.setText("Adresse");
        phoneCol.setText("Téléphone");

        addBtn.setText("Ajouter");
        modifyBtn.setText("Modifier");
        deleteBtn.setText("Supprimer");
        appointmentsBtn.setText("Rendez-vous");
    }

    /**
     * Checks to see if logged in used has any appointments within +15 minutes of current local time. An alert is sent either way.
     */
    public void checkForAppointments(){
        Alert noAppointments = new Alert(AlertType.INFORMATION);
        noAppointments.setTitle("Appointments!");
        noAppointments.setHeaderText("No upcoming appointments!");

        Alert noAppointmentsFrench = new Alert(AlertType.INFORMATION);
        noAppointmentsFrench.setTitle("Rendez-vous !");
        noAppointmentsFrench.setHeaderText("Aucun rendez-vous à venir !");

        ZonedDateTime now = ZonedDateTime.now(TimeHelper.getLocalZoneId());
        LocalTime localTime = now.toLocalTime();
        LocalDate localDate = now.toLocalDate();

        for(Appointment appointment : DBAppointments.getAllAppointments()){
            ZonedDateTime apptStart = appointment.getStart();
            ZonedDateTime localApptStart = TimeHelper.convertESTToLocal(apptStart);
            LocalTime apptStartTime = localApptStart.toLocalTime();
            LocalDate apptDate = localApptStart.toLocalDate();
            int id = appointment.getId().getValue();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

            Alert upcomingAppointment = new Alert(AlertType.INFORMATION, "ID: " + id + "\nDate: " + apptDate.format(dateFormatter) + "\nTime: " + apptStartTime.format(timeFormatter));
            upcomingAppointment.setTitle("Appointments!");
            upcomingAppointment.setHeaderText("Upcoming appointment!");

            Alert upcomingAppointmentFrench = new Alert(AlertType.INFORMATION, "ID: " + id + "\nDate: " + apptDate.format(dateFormatter) + "\nTemps: " + apptStartTime.format(timeFormatter));
            upcomingAppointmentFrench.setTitle("Rendez-vous !");
            upcomingAppointmentFrench.setHeaderText("Rendez-vous à venir !");

            if(localDate.equals(apptDate)){
                if(localTime.until(apptStartTime, ChronoUnit.MINUTES) <= 15 && localTime.until(apptStartTime, ChronoUnit.MINUTES) >= 0){
                    System.out.println(localTime.until(apptStartTime, ChronoUnit.MINUTES));
                    if (ScheduleApplication.language == ScheduleApplication.Language.ENGLISH) {
                        upcomingAppointment.showAndWait();
                    }else{
                        upcomingAppointmentFrench.showAndWait();
                    }
                    return;
                }
            }
        }

        if (ScheduleApplication.language == ScheduleApplication.Language.ENGLISH) {
            noAppointments.showAndWait();
        }else{
            noAppointmentsFrench.showAndWait();
        }
    }

    /**
     * Sets appropriate label language, populates <code>customerDataTable</code> TableView, calls method to check for upcoming appointments.
     * Handles user input via mouse click to select rows in <code>customerDataTable</code> tableView and populates <code>highlightedCustomer</code>.
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

        idCol.setCellValueFactory(data -> data.getValue().getId());
        nameCol.setCellValueFactory(data -> data.getValue().getName());
        addressCol.setCellValueFactory(data -> data.getValue().getFormattedAddress());
        postalCol.setCellValueFactory(data -> data.getValue().getPostalCode());
        phoneCol.setCellValueFactory(data -> data.getValue().getPhone());

        customerDataTable.setItems(DBCustomers.getAllCustomers());

        checkForAppointments();

        customerDataTable.setOnMouseClicked(mouseEvent ->{
            try{
                Node node = ((Node)mouseEvent.getTarget()).getParent();
                TableRow row;

                if(node instanceof TableRow){
                    row = (TableRow)node;
                }else{
                    row = (TableRow)node.getParent();
                }

                if(row.getItem() != null){
                    highlightedCustomer = (Customer)row.getItem();
                }
            }catch(Exception ignored){}
        });
    }
}
