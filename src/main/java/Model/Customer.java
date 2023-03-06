package Model;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivisions;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.ZonedDateTime;

public class Customer {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty postalCode;
    private SimpleStringProperty phone;
    private ZonedDateTime createDate;
    private SimpleStringProperty createdBy;
    private ZonedDateTime lastUpdate;
    private SimpleStringProperty lastUpdatedBy;
    private SimpleIntegerProperty divisionId;
    private FirstLevelDivision firstLevelDivision;
    private Country country;
    private SimpleStringProperty formattedAddress;

    public Customer(int id, String name, String address, String postalCode, String phone, ZonedDateTime createDate, String createdBy,
                    ZonedDateTime lastUpdate, String lastUpdatedBy, int divisionId){

        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
        this.createDate = createDate;
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.divisionId = new SimpleIntegerProperty(divisionId);
        this.firstLevelDivision = DBFirstLevelDivisions.getDivision(divisionId);
        this.country = DBCountries.getCountry(this.firstLevelDivision.getCountryId().getValue());
        setFormattedAddress();
    }

    public void setFormattedAddress(){
        String formattedAddressStr = "" + this.address.getValue() + ", " + this.firstLevelDivision.getDivision().getValue();
        this.formattedAddress = new SimpleStringProperty(formattedAddressStr);
    }

    public SimpleIntegerProperty getId(){ return id; }

    public void setId(int id){
        this.id = new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty getName() { return name; }

    public void setName(String name){
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty getAddress(){ return address; }

    public void setAddress(String address){
        this.address = new SimpleStringProperty(address);
    }

    public SimpleStringProperty getPostalCode(){ return postalCode; }

    public void setPostalCode(String postalCode){
        this.postalCode = new SimpleStringProperty(postalCode);
    }

    public SimpleStringProperty getPhone(){ return phone; }

    public void setPhone(String phone){
        this.phone = new SimpleStringProperty(phone);
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

    public SimpleIntegerProperty getDivisionId(){ return divisionId; }

    public void setDivisionId(int divisionId){
        this.divisionId = new SimpleIntegerProperty(divisionId);
    }

    public FirstLevelDivision getFirstLevelDivision(){ return firstLevelDivision; }

    public void setFirstLevelDivision(FirstLevelDivision division){
        this.firstLevelDivision = division;
    }

    public Country getCountry(){ return country; }

    public void setCountry(Country country){
        this.country = country;
    }

    public SimpleStringProperty getFormattedAddress(){ return formattedAddress; }

    public void setFormattedAddress(String formattedAddress){
        this.formattedAddress = new SimpleStringProperty(formattedAddress);
    }
}
