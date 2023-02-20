package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
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
import java.util.ResourceBundle;

public class CustomerModificationController implements Initializable {

    public static Customer passedCustomer = null;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<String> countryCombo;
    @FXML
    private ComboBox<String> divisionCombo;
    @FXML
    private Label headerLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label postalLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label divisionLabel;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;


    public void populateFields(){
        idField.setText(Integer.toString(passedCustomer.getId().getValue()));
        nameField.setText(passedCustomer.getName().getValue());
        addressField.setText(passedCustomer.getAddress().getValue());
        postalField.setText(passedCustomer.getPostalCode().getValue());
        phoneField.setText(passedCustomer.getPhone().getValue());

        countryCombo.setValue(passedCustomer.getCountry().getName().getValue());

        divisionCombo.getItems().clear();
        populateDivisionCombo(passedCustomer.getCountry().getId().getValue());

        divisionCombo.setValue(passedCustomer.getFirstLevelDivision().getDivision().getValue());
    }

    public void populateDivisionCombo(int countryId){
        divisionCombo.getItems().clear();
        for(FirstLevelDivision firstLevelDivision : DBFirstLevelDivisions.getAllCountryDivisions(countryId)){
            divisionCombo.getItems().add(firstLevelDivision.getDivision().getValue());
        }
    }

    public void modifyPassedCustomer(){
        int id = passedCustomer.getId().getValue();
        String name = nameField.getText();
        String address = addressField.getText();
        String postal = postalField.getText();
        String phone = phoneField.getText();

        FirstLevelDivision division = DBFirstLevelDivisions.getDivision(divisionCombo.getValue());
        int divisionId = division.getId().getValue();

        DBCustomers.updateCustomerName(id, name);
        DBCustomers.updateCustomerAddress(id, address);
        DBCustomers.updateCustomerPostal(id, postal);
        DBCustomers.updateCustomerPhone(id, phone);
        DBCustomers.updateCustomerDivisionId(id, divisionId);
    }

    public void createNewCustomer(){
        String name = nameField.getText();
        String address = addressField.getText();
        String postal = postalField.getText();
        String phone = phoneField.getText();

        FirstLevelDivision division = DBFirstLevelDivisions.getDivision(divisionCombo.getValue());
        int divisionId = division.getId().getValue();

        Customer customer = new Customer(-1, name, address, postal, phone, divisionId);
        DBCustomers.addCustomer(customer);
    }

    public Boolean validateData(){
        Alert invalidNameField = new Alert(AlertType.ERROR, "Invalid name field!");
        Alert invalidAddressField = new Alert(AlertType.ERROR, "Invalid address field!");
        Alert invalidPostalField = new Alert(AlertType.ERROR, "Invalid postal code field!");
        Alert invalidPhoneField = new Alert(AlertType.ERROR, "Invalid phone number field!");
        Alert invalidCountrySelection = new Alert(AlertType.ERROR, "Invalid country selection!");
        Alert invalidDivisionSelection = new Alert(AlertType.ERROR, "Invalid state/region selection!");

        String name = nameField.getText();
        if(name.equals("")){
            invalidNameField.showAndWait();
            return false;
        }

        String address = addressField.getText();
        if(address.equals("")){
            invalidAddressField.showAndWait();
            return false;
        }

        String postalCode = postalField.getText();
        if(postalCode.equals("")){
            invalidPostalField.showAndWait();
            return false;
        }

        String phoneNumber = phoneField.getText();
        if(phoneNumber.equals("")){
            invalidPhoneField.showAndWait();
            return false;
        }

        String country = countryCombo.getValue();
        if(country.equals("")){
            invalidCountrySelection.showAndWait();
            return false;
        }

        String division = divisionCombo.getValue();
        if(division.equals("")){
            invalidDivisionSelection.showAndWait();
            return false;
        }

        return true;
    }

    public void saveBtnAction(ActionEvent actionEvent) throws IOException{
        if(validateData()){
            if(passedCustomer != null){
                modifyPassedCustomer();
            }
            else{
                createNewCustomer();
            }

            goToCustomerDataForm(actionEvent);
        }
    }

    public void cancelBtnAction(ActionEvent actionEvent) throws IOException{
        goToCustomerDataForm(actionEvent);
    }

    public void countryComboAction(ActionEvent actionEvent){
        String countryStr = countryCombo.getValue();
        Country country = DBCountries.getCountry(countryStr);

        populateDivisionCombo(country.getId().getValue());

        if(countryStr.equals("U.S")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                divisionLabel.setText("State");
            }else{
                divisionLabel.setText("État");
            }
        }else{
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                divisionLabel.setText("Region");
            }else{
                divisionLabel.setText("Région");
            }
        }
    }

    public void goToCustomerDataForm(ActionEvent actionEvent) throws IOException {
        passedCustomer = null;

        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void setEnglishLabels(){
        headerLabel.setText("Customer Information");

        nameLabel.setText("Name");
        addressLabel.setText("Address");
        postalLabel.setText("Postal Code");
        phoneLabel.setText("Phone Number");
        countryLabel.setText("Country");
        divisionLabel.setText("Division");

        saveBtn.setText("Save");
        cancelBtn.setText("Cancel");
    }

    public void setFrenchLabels(){
        headerLabel.setText("Informations Client");

        nameLabel.setText("Nom");
        addressLabel.setText("Adresse");
        postalLabel.setText("Code Postal");
        phoneLabel.setText("Numéro de téléphone");
        countryLabel.setText("Pays");
        divisionLabel.setText("Division");

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

        countryCombo.getItems().clear();
        for(Country country : DBCountries.getAllCountries()){
            countryCombo.getItems().add(country.getName().getValue());
        }

        if(passedCustomer != null){
            populateFields();
        }
    }
}
