package DBAccess;

import Database.JDBC;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {

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

    public static String getPassword(String user){
        String password = null;

        try{
            String sql = "SELECT * FROM users WHERE User_Name=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                password = rs.getString("Password");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return password;
    }

    public static Boolean checkForUser(String user){
        Boolean result = false;

        try{
            String sql = "SELECT * FROM users WHERE User_Name=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, user);

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
