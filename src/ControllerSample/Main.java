package ControllerSample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Hello World");
        Parent homeRoot = FXMLLoader.load(getClass().getResource("resources/home.fxml"));
        Parent loginRoot = FXMLLoader.load(getClass().getResource("resources/login.fxml"));

        Scene login = new Scene(loginRoot);
        Scene home = new Scene(homeRoot);
        primaryStage.setScene(login);
        primaryStage.show();
    }
}
