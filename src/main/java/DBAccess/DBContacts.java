package DBAccess;

import Database.JDBC;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

    public static ObservableList<Contact> getAllContacts(){
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contact contact = new Contact(id, name, email);
                allContacts.add(contact);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return allContacts;
    }

    public static Contact getContact(int contactId){
        Contact contact = null;

        try{
            String sql = "SELECT * FROM contacts WHERE Contact_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                contact = new Contact(id, name, email);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return contact;
    }

    public static Contact getContact(String contactName){
        Contact contact = null;

        try{
            String sql = "SELECT * FROM contacts WHERE Contact_Name=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, contactName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                contact = new Contact(id, name, email);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return contact;
    }
}
