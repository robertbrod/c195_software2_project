package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Helper.TimeHelper;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
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
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentModificationController implements Initializable {
    public static Appointment passedAppointment = null;
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
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    public void saveBtnAction(ActionEvent actionEvent) throws IOException{
        if(validateData()){
            if(passedAppointment != null){
                modifyPassedAppointment();
            } else{
                createNewAppointment();
            }

            goToAppointmentsForm(actionEvent);
        }
    }

    public void cancelBtnAction(ActionEvent actionEvent) throws IOException{
        goToAppointmentsForm(actionEvent);
    }

    public void populateFields(){
        idField.setText(Integer.toString(passedAppointment.getId().getValue()));
        titleField.setText(passedAppointment.getTitle().getValue());
        descriptionField.setText(passedAppointment.getDescription().getValue());
        locationField.setText(passedAppointment.getLocation().getValue());
        typeField.setText(passedAppointment.getType().getValue());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ZonedDateTime start = passedAppointment.getStart();
        ZonedDateTime localStart = start.withZoneSameInstant(TimeHelper.getLocalZoneId());
        String localStartStr = localStart.format(formatter);
        startField.setText(localStartStr);

        ZonedDateTime end = passedAppointment.getEnd();
        ZonedDateTime localEnd = end.withZoneSameInstant(TimeHelper.getLocalZoneId());
        String localEndStr = localEnd.format(formatter);
        endField.setText(localEndStr);

        contactCombo.setValue(passedAppointment.getContact().getName().getValue());
        customerIdCombo.setValue(Integer.toString(passedAppointment.getCustomerId().getValue()));
        userIdCombo.setValue(Integer.toString(passedAppointment.getUserId().getValue()));
    }

    public void modifyPassedAppointment(){
        int id = passedAppointment.getId().getValue();
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String startTimeStr = startField.getText();
        LocalDateTime startLocalTime = LocalDateTime.parse(startTimeStr, formatter);
        ZonedDateTime startZonedTime = ZonedDateTime.of(startLocalTime, TimeHelper.getLocalZoneId());
        ZonedDateTime adjustedStartTime = TimeHelper.convertLocalToUTC(startZonedTime);
        String adjustedStartTimeStr = adjustedStartTime.format(formatter);

        String endTimeStr = endField.getText();
        LocalDateTime endLocalTime = LocalDateTime.parse(endTimeStr, formatter);
        ZonedDateTime zonedEndTime = ZonedDateTime.of(endLocalTime, TimeHelper.getLocalZoneId());
        ZonedDateTime adjustedEndTime = TimeHelper.convertLocalToUTC(zonedEndTime);
        String adjustedEndTimeStr = adjustedEndTime.format(formatter);

        int contactId = passedAppointment.getContact().getId().getValue();

        Customer customer = DBCustomers.getCustomer(Integer.parseInt(customerIdCombo.getValue()));
        int customerId = customer.getId().getValue();

        User user = DBUsers.getUser(Integer.parseInt(userIdCombo.getValue()));
        int userId = user.getId().getValue();

        DBAppointments.updateAppointmentTitle(id, title);
        DBAppointments.updateAppointmentDescription(id, description);
        DBAppointments.updateAppointmentLocation(id, location);
        DBAppointments.updateAppointmentType(id, type);
        DBAppointments.updateAppointmentStart(id, adjustedStartTimeStr);
        DBAppointments.updateAppointmentEnd(id, adjustedEndTimeStr);
        DBAppointments.updateAppointmentContactId(id, contactId);
        DBAppointments.updateAppointmentCustomerId(id, customerId);
        DBAppointments.updateAppointmentUserId(id, userId);
    }

    public void createNewAppointment(){
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String startTimeStr = startField.getText();
        LocalDateTime startLocalTime = LocalDateTime.parse(startTimeStr, formatter);
        ZonedDateTime zonedStartTime = ZonedDateTime.of(startLocalTime, TimeHelper.getLocalZoneId());
        ZonedDateTime adjustedStartTime = zonedStartTime.withZoneSameInstant(ZoneId.of("UTC"));

        String endTimeStr = endField.getText();
        LocalDateTime endLocalTime = LocalDateTime.parse(endTimeStr, formatter);
        ZonedDateTime zonedEndTime = ZonedDateTime.of(endLocalTime, TimeHelper.getLocalZoneId());
        ZonedDateTime adjustedEndTime = zonedEndTime.withZoneSameInstant(ZoneId.of("UTC"));

        Contact contact = DBContacts.getContact(contactCombo.getValue());
        int contactId = contact.getId().getValue();

        Customer customer = DBCustomers.getCustomer(Integer.parseInt(customerIdCombo.getValue()));
        int customerId = customer.getId().getValue();

        User user = DBUsers.getUser(Integer.parseInt(userIdCombo.getValue()));
        int userId = user.getId().getValue();

        Appointment appointment = new Appointment(-1, title, description, location, type, adjustedStartTime, adjustedEndTime, null,
                                             null, null, null, customerId, userId, contactId);
        DBAppointments.addAppointment(appointment);
    }

    public boolean validateData(){
        Alert invalidTitleField = new Alert(AlertType.ERROR, "Invalid title field!");
        Alert invalidDescriptionField = new Alert(AlertType.ERROR, "Invalid description field!");
        Alert invalidLocationField = new Alert(AlertType.ERROR, "Invalid location field!");
        Alert invalidTypeField = new Alert(AlertType.ERROR, "Invalid type field!");
        Alert invalidStartField = new Alert(AlertType.ERROR, "Invalid start field!");
        Alert startOutsideHours = new Alert(AlertType.ERROR, "Start time outside business hours!");
        Alert invalidEndField = new Alert(AlertType.ERROR, "Invalid end field!");
        Alert endOutsideHours = new Alert(AlertType.ERROR, "End time outside business hours!");
        Alert appointmentOverlap = new Alert(AlertType.ERROR, "This appointment overlaps another!");
        Alert invalidContactSelection = new Alert(AlertType.ERROR, "Invalid contact selection!");
        Alert invalidCustomerIdSelection = new Alert(AlertType.ERROR, "Invalid customer ID selection!");
        Alert invalidUserIdSelection = new Alert(AlertType.ERROR, "Invalid user ID selection!");

        Alert invalidTitleFieldFrench = new Alert(AlertType.ERROR, "Champ de titre invalide !");
        invalidTitleFieldFrench.setHeaderText("Erreur!");
        invalidTitleFieldFrench.setTitle("Erreur!");

        Alert invalidDescriptionFieldFrench = new Alert(AlertType.ERROR, "Champ de description invalide !");
        invalidDescriptionFieldFrench.setHeaderText("Erreur!");
        invalidDescriptionFieldFrench.setTitle("Erreur!");

        Alert invalidLocationFieldFrench = new Alert(AlertType.ERROR, "Champ de localisation invalide !");
        invalidLocationFieldFrench.setHeaderText("Erreur!");
        invalidLocationFieldFrench.setTitle("Erreur!");

        Alert invalidTypeFieldFrench = new Alert(AlertType.ERROR, "Champ de type invalide !");
        invalidTypeFieldFrench.setHeaderText("Erreur!");
        invalidTypeFieldFrench.setTitle("Erreur!");

        Alert invalidStartFieldFrench = new Alert(AlertType.ERROR, "Champ de départ invalide !");
        invalidStartFieldFrench.setHeaderText("Erreur!");
        invalidStartFieldFrench.setTitle("Erreur!");

        Alert startOutsideHoursFrench = new Alert(AlertType.ERROR, "Heure de début en dehors des heures d'ouverture!");
        invalidStartFieldFrench.setHeaderText("Erreur!");
        invalidStartFieldFrench.setTitle("Erreur!");

        Alert invalidEndFieldFrench = new Alert(AlertType.ERROR, "Champ de fin invalide !");
        invalidEndFieldFrench.setHeaderText("Erreur!");
        invalidEndFieldFrench.setTitle("Erreur!");

        Alert endOutsideHoursFrench = new Alert(AlertType.ERROR, "Heure de fin en dehors des heures d'ouverture !");
        invalidStartFieldFrench.setHeaderText("Erreur!");
        invalidStartFieldFrench.setTitle("Erreur!");

        Alert appointmentOverlapFrench = new Alert(AlertType.ERROR, "Ce rendez-vous en chevauche un autre !");
        invalidTitleFieldFrench.setHeaderText("Erreur!");
        invalidTitleFieldFrench.setTitle("Erreur!");

        Alert invalidContactSelectionFrench = new Alert(AlertType.ERROR, "Sélection de contact invalide !");
        invalidContactSelectionFrench.setHeaderText("Erreur!");
        invalidContactSelectionFrench.setTitle("Erreur!");

        Alert invalidCustomerIdSelectionFrench = new Alert(AlertType.ERROR, "Sélection d'ID client invalide !");
        invalidCustomerIdSelectionFrench.setHeaderText("Erreur!");
        invalidCustomerIdSelectionFrench.setTitle("Erreur!");

        Alert invalidUserIdSelectionFrench = new Alert(AlertType.ERROR, "Sélection d'ID utilisateur invalide !");
        invalidUserIdSelectionFrench.setHeaderText("Erreur!");
        invalidUserIdSelectionFrench.setTitle("Erreur!");

        String title = titleField.getText();
        if(title.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidTitleField.showAndWait();
            }else{
                invalidTitleFieldFrench.showAndWait();
            }
            return false;
        }

        String description = descriptionField.getText();
        if(description.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidDescriptionField.showAndWait();
            }else{
                invalidDescriptionFieldFrench.showAndWait();
            }
            return false;
        }

        String location = locationField.getText();
        if(location.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidLocationField.showAndWait();
            }else{
                invalidLocationFieldFrench.showAndWait();
            }
            return false;
        }

        String type = typeField.getText();
        if(type.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidTypeField.showAndWait();
            }else{
                invalidTypeFieldFrench.showAndWait();
            }
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String start = startField.getText();
        if(start.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidStartField.showAndWait();
            }else{
                invalidStartFieldFrench.showAndWait();
            }
            return false;
        }
        try{
            LocalDateTime localStart = LocalDateTime.parse(start, formatter);
            ZonedDateTime zonedStart = ZonedDateTime.of(localStart, TimeHelper.getLocalZoneId());
            ZonedDateTime estStart = TimeHelper.convertLocalToEST(zonedStart);
            LocalDate startDate = zonedStart.toLocalDate();
            LocalTime estStartTime = estStart.toLocalTime();
            LocalTime startOfDay = LocalTime.of(8, 0);
            LocalTime endOfDay = LocalTime.of(22, 0);

            if(estStartTime.isBefore(startOfDay) || estStartTime.isAfter(endOfDay)){
                if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                    startOutsideHours.showAndWait();
                    return false;
                }else{
                    startOutsideHoursFrench.showAndWait();
                    return false;
                }
            }

            for(Appointment appointment : DBAppointments.getAllAppointments()){
                LocalDate apptDate = appointment.getStart().toLocalDate();

                ZonedDateTime apptStart = appointment.getStart();
                ZonedDateTime apptStartEST = TimeHelper.convertLocalToEST(apptStart);
                LocalTime apptStartTimeEst = apptStartEST.toLocalTime();

                ZonedDateTime apptEnd = appointment.getEnd();
                ZonedDateTime apptEndEST = TimeHelper.convertLocalToEST(apptEnd);
                LocalTime apptEndTimeEst = apptEndEST.toLocalTime();

                if(startDate.equals(apptDate)){
                    if((estStartTime.isAfter(apptStartTimeEst) && estStartTime.isBefore(apptEndTimeEst))){
                        if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                            appointmentOverlap.showAndWait();
                            return false;
                        }else{
                            appointmentOverlapFrench.showAndWait();
                            return false;
                        }
                    }
                }
            }
        }catch(Exception e){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidStartField.showAndWait();
            }else{
                invalidStartFieldFrench.showAndWait();
            }
            return false;
        }

        String end = endField.getText();
        if(end.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidEndField.showAndWait();
            }else{
                invalidEndFieldFrench.showAndWait();
            }
            return false;
        }
        try{
            LocalDateTime localEnd = LocalDateTime.parse(end, formatter);
            ZonedDateTime zonedEnd = ZonedDateTime.of(localEnd, TimeHelper.getLocalZoneId());
            ZonedDateTime estEnd = TimeHelper.convertLocalToEST(zonedEnd);
            LocalDate endDate = zonedEnd.toLocalDate();
            LocalTime estEndTime = estEnd.toLocalTime();
            LocalTime startOfDay = LocalTime.of(8, 0);
            LocalTime endOfDay = LocalTime.of(22, 0);

            if(estEndTime.isBefore(startOfDay) || estEndTime.isAfter(endOfDay)){
                if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                    endOutsideHours.showAndWait();
                    return false;
                }else{
                    endOutsideHoursFrench.showAndWait();
                    return false;
                }
            }

            for(Appointment appointment : DBAppointments.getAllAppointments()){
                LocalDate apptDate = appointment.getEnd().toLocalDate();

                ZonedDateTime apptStart = appointment.getStart();
                ZonedDateTime apptStartEST = TimeHelper.convertLocalToEST(apptStart);
                LocalTime apptStartTimeEst = apptStartEST.toLocalTime();

                ZonedDateTime apptEnd = appointment.getEnd();
                ZonedDateTime apptEndEST = TimeHelper.convertLocalToEST(apptEnd);
                LocalTime apptEndTimeEst = apptEndEST.toLocalTime();

                if(endDate.equals(apptDate)){
                    if((estEndTime.isAfter(apptStartTimeEst) && estEndTime.isBefore(apptEndTimeEst))){
                        if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                            appointmentOverlap.showAndWait();
                            return false;
                        }else{
                            appointmentOverlapFrench.showAndWait();
                            return false;
                        }
                    }
                }
            }
        }catch(Exception e){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidEndField.showAndWait();
            }else{
                invalidEndFieldFrench.showAndWait();
            }
            return false;
        }

        String contact = contactCombo.getValue();
        if(contact.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidContactSelection.showAndWait();
            }else{
                invalidContactSelectionFrench.showAndWait();
            }
            return false;
        }

        String customerId = customerIdCombo.getValue();
        if(customerId.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidCustomerIdSelection.showAndWait();
            }else{
                invalidCustomerIdSelectionFrench.showAndWait();
            }
            return false;
        }

        String userId = userIdCombo.getValue();
        if(userId.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidUserIdSelection.showAndWait();
            }else{
                invalidUserIdSelectionFrench.showAndWait();
            }
            return false;
        }

        return true;
    }

    public void goToAppointmentsForm(ActionEvent actionEvent) throws IOException{
        passedAppointment = null;

        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("appointments.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void setEnglishLabels(){
        headerLabel.setText("Appointment Information");

        idLabel.setText("ID");
        titleLabel.setText("Title");
        descriptionLabel.setText("Description");
        locationLabel.setText("Location");
        typeLabel.setText("Type");
        startLabel.setText("Start");
        endLabel.setText("End");
        contactLabel.setText("Contact");
        customerIdLabel.setText("Customer ID");
        userIdLabel.setText("User ID");

        saveBtn.setText("Save");
        cancelBtn.setText("Cancel");
    }

    public void setFrenchLabels(){
        headerLabel.setText("Informations sur le rendez-vous");

        idLabel.setText("ID");
        titleLabel.setText("Titre");
        descriptionLabel.setText("Description");
        locationLabel.setText("Emplacement");
        typeLabel.setText("Taper");
        startLabel.setText("Commencer");
        endLabel.setText("Fin");
        contactLabel.setText("Contact");
        customerIdLabel.setText("Client ID");
        userIdLabel.setText("Utilisateur ID");

        saveBtn.setText("Sauvegarder");
        cancelBtn.setText("Annuler");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
            setEnglishLabels();
        }else{
            setFrenchLabels();
        }

        contactCombo.getItems().clear();
        for(Contact contact : DBContacts.getAllContacts()){
            contactCombo.getItems().add(contact.getName().getValue());
        }

        customerIdCombo.getItems().clear();
        for(Customer customer : DBCustomers.getAllCustomers()){
            customerIdCombo.getItems().add(Integer.toString(customer.getId().getValue()));
        }

        userIdCombo.getItems().clear();
        for(User user : DBUsers.getAllUsers()){
            userIdCombo.getItems().add(Integer.toString(user.getId().getValue()));
        }

        if(passedAppointment != null){
            populateFields();
        }
    }
}
