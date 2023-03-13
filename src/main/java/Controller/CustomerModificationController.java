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

/**
 * CustomerModificationController is the controller class for the <code>customer_modification.fxml</code> scene.
 *
 * @author Robert Brod
 */
public class CustomerModificationController implements Initializable {

    /**
     * Static Customer object used to 'pass' Customer data between <code>CustomerDataController</code> and <code>CustomerModificationController</code>.
     */
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

    /**
     * Populates form fields if a customer was 'passed' in with that customer's data.
     */
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

    /**
     * Populates First Level Division combo appropriately based on Country selection.
     *
     * @param countryId ID of selected country
     */
    public void populateDivisionCombo(int countryId){
        divisionCombo.getItems().clear();
        for(FirstLevelDivision firstLevelDivision : DBFirstLevelDivisions.getAllCountryDivisions(countryId)){
            divisionCombo.getItems().add(firstLevelDivision.getDivision().getValue());
        }
    }

    /**
     * Updates passed customer data in database.
     */
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

    /**
     * Creates a new customer object with form's field data and adds customer to database.
     */
    public void createNewCustomer(){
        String name = nameField.getText();
        String address = addressField.getText();
        String postal = postalField.getText();
        String phone = phoneField.getText();

        FirstLevelDivision division = DBFirstLevelDivisions.getDivision(divisionCombo.getValue());
        int divisionId = division.getId().getValue();

        Customer customer = new Customer(-1, name, address, postal, phone, null, null, null, null, divisionId);
        DBCustomers.addCustomer(customer);
    }

    /**
     * Validates all form data. Validates that no form field was left blank.
     *
     * @return Returns true if data was successfully validated
     */
    public Boolean validateData(){
        Alert invalidNameField = new Alert(AlertType.ERROR, "Invalid name field!");
        Alert invalidAddressField = new Alert(AlertType.ERROR, "Invalid address field!");
        Alert invalidPostalField = new Alert(AlertType.ERROR, "Invalid postal code field!");
        Alert invalidPhoneField = new Alert(AlertType.ERROR, "Invalid phone number field!");
        Alert invalidCountrySelection = new Alert(AlertType.ERROR, "Invalid country selection!");
        Alert invalidDivisionSelection = new Alert(AlertType.ERROR, "Invalid state/region selection!");

        Alert invalidNameFieldFrench = new Alert(AlertType.ERROR, "Champ de nom invalide !");
        invalidNameFieldFrench.setHeaderText("Erreur!");
        invalidNameFieldFrench.setTitle("Erreur!");

        Alert invalidAddressFieldFrench = new Alert(AlertType.ERROR, "Champ d'adresse invalide !");
        invalidNameFieldFrench.setHeaderText("Erreur!");
        invalidNameFieldFrench.setTitle("Erreur!");

        Alert invalidPostalFieldFrench = new Alert(AlertType.ERROR, "Champ de code postal invalide !");
        invalidNameFieldFrench.setHeaderText("Erreur!");
        invalidNameFieldFrench.setTitle("Erreur!");

        Alert invalidPhoneFieldFrench = new Alert(AlertType.ERROR, "Champ de numéro de téléphone invalide !");
        invalidNameFieldFrench.setHeaderText("Erreur!");
        invalidNameFieldFrench.setTitle("Erreur!");

        Alert invalidCountrySelectionFrench = new Alert(AlertType.ERROR, "Sélection de pays invalide !");
        invalidNameFieldFrench.setHeaderText("Erreur!");
        invalidNameFieldFrench.setTitle("Erreur!");

        Alert invalidDivisionSelectionFrench = new Alert(AlertType.ERROR, "Sélection d'état/région invalide !");
        invalidNameFieldFrench.setHeaderText("Erreur!");
        invalidNameFieldFrench.setTitle("Erreur!");


        String name = nameField.getText();
        if(name.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidNameField.showAndWait();
            }else{
                invalidNameFieldFrench.showAndWait();
            }
            return false;
        }

        String address = addressField.getText();
        if(address.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidAddressField.showAndWait();
            }else{
                invalidAddressFieldFrench.showAndWait();
            }
            return false;
        }

        String postalCode = postalField.getText();
        if(postalCode.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidPostalField.showAndWait();
            }else{
                invalidPostalFieldFrench.showAndWait();
            }
            return false;
        }

        String phoneNumber = phoneField.getText();
        if(phoneNumber.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidPhoneField.showAndWait();
            }else{
                invalidPhoneFieldFrench.showAndWait();
            }
            return false;
        }

        String country = countryCombo.getValue();
        if(country.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidCountrySelection.showAndWait();
            }else{
                invalidCountrySelectionFrench.showAndWait();
            }
            return false;
        }

        String division = divisionCombo.getValue();
        if(division.equals("")){
            if(ScheduleApplication.language == ScheduleApplication.Language.ENGLISH){
                invalidDivisionSelection.showAndWait();
            }else{
                invalidDivisionSelectionFrench.showAndWait();
            }
            return false;
        }

        return true;
    }

    /**
     * Calls method to validate form data and either the method to create a new customer, or to modify the passed customer if there is one.
     * Sends user to <code>customer_data.fxml</code> scene.
     *
     * @param actionEvent save button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void saveBtnAction(ActionEvent actionEvent) throws IOException{
        if(validateData()){
            if(passedCustomer != null){
                modifyPassedCustomer();
            } else{
                createNewCustomer();
            }

            goToCustomerDataForm(actionEvent);
        }
    }

    /**
     * Calls method to send user to <code>customer_data.fxml</code> scene. Methods separated for consistency and clarity.
     *
     * @param actionEvent cancel button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void cancelBtnAction(ActionEvent actionEvent) throws IOException{
        goToCustomerDataForm(actionEvent);
    }

    /**
     * Calls method to populate First Level Division combo appropriately based on country selection. Changes label for First Level Division
     * field to appropriate table such as state or region.
     *
     * @param actionEvent country combo selection
     */
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

    /**
     * Sends user to <code>customer_date.fxml</code> scene.
     *
     * @param actionEvent save/cancel button fire, used to reference primary stage
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    public void goToCustomerDataForm(ActionEvent actionEvent) throws IOException {
        passedCustomer = null;

        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set scene labels to English
     */
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

    /**
     * Set scene labels to French
     */
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

    /**
     * Sets appropriate scene labels, populates country combo, populated field with <code>passedCustomer</code> data if there
     * is one.
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

        countryCombo.getItems().clear();
        for(Country country : DBCountries.getAllCountries()){
            countryCombo.getItems().add(country.getName().getValue());
        }

        if(passedCustomer != null){
            populateFields();
        }
    }
}
