package Controller;

import DBAccess.DBCountries;
import Model.Country;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TableView<Country> test_table;
    @FXML
    private TableColumn<Country, Number> country_id;
    @FXML
    private TableColumn<Country, String> country;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //country_id.setCellValueFactory(data -> data.getValue().getId());
        //country.setCellValueFactory(data -> data.getValue().getName());

        //test_table.setItems(DBCountries.getAllCountries());
    }
}
