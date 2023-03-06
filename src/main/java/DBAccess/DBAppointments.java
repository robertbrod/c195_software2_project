package DBAccess;

import Database.JDBC;
import Helper.TimeHelper;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rbrod.scheduleapp.ScheduleApplication;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

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

                ZonedDateTime zonedStartTime;
                try{
                    LocalDate localStartDate = rs.getDate("Start").toLocalDate();
                    LocalTime localStartTime = rs.getTime("Start").toLocalTime();
                    zonedStartTime = ZonedDateTime.of(localStartDate, localStartTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedStartTime = null;
                }

                ZonedDateTime zonedEndTime;
                try{
                    LocalDate localEndDate = rs.getDate("End").toLocalDate();
                    LocalTime localEndTime = rs.getTime("End").toLocalTime();
                    zonedEndTime = ZonedDateTime.of(localEndDate, localEndTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedEndTime = null;
                }

                ZonedDateTime zonedCreateTime;
                try{
                    LocalDate localCreateDate = rs.getDate("Create_Date").toLocalDate();
                    LocalTime localCreateTime = rs.getTime("Create_Date").toLocalTime();
                    zonedCreateTime = ZonedDateTime.of(localCreateDate, localCreateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedCreateTime = null;
                }


                String createdBy = rs.getString("Created_By");

                ZonedDateTime zonedUpdateTime;
                try{
                    LocalDate localUpdateDate = rs.getDate("Last_Update").toLocalDate();
                    LocalTime localUpdateTime = rs.getTime("Last_Update").toLocalTime();
                    zonedUpdateTime = ZonedDateTime.of(localUpdateDate, localUpdateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedUpdateTime = null;
                }

                String updatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(id, title, description, location, type, zonedStartTime, zonedEndTime, zonedCreateTime,
                                                          createdBy, zonedUpdateTime, updatedBy, customerId, userId, contactId);

                allAppointments.add(appointment);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return allAppointments;
    }

    public static ObservableList<Appointment> getAllCustomerAppointments(int custId){
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE Customer_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, custId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");

                ZonedDateTime zonedStartTime;
                try{
                    LocalDate localStartDate = rs.getDate("Start").toLocalDate();
                    LocalTime localStartTime = rs.getTime("Start").toLocalTime();
                    zonedStartTime = ZonedDateTime.of(localStartDate, localStartTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedStartTime = null;
                }

                ZonedDateTime zonedEndTime;
                try{
                    LocalDate localEndDate = rs.getDate("End").toLocalDate();
                    LocalTime localEndTime = rs.getTime("End").toLocalTime();
                    zonedEndTime = ZonedDateTime.of(localEndDate, localEndTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedEndTime = null;
                }

                ZonedDateTime zonedCreateTime;
                try{
                    LocalDate localCreateDate = rs.getDate("Create_Date").toLocalDate();
                    LocalTime localCreateTime = rs.getTime("Create_Date").toLocalTime();
                    zonedCreateTime = ZonedDateTime.of(localCreateDate, localCreateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedCreateTime = null;
                }


                String createdBy = rs.getString("Created_By");

                ZonedDateTime zonedUpdateTime;
                try{
                    LocalDate localUpdateDate = rs.getDate("Last_Update").toLocalDate();
                    LocalTime localUpdateTime = rs.getTime("Last_Update").toLocalTime();
                    zonedUpdateTime = ZonedDateTime.of(localUpdateDate, localUpdateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedUpdateTime = null;
                }

                String updatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(id, title, description, location, type, zonedStartTime, zonedEndTime, zonedCreateTime,
                        createdBy, zonedUpdateTime, updatedBy, customerId, userId, contactId);

                allAppointments.add(appointment);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return allAppointments;
    }

    public static ObservableList<Appointment> getWeeklyAppointments(){
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE Start < date_add(now(), interval 1 week)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");

                ZonedDateTime zonedStartTime;
                try{
                    LocalDate localStartDate = rs.getDate("Start").toLocalDate();
                    LocalTime localStartTime = rs.getTime("Start").toLocalTime();
                    zonedStartTime = ZonedDateTime.of(localStartDate, localStartTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedStartTime = null;
                }

                ZonedDateTime zonedEndTime;
                try{
                    LocalDate localEndDate = rs.getDate("End").toLocalDate();
                    LocalTime localEndTime = rs.getTime("End").toLocalTime();
                    zonedEndTime = ZonedDateTime.of(localEndDate, localEndTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedEndTime = null;
                }

                ZonedDateTime zonedCreateTime;
                try{
                    LocalDate localCreateDate = rs.getDate("Create_Date").toLocalDate();
                    LocalTime localCreateTime = rs.getTime("Create_Date").toLocalTime();
                    zonedCreateTime = ZonedDateTime.of(localCreateDate, localCreateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedCreateTime = null;
                }


                String createdBy = rs.getString("Created_By");

                ZonedDateTime zonedUpdateTime;
                try{
                    LocalDate localUpdateDate = rs.getDate("Last_Update").toLocalDate();
                    LocalTime localUpdateTime = rs.getTime("Last_Update").toLocalTime();
                    zonedUpdateTime = ZonedDateTime.of(localUpdateDate, localUpdateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedUpdateTime = null;
                }

                String updatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(id, title, description, location, type, zonedStartTime, zonedEndTime, zonedCreateTime,
                        createdBy, zonedUpdateTime, updatedBy, customerId, userId, contactId);

                allAppointments.add(appointment);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return allAppointments;
    }

    public static ObservableList<Appointment> getMonthlyAppointments(){
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE Start < date_add(now(), interval 1 month)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");

                ZonedDateTime zonedStartTime;
                try{
                    LocalDate localStartDate = rs.getDate("Start").toLocalDate();
                    LocalTime localStartTime = rs.getTime("Start").toLocalTime();
                    zonedStartTime = ZonedDateTime.of(localStartDate, localStartTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedStartTime = null;
                }

                ZonedDateTime zonedEndTime;
                try{
                    LocalDate localEndDate = rs.getDate("End").toLocalDate();
                    LocalTime localEndTime = rs.getTime("End").toLocalTime();
                    zonedEndTime = ZonedDateTime.of(localEndDate, localEndTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedEndTime = null;
                }

                ZonedDateTime zonedCreateTime;
                try{
                    LocalDate localCreateDate = rs.getDate("Create_Date").toLocalDate();
                    LocalTime localCreateTime = rs.getTime("Create_Date").toLocalTime();
                    zonedCreateTime = ZonedDateTime.of(localCreateDate, localCreateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedCreateTime = null;
                }


                String createdBy = rs.getString("Created_By");

                ZonedDateTime zonedUpdateTime;
                try{
                    LocalDate localUpdateDate = rs.getDate("Last_Update").toLocalDate();
                    LocalTime localUpdateTime = rs.getTime("Last_Update").toLocalTime();
                    zonedUpdateTime = ZonedDateTime.of(localUpdateDate, localUpdateTime, ZoneId.of("UTC"));
                } catch(Exception e){
                    zonedUpdateTime = null;
                }

                String updatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(id, title, description, location, type, zonedStartTime, zonedEndTime, zonedCreateTime,
                        createdBy, zonedUpdateTime, updatedBy, customerId, userId, contactId);

                allAppointments.add(appointment);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return allAppointments;
    }

    public static void addAppointment(Appointment appointment){
        try{
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Customer_ID, User_ID, Contact_ID) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, appointment.getTitle().getValue());
            ps.setString(2, appointment.getDescription().getValue());
            ps.setString(3, appointment.getLocation().getValue());
            ps.setString(4, appointment.getType().getValue());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            ZonedDateTime start = appointment.getStart();
            String startStr = start.format(formatter);
            ps.setString(5, startStr);

            ZonedDateTime end = appointment.getEnd();
            String endStr = end.format(formatter);
            ps.setString(6, endStr);

            ZonedDateTime createDate = TimeHelper.getLocalToUTCConversion();
            ps.setString(7, createDate.format(formatter));

            ps.setString(8, ScheduleApplication.user.getName().getValue());
            ps.setInt(9, appointment.getCustomerId().getValue());
            ps.setInt(10, appointment.getUserId().getValue());
            ps.setInt(11, appointment.getContactId().getValue());

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeAppointment(Appointment appointment){
        try{
            String sql = "DELETE FROM appointments WHERE Appointment_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, appointment.getId().getValue());

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentTitle(int id, String title){
        try{
            String sql = "UPDATE appointments " +
                         "SET Title = ?, Last_Update = ?, Last_Updated_By = ? " +
                         "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentDescription(int id, String description){
        try{
            String sql = "UPDATE appointments " +
                    "SET Description = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, description);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentLocation(int id, String location){
        try{
            String sql = "UPDATE appointments " +
                    "SET Location = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, location);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentType(int id, String type){
        try{
            String sql = "UPDATE appointments " +
                    "SET Type = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, type);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentStart(int id, String start){
        try{
            String sql = "UPDATE appointments " +
                    "SET Start = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, start);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentEnd(int id, String end){
        try{
            String sql = "UPDATE appointments " +
                    "SET End = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, end);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentCustomerId(int id, int customerId){
        try{
            String sql = "UPDATE appointments " +
                    "SET Customer_ID = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentUserId(int id, int userId){
        try{
            String sql = "UPDATE appointments " +
                    "SET User_ID = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, userId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateAppointmentContactId(int id, int contactId){
        try{
            String sql = "UPDATE appointments " +
                    "SET Contact_ID = ?, Last_Update = ?, Last_Updated_By = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, contactId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime lastUpdate = TimeHelper.getLocalToUTCConversion();
            ps.setString(2, lastUpdate.format(formatter));

            ps.setString(3, ScheduleApplication.user.getName().getValue());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
