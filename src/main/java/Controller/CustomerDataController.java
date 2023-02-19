package Controller;

import DBAccess.DBCustomers;
import Model.Customer;
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

    public void modifyBtnAction(ActionEvent actionEvent) throws IOException {
        Alert noCustomerSelected = new Alert(AlertType.ERROR, "No customer selected!");

        if(highlightedCustomer != null){
            CustomerModificationController.passedCustomer = highlightedCustomer;
            goToCustomerModificationForm(actionEvent);
        }else{
            noCustomerSelected.showAndWait();
        }
    }

    public void addBtnAction(ActionEvent actionEvent) throws IOException{
        highlightedCustomer = null;

        goToCustomerModificationForm(actionEvent);
    }

    public void deleteBtnAction(ActionEvent actionEvent){
        Alert noCustomerSelected = new Alert(AlertType.ERROR, "No customer selected!");

        if(highlightedCustomer != null){
            DBCustomers.removeCustomer(highlightedCustomer);
            highlightedCustomer = null;
        }else{
            noCustomerSelected.showAndWait();
        }

        customerDataTable.setItems(DBCustomers.getAllCustomers());
    }

    public void appointmentsBtnAction(ActionEvent actionEvent){

    }

    public void goToCustomerModificationForm(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(ScheduleApplication.class.getResource("customer_modification.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(data -> data.getValue().getId());
        nameCol.setCellValueFactory(data -> data.getValue().getName());
        addressCol.setCellValueFactory(data -> data.getValue().getFormattedAddress());
        postalCol.setCellValueFactory(data -> data.getValue().getPostalCode());
        phoneCol.setCellValueFactory(data -> data.getValue().getPhone());

        customerDataTable.setItems(DBCustomers.getAllCustomers());

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
