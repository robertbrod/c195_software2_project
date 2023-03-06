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

public class CustomerDataController implements Initializable {

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

    public void addBtnAction(ActionEvent actionEvent) throws IOException{
        highlightedCustomer = null;

        goToCustomerModificationForm(actionEvent);
    }

    public void deleteBtnAction(ActionEvent actionEvent){
        Alert noCustomerSelected = new Alert(AlertType.ERROR, "No customer selected!");
        Alert customerHasAppointments = new Alert(AlertType.ERROR, "Customer appointments must be cancelled before removing customer!");

        Alert noCustomerSelectedFrench = new Alert(AlertType.ERROR, "Aucun client sélectionné !");
        noCustomerSelectedFrench.setHeaderText("Erreur!");
        noCustomerSelectedFrench.setTitle("Erreur!");

        Alert customerHasAppointmentsFrench = new Alert(AlertType.ERROR, "Les rendez-vous clients doivent être annulés avant de supprimer le client !");
        customerHasAppointmentsFrench.setHeaderText("Erreur!");
        customerHasAppointmentsFrench.setTitle("Erreur!");

        if(highlightedCustomer != null){
            ObservableList<Appointment> customerAppointments = DBAppointments.getAllCustomerAppointments(highlightedCustomer.getId().getValue());
            if(customerAppointments.size() != 0){
                if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                    customerHasAppointments.showAndWait();
                }else{
                    customerHasAppointmentsFrench.showAndWait();
                }
            }else{
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
            }
        }else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                noCustomerSelected.showAndWait();
            }else{
                noCustomerSelectedFrench.showAndWait();
            }
        }

        customerDataTable.setItems(DBCustomers.getAllCustomers());
    }

    public void appointmentsBtnAction(ActionEvent actionEvent) throws IOException{
        goToAppointmentsForm(actionEvent);
    }

    public void goToAppointmentsForm(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("appointments.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void goToCustomerModificationForm(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_modification.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

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
            LocalTime apptStartTimeLocal = TimeHelper.convertUTCToLocal(apptStart).toLocalTime();
            LocalDate apptDate = appointment.getStart().toLocalDate();
            int id = appointment.getId().getValue();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

            Alert upcomingAppointment = new Alert(AlertType.INFORMATION, "ID: " + id + "\nDate: " + apptDate.format(dateFormatter) + "\nTime: " + apptStartTimeLocal.format(timeFormatter));
            upcomingAppointment.setTitle("Appointments!");
            upcomingAppointment.setHeaderText("Upcoming appointment!");

            Alert upcomingAppointmentFrench = new Alert(AlertType.INFORMATION, "ID: " + id + "\nDate: " + apptDate.format(dateFormatter) + "\nTemps: " + apptStartTimeLocal.format(timeFormatter));
            upcomingAppointmentFrench.setTitle("Rendez-vous !");
            upcomingAppointmentFrench.setHeaderText("Rendez-vous à venir !");

            if(localDate.equals(apptDate)){
                if(localTime.until(apptStartTimeLocal, ChronoUnit.MINUTES) <= 15){
                    if (ScheduleApplication.language == ScheduleApplication.Language.ENGLISH) {
                        upcomingAppointment.showAndWait();
                        return;
                    }else{
                        upcomingAppointmentFrench.showAndWait();
                        return;
                    }
                }
            }
        }

        if (ScheduleApplication.language == ScheduleApplication.Language.ENGLISH) {
            noAppointments.showAndWait();
        }else{
            noAppointmentsFrench.showAndWait();
        }
    }

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
