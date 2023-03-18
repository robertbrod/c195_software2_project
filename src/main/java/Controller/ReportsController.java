package Controller;

import DBAccess.DBAppointments;
import Helper.TimeHelper;
import Model.Appointment;
import Model.ReportTotal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import rbrod.scheduleapp.ScheduleApplication;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * ReportsController is the controller class for the <code>reports.fxml</code> scene.
 *
 * @author Robert Brod
 */
public class ReportsController implements Initializable {
    @FXML
    private Label headerLabel;
    @FXML
    private Label typeTotalLabel;
    @FXML
    private TableView<ReportTotal> totalByTypeTableView;
    @FXML
    private TableColumn<ReportTotal, String> typeCol;
    @FXML
    private TableColumn<ReportTotal, Number> totalCol;
    @FXML
    private Label monthTotalLabel;
    @FXML
    private Label january;
    @FXML
    private Label januaryTotal;
    @FXML
    private Label february;
    @FXML
    private Label februaryTotal;
    @FXML
    private Label march;
    @FXML
    private Label marchTotal;
    @FXML
    private Label april;
    @FXML
    private Label aprilTotal;
    @FXML
    private Label may;
    @FXML
    private Label mayTotal;
    @FXML
    private Label june;
    @FXML
    private Label juneTotal;
    @FXML
    private Label july;
    @FXML
    private Label julyTotal;
    @FXML
    private Label august;
    @FXML
    private Label augustTotal;
    @FXML
    private Label september;
    @FXML
    private Label septemberTotal;
    @FXML
    private Label october;
    @FXML
    private Label octoberTotal;
    @FXML
    private Label november;
    @FXML
    private Label novemberTotal;
    @FXML
    private Label december;
    @FXML
    private Label decemberTotal;
    @FXML
    private Label contactTotalLabel;
    @FXML
    private Label anikaTotal;
    @FXML
    private Label danielTotal;
    @FXML
    private Label liTotal;
    @FXML
    private TableView<Appointment> anikaScheduleTable;
    @FXML
    private TableColumn<Appointment, Number> anikaIdCol;
    @FXML
    private TableColumn<Appointment, String> anikaTitleCol;
    @FXML
    private TableColumn<Appointment, String> anikaTypeCol;
    @FXML
    private TableColumn<Appointment, String> anikaDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> anikaStartCol;
    @FXML
    private TableColumn<Appointment, String> anikaEndCol;
    @FXML
    private TableColumn<Appointment, Number> anikaCustomerIdCol;
    @FXML
    private TableView<Appointment> danielScheduleTable;
    @FXML
    private TableColumn<Appointment, Number> danielIdCol;
    @FXML
    private TableColumn<Appointment, String> danielTitleCol;
    @FXML
    private TableColumn<Appointment, String> danielTypeCol;
    @FXML
    private TableColumn<Appointment, String> danielDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> danielStartCol;
    @FXML
    private TableColumn<Appointment, String> danielEndCol;
    @FXML
    private TableColumn<Appointment, Number> danielCustomerIdCol;
    @FXML
    private TableView<Appointment> liScheduleTable;
    @FXML
    private TableColumn<Appointment, Number> liIdCol;
    @FXML
    private TableColumn<Appointment, String> liTitleCol;
    @FXML
    private TableColumn<Appointment, String> liTypeCol;
    @FXML
    private TableColumn<Appointment, String> liDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> liStartCol;
    @FXML
    private TableColumn<Appointment, String> liEndCol;
    @FXML
    private TableColumn<Appointment, Number> liCustomerIdCol;
    @FXML
    private Button appointmentsBtn;

