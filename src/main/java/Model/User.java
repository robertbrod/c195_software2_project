package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty password;

    public User(int id, String name, String password){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
    }

    public SimpleIntegerProperty getId(){ return id; }

    public SimpleStringProperty getName(){ return name; }

    public SimpleStringProperty getPassword(){ return password; }
}
