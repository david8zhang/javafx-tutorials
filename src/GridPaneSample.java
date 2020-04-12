import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GridPaneSample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(768);
        stage.setWidth(1366);
        stage.setTitle("Stack Pane Example");


        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");

        TextField usernameField = new TextField();
        TextField passwordField = new TextField();

        GridPane root = new GridPane();
        root.add(usernameLabel, 0, 0);
        root.add(passwordLabel, 0, 1);
        root.add(usernameField, 1, 0);
        root.add(passwordField, 1, 1);
        Scene scene = new Scene(root);

        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(10));

        stage.setScene(scene);
        stage.show();
    }
}
