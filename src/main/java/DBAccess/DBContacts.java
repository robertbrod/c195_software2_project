package DBAccess;

import Database.JDBC;
import Model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

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
}
