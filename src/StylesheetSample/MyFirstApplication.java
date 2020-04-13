package StylesheetSample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyFirstApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Matrix Marauders");
        stage.setWidth(1366);
        stage.setHeight(768);
        stage.setX(0);
        stage.setY(0);

        VBox parent = new VBox();
        ImageView imageView = new ImageView("https://i.redd.it/dqnyfzhsnfq41.jpg");
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);

        Label label1 = new Label("This is a label", imageView);
        Label label2 = new Label("another label");
        label2.setTextFill(Color.web("#03fc1c"));
        label2.setFont(new Font("Helvetica", 45));

        Hyperlink hyperLink = new Hyperlink("Click me");
        hyperLink.setOnAction(e -> {
            System.out.println("Link was smashed");
        });

        Hyperlink link = new Hyperlink("Something");
        link.setStyle("-fx-background-color: lightcoral");

        ToggleButton button1 = new ToggleButton("Button 1");
        ToggleButton button2 = new ToggleButton("Button 2");
        ToggleButton button3 = new ToggleButton("Button 3");
        ToggleGroup buttonGroup = new ToggleGroup();

        button1.setToggleGroup(buttonGroup);
        button2.setToggleGroup(buttonGroup);
        button3.setToggleGroup(buttonGroup);

        RadioButton r1 = new RadioButton("Radio Button");
        r1.setToggleGroup(buttonGroup);

        parent.getChildren().addAll(button1, button2, button3, r1);
        Scene scene1 = new Scene(parent);
        scene1.getStylesheets().add("StylesheetSample/stylesheets/styles.css");

        stage.setScene(scene1);
        stage.show();
    }
}
