package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty email;

    public Contact(int id, String name, String email){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
    }

    public SimpleIntegerProperty getId(){ return id; }

    public SimpleStringProperty getName(){ return name; }

    public SimpleStringProperty getEmail(){ return email; }
}
