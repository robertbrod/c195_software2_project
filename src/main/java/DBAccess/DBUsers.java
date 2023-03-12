package DBAccess;

import Database.JDBC;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBUsers is used for CRUD operations within the 'users' database table.
 *
 * @author Robert Brod
 */
public class DBUsers {

    /**
     * Populates and returns ObservableList Object with all rows in 'users' database table. User object is created for each row for
     * manipulation and retrieval of data.
     *
     * @return ObservableList containing all row data in database table
     */
    public static ObservableList<User> getAllUsers(){
        ObservableList<User> allUsers = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");

                User user = new User(id, name, password, null, null, null, null);
                allUsers.add(user);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return allUsers;
    }

    /**
     * Populates and return User object with row in 'users' table with matching User ID.
     *
     * @param userId User ID
     * @return       Object containing row data in 'users' database containing User ID
     */
    public static User getUser(int userId){
        User user = null;

        try{
            String sql = "SELECT * FROM users WHERE User_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");

                user = new User(id, name, password, null, null, null, null);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Populates User object with row in 'users' table with matching User Name.
     *
     * @param userName User Name
     * @return         Object containing row data in 'users' database containing User Name
     */
    public static User getUser(String userName){
        User user = null;

        try{
            String sql = "SELECT * FROM users WHERE User_Name=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");

                user = new User(id, name, password, null, null, null, null);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Retrieves password of User in database with matching User Name.
     *
     * @param userName User Name
     * @return         Password of User
     */
    public static String getPassword(String userName){
        String password = null;

        try{
            String sql = "SELECT * FROM users WHERE User_Name=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                password = rs.getString("Password");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return password;
    }

    /**
     * Checks to see if user exists with User Name.
     *
     * @param userName User Name
     * @return         Returns true if user exists with User Name
     */
    public static Boolean checkForUser(String userName){
        boolean result = false;

        try{
            String sql = "SELECT * FROM users WHERE User_Name=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                result = true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
