import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HboxSample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("HBox Sample");
        stage.setWidth(1366);
        stage.setHeight(768);

        HBox parent = new HBox();

        parent.setSpacing(10);
//        parent.setAlignment(Pos.CENTER);
//        parent.setPadding(new Insets(10, 10, 10, 10));

        Button b1 = new Button("Button 1");
        Button b2 = new Button("Button 2");
        Button b3 = new Button("Button 3");
        Button b4 = new Button("Button 4");

        HBox.setMargin(b1, new Insets(10, 10, 10, 10));

        parent.getChildren().addAll(b1, b2, b3, b4);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }
}
