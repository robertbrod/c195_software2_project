package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * ReportTotal class used to store, modify, and retrieve report total data used in TableViews.
 *
 * @author Robert Brod
 */
public class ReportTotal {
    private SimpleIntegerProperty total;
    private SimpleStringProperty type;

    public ReportTotal(String type){

        this.type = new SimpleStringProperty(type);
        this.total = new SimpleIntegerProperty(0);
    }

    public SimpleStringProperty getType(){ return type; }

    public void setType(String type){
        this.type = new SimpleStringProperty(type);
    }

    public SimpleIntegerProperty getTotal(){ return total; }

    public void setTotal(int total){
        this.total = new SimpleIntegerProperty(total);
    }

    /**
     * Increment total value by 1
     */
    public void incrementTotal(){
        int oldValue = total.getValue();
        int newValue = oldValue + 1;
        total = new SimpleIntegerProperty(newValue);
    }
}
