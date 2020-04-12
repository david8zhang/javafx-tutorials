import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneSample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(500);

        BorderPane root = new BorderPane();

        Button b1 = new Button("Button 1");
        Button b2 = new Button("Button 2");
        Button b3 = new Button("Button 3");

        BorderPane.setAlignment(b1, Pos.CENTER);
        BorderPane.setAlignment(b2, Pos.CENTER);
        BorderPane.setAlignment(b3, Pos.CENTER);
        root.setCenter(b1);
        root.setRight(b2);
        root.setBottom(b3);

        Scene scene1 = new Scene(root);

        stage.setScene(scene1);
        stage.show();
    }
}
