package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty createDate;
    private SimpleStringProperty createdBy;
    private SimpleStringProperty lastUpdate;
    private SimpleStringProperty lastUpdatedBy;

    public Country(int id, String name, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
    }

    public Country(int id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public SimpleIntegerProperty getId(){
        return id;
    }

    public void setId(int id){
        this.id = new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty getName(){
        return name;
    }

    public void setName(String name){
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty getCreateDate(){ return createDate; }

    public void setCreateDate(String createDate){
        this.createDate = new SimpleStringProperty(createDate);
    }

    public SimpleStringProperty getCreatedBy(){ return createdBy; }

    public void setCreatedBy(String createdBy){
        this.createdBy = new SimpleStringProperty(createdBy);
    }

    public SimpleStringProperty getLastUpdate(){ return lastUpdate; }

    public void setLastUpdate(String lastUpdate){
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
    }

    public SimpleStringProperty getLastUpdatedBy(){ return lastUpdatedBy; }

    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
    }

}
