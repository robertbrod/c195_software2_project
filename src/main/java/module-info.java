module rbrod.scheduleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens rbrod.scheduleapp to javafx.fxml;
    exports rbrod.scheduleapp;
}