    /**
     * Calls method to send user to <code>appointments.fxml</code> scene. Methods separated for consistency and clarity.
     *
     * @param actionEvent appointments button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void appointmentsBtnAction(ActionEvent actionEvent) throws IOException {
        goToAppointmentsForm(actionEvent);
    }

    /**
     * Sends user to <code>appointments.fxml</code> scene.
     *
     * @param actionEvent appointments button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToAppointmentsForm(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("appointments.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calculates total appointment counts by type property. Refreshes TableView to reflect these updates.
     */
    public void populateTypeTotals(){
        ObservableList<ReportTotal> totalsByType = FXCollections.observableArrayList();

        ObservableList<Appointment> appointments = DBAppointments.getAllAppointments();
        ObservableList<String> uniqueTypes = FXCollections.observableArrayList();

        //Get unique types
        uniqueTypes.add(appointments.get(0).getType().getValue());
        for(int i = 1; i < appointments.size(); i++){
            boolean matchFound = false;
            for(String type : uniqueTypes){
                if(appointments.get(i).getType().getValue().equals(type)){
                    matchFound = true;
                }
            }
            if(!matchFound){
                uniqueTypes.add(appointments.get(i).getType().getValue());
            }
        }

        //Create ReportTotal object for each unique type
        for(String type : uniqueTypes){
            ReportTotal reportTotal = new ReportTotal(type);
            totalsByType.add(reportTotal);
        }

        //Calculate totals
        for(Appointment appointment : appointments){
            for(ReportTotal reportTotal : totalsByType){
                if(appointment.getType().getValue().equals(reportTotal.getType().getValue())){
                    reportTotal.incrementTotal();
                    break;
                }
            }
        }

        totalByTypeTableView.setItems(totalsByType);
    }

    /**
     * Populates all report total fields
     */
    public void populateTotals(){
        populateTypeTotals();

        int count = 0;

        //Count Anika's appointments
        for(Appointment appointment : DBAppointments.getAllAppointments()){
            if(appointment.getContact().getName().getValue().equals("Anika Costa")){
                count++;
            }
        }
        anikaTotal.setText(Integer.toString(count));
        count = 0;

        //Count Daniel's appointments
        for(Appointment appointment : DBAppointments.getAllAppointments()){
            if(appointment.getContact().getName().getValue().equals("Daniel Garcia")){
                count++;
            }
        }
        danielTotal.setText(Integer.toString(count));
        count = 0;

        //Count Li's appointments
        for(Appointment appointment : DBAppointments.getAllAppointments()){
            if(appointment.getContact().getName().getValue().equals("Li Lee")){
                count++;
            }
        }
        liTotal.setText(Integer.toString(count));
        count = 0;

        int janCount = 0;
        int febCount = 0;
        int marCount = 0;
        int aprCount = 0;
        int mayCount = 0;
        int juneCount = 0;
        int julyCount = 0;
        int augCount = 0;
        int sepCount = 0;
        int octCount = 0;
        int novCount = 0;
        int decCount = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        for(Appointment appointment : DBAppointments.getAllAppointments()){
            ZonedDateTime aptStart = appointment.getStart();
            String month = aptStart.format(formatter);

            switch (month) {
                case "01" -> janCount++;
                case "02" -> febCount++;
                case "03" -> marCount++;
                case "04" -> aprCount++;
                case "05" -> mayCount++;
                case "06" -> juneCount++;
                case "07" -> julyCount++;
                case "08" -> augCount++;
                case "09" -> sepCount++;
                case "10" -> octCount++;
                case "11" -> novCount++;
                case "12" -> decCount++;
            }
        }

        januaryTotal.setText(Integer.toString(janCount));
        februaryTotal.setText(Integer.toString(febCount));
        marchTotal.setText(Integer.toString(marCount));
        aprilTotal.setText(Integer.toString(aprCount));
        mayTotal.setText(Integer.toString(mayCount));
        juneTotal.setText(Integer.toString(juneCount));
        julyTotal.setText(Integer.toString(julyCount));
        augustTotal.setText(Integer.toString(augCount));
        septemberTotal.setText(Integer.toString(sepCount));
        octoberTotal.setText(Integer.toString(octCount));
        novemberTotal.setText(Integer.toString(novCount));
        decemberTotal.setText(Integer.toString(decCount));
    }

    /**
     * Sets scene labels to English
     */
    public void setEnglishLabels(){
        headerLabel.setText("Reports");
        typeTotalLabel.setText("Total By Type");
        monthTotalLabel.setText("Total By Month");
        contactTotalLabel.setText("Total By Contact");
        january.setText("January");
        february.setText("February");
        march.setText("March");
        april.setText("April");
        may.setText("May");
        june.setText("June");
        july.setText("July");
        august.setText("August");
        september.setText("September");
        october.setText("October");
        november.setText("November");
        december.setText("December");
        appointmentsBtn.setText("Appointments");
    }

