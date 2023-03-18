module rbrod.scheduleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens Controller to javafx.fxml;
    exports Model;
    exports Controller;
    exports Database;
    exports Helper;
    exports DBAccess;

    exports rbrod.scheduleapp;
    opens rbrod.scheduleapp;
}