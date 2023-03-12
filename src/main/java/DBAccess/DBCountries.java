package DBAccess;

import Database.JDBC;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBCountries is used for CRUD operations within the 'countries' database table.
 *
 * @author Robert Brod
 */
public class DBCountries {

    /**
     * Populates and returns Country Object with row in 'countries' database table with matching Country ID.
     *
     * @param countryId Country ID
     * @return          Object containing row data
     */
    public static Country getCountry(int countryId){
        Country country = null;

        try{
            String sql = "SELECT * FROM countries WHERE Country_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Country_ID");
                String name = rs.getString("Country");
                country = new Country(id, name, null, null, null, null);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return country;
    }

    /**
     * Populates and returns Country Object with row in 'countries' database table with matching Country Name.
     *
     * @param countryName Country Name
     * @return            Object containing row data
     */
    public static Country getCountry(String countryName){
        Country country = null;

        try{
            String sql = "SELECT * FROM countries WHERE Country=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, countryName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Country_ID");
                String name = rs.getString("Country");
                country = new Country(id, name, null, null, null, null);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return country;
    }

    /**
     * Populates and returns ObservableList Object with all rows in 'countries' database table. Country object is created for each
     * row for manipulation and retrieval of data.
     *
     * @return ObservableList containing row data.
     */
    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country> countries = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Country_ID");
                String name = rs.getString("Country");

                Country country = new Country(id, name, null, null, null, null);
                countries.add(country);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return countries;
    }
}
