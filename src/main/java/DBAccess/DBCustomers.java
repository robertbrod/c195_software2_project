package DBAccess;

import Database.JDBC;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {

    public static ObservableList<Customer> getAllCustomers(){
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");

                Customer customer = new Customer(id, name, address,postal, phone, divisionId);
                allCustomers.add(customer);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return allCustomers;
    }

    public static void addCustomer(Customer customer){
        try{
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                         "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, customer.getName().getValue());
            ps.setString(2, customer.getAddress().getValue());
            ps.setString(3, customer.getPostalCode().getValue());
            ps.setString(4, customer.getPhone().getValue());
            ps.setInt(5, customer.getDivisionId().getValue());

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeCustomer(Customer customer){
        try{
            String sql = "DELETE FROM customers WHERE Customer_Name=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, customer.getName().getValue());

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
