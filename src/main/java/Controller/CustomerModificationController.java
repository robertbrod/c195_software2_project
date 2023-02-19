package Controller;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivisions;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label divisionLabel;
    @FXML
    private ComboBox<String> divisionCombo;

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

    public void saveBtnAction(ActionEvent actionEvent){
        if(passedCustomer != null){

        }
    }

    public void cancelBtnAction(ActionEvent actionEvent) throws IOException{
        goToCustomerDataForm(actionEvent);
    }

    public void countryComboAction(ActionEvent actionEvent){
        String countryStr = countryCombo.getValue();
        Country country = DBCountries.getCountry(countryStr);

        populateDivisionCombo(country.getId().getValue());
    }

    public void goToCustomerDataForm(ActionEvent actionEvent) throws IOException {
        passedCustomer = null;

        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_data.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.getItems().clear();
        for(Country country : DBCountries.getAllCountries()){
            countryCombo.getItems().add(country.getName().getValue());
        }

        if(passedCustomer != null){
            populateFields();
        }
    }
}
