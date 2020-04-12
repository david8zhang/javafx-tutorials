package board;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int TILE_SIZE = 40;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 600;

    private static final int NUM_X_TILES = HEIGHT / TILE_SIZE;
    private static final int NUM_Y_TILES = WIDTH / TILE_SIZE;

    private Tile[][] board = new Tile[NUM_X_TILES][NUM_Y_TILES];
    private Scene scene;

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Main.HEIGHT, Main.WIDTH);

        for (int i = 0; i < NUM_X_TILES; i++) {
            for (int j = 0; j < NUM_Y_TILES; j++) {
                Tile tile = new Tile(i, j);
                board[i][j] = tile;
                root.getChildren().addAll(tile);
            }
        }
        return root;
    }

    private class Tile extends StackPane {
        private int x, y;

        private Rectangle tileRect = new Rectangle(TILE_SIZE, TILE_SIZE);

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;

            tileRect.setStroke(Color.rgb(0, 0, 0, 0.2));
            tileRect.setFill(Paint.valueOf("#89c4f4"));
            getChildren().addAll(tileRect);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
