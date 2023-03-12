package rbrod.scheduleapp;

import Database.JDBC;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * ScheduleApplication is the class that creates the main stage data and launches the JavaFX application.
 *
 * @author Robert Brod
 */
public class ScheduleApplication extends Application {

    /**
     * Static Language object used to check system language throughout application.
     */
    public static Language language;
    /**
     * Static User object used to check which user is logged in throughout application.
     */
    public static User user;

    /**
     * Enumerated data type to store languages.
     */
    public enum Language {
        ENGLISH, FRENCH
    }

    /**
     * Sets public static object accessed throughout program based on system language.
     */
    public void setLanguage(){
        String language = String.valueOf(Locale.getDefault());

        if(language.equals("en_US")){
            ScheduleApplication.language = Language.ENGLISH;
        }else{
            ScheduleApplication.language = Language.FRENCH;
        }
    }

    /**
     * Creates <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html">Stage</a> and loads <code>
     * main-form.fxml</code> <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html">Scene</a>.
     *
     * @param stage primary stage for this application, onto which the application scene can be set
     * @throws IOException necessary due to <code>.fxml</code> file being loaded from file
     */
    @Override
    public void start(Stage stage) throws IOException {
        setLanguage();

        FXMLLoader fxmlLoader = new FXMLLoader(ScheduleApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(ScheduleApplication.class.getResourceAsStream("icon.png"))));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens connection to <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/">JDBC</a> API, launches
     * standalone application, and closes connection to JDBC API upon exit.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}