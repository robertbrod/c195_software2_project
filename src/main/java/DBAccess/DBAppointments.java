package DBAccess;

import Database.JDBC;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class DBAppointments {

    public static ObservableList<Appointment> getAllAppointments(){
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String start = rs.getString("Start");
                String end = rs.getString("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(id, title, description, location, type, start, end, customerId, userId, contactId);
                allAppointments.add(appointment);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return allAppointments;
    }

    public static void addAppointment(Appointment appointment){
        try{
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, appointment.getTitle().getValue());
            ps.setString(2, appointment.getDescription().getValue());
            ps.setString(3, appointment.getLocation().getValue());
            ps.setString(4, appointment.getType().getValue());
            ps.setString(5, appointment.getStart().getValue());
            ps.setString(6, appointment.getEnd().getValue());
            ps.setInt(7, appointment.getCustomerId().getValue());
            ps.setInt(8, appointment.getUserId().getValue());
            ps.setInt(9, appointment.getContactId().getValue());

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeAppointment(Appointment appointment){
        try{
            String sql = "DELETE FROM appointments WHERE Title=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, appointment.getTitle().getValue());

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentTitle(int id, String title){
        try{
            String sql = "UPDATE appointments " +
                         "SET Title = ? " +
                         "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentDescription(int id, String description){
        try{
            String sql = "UPDATE appointments " +
                    "SET Description = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, description);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentLocation(int id, String location){
        try{
            String sql = "UPDATE appointments " +
                    "SET Location = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, location);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentType(int id, String type){
        try{
            String sql = "UPDATE appointments " +
                    "SET Type = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, type);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentStart(int id, String start){
        try{
            String sql = "UPDATE appointments " +
                    "SET Start = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, start);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentEnd(int id, String end){
        try{
            String sql = "UPDATE appointments " +
                    "SET End = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, end);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentCustomerId(int id, int customerId){
        try{
            String sql = "UPDATE appointments " +
                    "SET Customer_ID = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentUserId(int id, int userId){
        try{
            String sql = "UPDATE appointments " +
                    "SET User_ID = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentContactId(int id, int contactId){
        try{
            String sql = "UPDATE appointments " +
                    "SET Contact_ID = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, contactId);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
