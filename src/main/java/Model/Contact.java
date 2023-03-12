package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Contact class used to store, modify, and retrieve Contact data fetched from database.
 *
 * @author Robert Brod
 */
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

    public void setId(int id){
        this.id = new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty getName(){ return name; }

    public void setName(String name){
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty getEmail(){ return email; }

    public void setEmail(String email){
        this.email = new SimpleStringProperty(email);
    }
}
