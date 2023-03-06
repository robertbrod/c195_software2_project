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


public class ScheduleApplication extends Application {

    public static Language language;
    public static User user;

    public enum Language {
        ENGLISH, FRENCH
    }

    public void setLanguage(){
        String language = String.valueOf(Locale.getDefault());

        if(language.equals("en_US")){
            ScheduleApplication.language = Language.ENGLISH;
        }else{
            ScheduleApplication.language = Language.FRENCH;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        setLanguage();

        FXMLLoader fxmlLoader = new FXMLLoader(ScheduleApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.getIcons().add(new Image(ScheduleApplication.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}