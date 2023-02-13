package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class Appointment {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty location;
    private SimpleStringProperty type;
    private SimpleStringProperty start;
    private SimpleStringProperty end;
    private SimpleIntegerProperty customerId;
    private SimpleIntegerProperty userId;
    private SimpleIntegerProperty contactId;

    public Appointment(int id, String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId){
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

    public SimpleStringProperty getTitle(){ return title; }

    public SimpleStringProperty getDescription(){ return description; }

    public SimpleStringProperty getLocation(){ return location; }

    public SimpleStringProperty getType(){ return type; }

    public SimpleStringProperty getStart(){ return start; }

    public SimpleStringProperty getEnd(){ return end; }

    public SimpleIntegerProperty getCustomerId(){ return customerId; }

    public SimpleIntegerProperty getUserId(){ return userId; }

    public SimpleIntegerProperty getContactId(){ return contactId; }
}
