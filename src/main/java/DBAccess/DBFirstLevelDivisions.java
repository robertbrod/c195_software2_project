package DBAccess;

import Database.JDBC;
import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBFirstLevelDivisions is used for CRUD operations within the 'first_level_divisions' database table.
 *
 * @author Robert Brod
 */
public class DBFirstLevelDivisions {

    /**
     * Populates and returns FirstLevelDivision object with row in 'first_level_divisions' table with matching Division ID.
     *
     * @param divisionId Division ID
     * @return           Object containing row data
     */
    public static FirstLevelDivision getDivision(int divisionId){
        FirstLevelDivision division = null;

        try{
            String sql = "SELECT * FROM first_level_divisions WHERE Division_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, divisionId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                division = new FirstLevelDivision(id, divisionName, null, null, null, null, countryId);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return division;
    }

    /**
     * Populates and returns FirstLevelDivision object with row in 'first_level_divisions' table with matching Division Name.
     *
     * @param divisionName Division Name
     * @return             Object containing row data in 'first_level_divisions' table containing Division Name
     */
    public static FirstLevelDivision getDivision(String divisionName){
        FirstLevelDivision division = null;

        try{
            String sql = "SELECT * FROM first_level_divisions WHERE Division=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, divisionName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                division = new FirstLevelDivision(id, name, null, null, null, null, countryId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return division;
    }

    /**
     * Populates and returns ObservableList Object with all rows in 'first_level_divisions' database table containing Country ID. FirstLevelDivision
     * object is created for each row for manipulation and retrieval of data. This method is used to populate drop down menus according to country.
     *
     * @param countryId Country ID
     * @return          ObservableList containing all row data in database table matching Country ID
     */
    public static ObservableList<FirstLevelDivision> getAllCountryDivisions(int countryId){
        ObservableList<FirstLevelDivision> firstLevelDivisions = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                FirstLevelDivision firstLevelDivision;

                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int country = rs.getInt("Country_ID");

                firstLevelDivision = new FirstLevelDivision(id, name, null, null, null, null, country);
                firstLevelDivisions.add(firstLevelDivision);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return firstLevelDivisions;
    }
}