    /**
     * Sets scene labels to French
     */
    public void setFrenchLabels(){
        headerLabel.setText("Rapports");
        typeTotalLabel.setText("Total par type");
        monthTotalLabel.setText("Total par mois");
        contactTotalLabel.setText("Total par contact");
        january.setText("Janvier");
        february.setText("Février");
        march.setText("Mars");
        april.setText("Avril");
        may.setText("Peut");
        june.setText("Juin");
        july.setText("Juillet");
        august.setText("Août");
        september.setText("Septembre");
        october.setText("Octobre");
        november.setText("Novembre");
        december.setText("Décembre");
        appointmentsBtn.setText("Rendez-vous");
    }

    /**
     * Sets appropriate scene language, populates <code>anikaScheduleTable</code>, <code>danielScheduleTable</code>, and <code>liScheduleTable</code>
     * TableViews. Calls method to populate totals
     *
     * Lambda expression used to set cell value factories in our TableViews which provides a more concise and readable code compared
     * to creating a separate class for the callback function.
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

        typeCol.setCellValueFactory(data -> data.getValue().getType());
        totalCol.setCellValueFactory(data -> data.getValue().getTotal());

        anikaIdCol.setCellValueFactory(data -> data.getValue().getId());
        anikaTitleCol.setCellValueFactory(data -> data.getValue().getTitle());
        anikaTypeCol.setCellValueFactory(data -> data.getValue().getType());
        anikaDescriptionCol.setCellValueFactory(data -> data.getValue().getDescription());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a MM/dd");

        anikaStartCol.setCellValueFactory(data -> {
            ZonedDateTime startUTC = data.getValue().getStart();
            ZonedDateTime localStart = TimeHelper.convertUTCToLocal(startUTC);
            return new SimpleStringProperty(localStart.format(formatter));
        });

        anikaEndCol.setCellValueFactory(data -> {
            ZonedDateTime endUTC = data.getValue().getEnd();
            ZonedDateTime localEnd = TimeHelper.convertUTCToLocal(endUTC);
            return new SimpleStringProperty(localEnd.format(formatter));
        });

        anikaCustomerIdCol.setCellValueFactory(data -> data.getValue().getCustomerId());
        anikaScheduleTable.setItems(DBAppointments.getAllContactAppointments(1));

        danielIdCol.setCellValueFactory(data -> data.getValue().getId());
        danielTitleCol.setCellValueFactory(data -> data.getValue().getTitle());
        danielTypeCol.setCellValueFactory(data -> data.getValue().getType());
        danielDescriptionCol.setCellValueFactory(data -> data.getValue().getDescription());

        danielStartCol.setCellValueFactory(data -> {
            ZonedDateTime startUTC = data.getValue().getStart();
            ZonedDateTime localStart = TimeHelper.convertUTCToLocal(startUTC);
            return new SimpleStringProperty(localStart.format(formatter));
        });

        danielEndCol.setCellValueFactory(data -> {
            ZonedDateTime endUTC = data.getValue().getEnd();
            ZonedDateTime localEnd = TimeHelper.convertUTCToLocal(endUTC);
            return new SimpleStringProperty(localEnd.format(formatter));
        });

        danielCustomerIdCol.setCellValueFactory(data -> data.getValue().getCustomerId());
        danielScheduleTable.setItems(DBAppointments.getAllContactAppointments(2));

        liIdCol.setCellValueFactory(data -> data.getValue().getId());
        liTitleCol.setCellValueFactory(data -> data.getValue().getTitle());
        liTypeCol.setCellValueFactory(data -> data.getValue().getType());
        liDescriptionCol.setCellValueFactory(data -> data.getValue().getDescription());

        liStartCol.setCellValueFactory(data -> {
            ZonedDateTime startUTC = data.getValue().getStart();
            ZonedDateTime localStart = TimeHelper.convertUTCToLocal(startUTC);
            return new SimpleStringProperty(localStart.format(formatter));
        });

        liEndCol.setCellValueFactory(data -> {
            ZonedDateTime endUTC = data.getValue().getEnd();
            ZonedDateTime localEnd = TimeHelper.convertUTCToLocal(endUTC);
            return new SimpleStringProperty(localEnd.format(formatter));
        });

        liCustomerIdCol.setCellValueFactory(data -> data.getValue().getCustomerId());
        liScheduleTable.setItems(DBAppointments.getAllContactAppointments(3));

        populateTotals();
    }
}
