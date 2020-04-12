import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackPaneSample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(768);
        stage.setWidth(1366);
        stage.setTitle("Stack Pane Example");


        ImageView imageView = new ImageView("https://i.redd.it/dqnyfzhsnfq41.jpg");
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        Button b1 = new Button("Good hamster");

        StackPane root = new StackPane(imageView, b1);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
