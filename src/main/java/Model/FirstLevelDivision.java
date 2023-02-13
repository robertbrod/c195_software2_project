package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FirstLevelDivision {
    private SimpleIntegerProperty id;
    private SimpleStringProperty division;

    public FirstLevelDivision(int id, String division){
        this.id = new SimpleIntegerProperty(id);
        this.division = new SimpleStringProperty(division);
    }

    public SimpleIntegerProperty getId(){ return id; }

    public SimpleStringProperty getDivision(){ return division; }
}
