package DBAccess;

import Database.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {

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
