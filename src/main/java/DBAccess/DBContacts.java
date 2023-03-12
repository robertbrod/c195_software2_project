package DBAccess;

import Database.JDBC;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBContacts is used for CRUD operations within the 'contacts' database table.
 *
 * @author Robert Brod
 */
public class DBContacts {

    /**
     * Populates and return ObservableList object with all rows in 'contact' database table. Contact Object is created for each
     * row for manipulation and retrieval of data.
     *
     * @return ObservableList containing row data
     */
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

    /**
     * Populates and return Contact Object with row in 'contacts' database table with matching Contact ID.
     *
     * @param contactId Contact ID
     * @return          Object containing row data
     */
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

    /**
     * Populates and return Contact Object with row in 'contacts' database table with matching Contact Name.
     *
     * @param contactName Contact Name
     * @return            Object containing row data
     */
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
