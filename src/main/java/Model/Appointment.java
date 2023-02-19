package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty location;
    private SimpleStringProperty type;
    private SimpleStringProperty start;
    private SimpleStringProperty end;
    private SimpleStringProperty createDate;
    private SimpleStringProperty createdBy;
    private SimpleStringProperty lastUpdate;
    private SimpleStringProperty lastUpdatedBy;
    private SimpleIntegerProperty customerId;
    private SimpleIntegerProperty userId;
    private SimpleIntegerProperty contactId;

    public Appointment(int id, String title, String description, String location, String type, String start, String end, String createDate,
                       String createdBy, String lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId){

        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.type = new SimpleStringProperty(type);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.userId = new SimpleIntegerProperty(userId);
        this.contactId = new SimpleIntegerProperty(contactId);
    }

    public Appointment(int id, String title, String description, String location, String type, String start, String end, int customerId,
                       int userId, int contactId){

        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.type = new SimpleStringProperty(type);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.userId = new SimpleIntegerProperty(userId);
        this.contactId = new SimpleIntegerProperty(contactId);
    }

    public SimpleIntegerProperty getId(){ return id; }

    public void setId(int id){
        this.id = new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty getTitle(){ return title; }

    public void setTitle(String title){
        this.title = new SimpleStringProperty(title);
    }

    public SimpleStringProperty getDescription(){ return description; }

    public void setDescription(String description){
        this.description = new SimpleStringProperty(description);
    }

    public SimpleStringProperty getLocation(){ return location; }

    public void setLocation(String location){
        this.location = new SimpleStringProperty(location);
    }

    public SimpleStringProperty getType(){ return type; }

    public void setType(String type){
        this.type = new SimpleStringProperty(type);
    }

    public SimpleStringProperty getStart(){ return start; }

    public void setStart(String start){
        this.start = new SimpleStringProperty(start);
    }

    public SimpleStringProperty getEnd(){ return end; }

    public void setEnd(String end){
        this.end = new SimpleStringProperty(end);
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

    public SimpleIntegerProperty getCustomerId(){ return customerId; }

    public void setCustomerId(int customerId){
        this.customerId = new SimpleIntegerProperty(customerId);
    }

    public SimpleIntegerProperty getUserId(){ return userId; }

    public void setUserId(int userId){
        this.userId = new SimpleIntegerProperty(userId);
    }

    public SimpleIntegerProperty getContactId(){ return contactId; }

    public void setContactId(int contactId){
        this.contactId = new SimpleIntegerProperty(contactId);
    }
}
