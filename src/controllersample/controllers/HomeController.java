package controllersample.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {
    public void onClickEvent(MouseEvent mouseEvent) {
        System.out.println("Screen Clicked");
    }
}
