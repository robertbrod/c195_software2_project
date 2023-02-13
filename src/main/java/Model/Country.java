package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public Country(int id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public SimpleIntegerProperty getId(){
        return id;
    }

    public SimpleStringProperty getName(){
        return name;
    }
}
