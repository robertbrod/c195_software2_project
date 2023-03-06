package Model;

import DBAccess.DBContacts;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.ZonedDateTime;

public class Appointment {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty location;
    private SimpleStringProperty type;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private ZonedDateTime createDate;
    private SimpleStringProperty createdBy;
    private ZonedDateTime lastUpdate;
    private SimpleStringProperty lastUpdatedBy;
    private SimpleIntegerProperty customerId;
    private SimpleIntegerProperty userId;
    private SimpleIntegerProperty contactId;
    private Contact contact;

    public Appointment(int id, String title, String description, String location, String type, ZonedDateTime start, ZonedDateTime end, ZonedDateTime createDate,
                       String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId){

        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.type = new SimpleStringProperty(type);
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.userId = new SimpleIntegerProperty(userId);
        this.contactId = new SimpleIntegerProperty(contactId);
        this.contact = DBContacts.getContact(this.contactId.getValue());
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

    public ZonedDateTime getStart(){ return start; }

    public void setStart(ZonedDateTime start){
        this.start = start;
    }

    public ZonedDateTime getEnd(){ return end; }

    public void setEnd(ZonedDateTime end){
        this.end = end;
    }

    public ZonedDateTime getCreateDate(){ return createDate; }

    public void setCreateDate(ZonedDateTime createDate){
        this.createDate = createDate;
    }

    public SimpleStringProperty getCreatedBy(){ return createdBy; }

    public void setCreatedBy(String createdBy){
        this.createdBy = new SimpleStringProperty(createdBy);
    }

    public ZonedDateTime getLastUpdate(){ return lastUpdate; }

    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
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

    public Contact getContact(){ return contact; }

    public void setContact(Contact contact){
        this.contact = contact;
    }
}
