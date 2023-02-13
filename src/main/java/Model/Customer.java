package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleIntegerProperty postalCode;
    private SimpleStringProperty phone;

    public Customer(int id, String name, String address, int postalCode, String phone){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.postalCode = new SimpleIntegerProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
    }

    public SimpleIntegerProperty getId(){ return id; }

    public SimpleStringProperty getName() { return name; }

    public SimpleStringProperty getAddress(){ return address; }

    public SimpleIntegerProperty getPostalCode(){ return postalCode; }

    public SimpleStringProperty getPhone(){ return phone; }
}
