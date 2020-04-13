package SpriteBoard;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int TILE_SIZE = 40;
    private static final int BOARD_HEIGHT = 600;
    private static final int BOARD_WIDTH = 800;

    private static final int NUM_X_TILES = BOARD_WIDTH / TILE_SIZE;
    private static final int NUM_Y_TILES = BOARD_HEIGHT / TILE_SIZE;

    private static final String OCEAN_SPRITE = "https://opengameart.org/sites/default/files/WaterTileOcean.gif";

    private Tile[][] board = new Tile[NUM_X_TILES][NUM_Y_TILES];
    private Scene scene;

    public Parent createContent() {
        VBox root = new VBox();

        root.setMinHeight(620);

        Pane titlePane = new Pane();
        titlePane.setPrefSize(BOARD_WIDTH, 20);

        Label label = new Label("Title");
        titlePane.getChildren().addAll(label);

        Pane boardPane = new Pane();

        root.getChildren().addAll(titlePane, boardPane);
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);

        for (int i = 0; i < NUM_X_TILES; i++) {
            for (int j = 0; j < NUM_Y_TILES; j++) {
                Tile tile = new Tile(i, j, OCEAN_SPRITE);
                board[i][j] = tile;
                boardPane.getChildren().addAll(tile);
            }
        }

//        StackPane ui = new StackPane();
//        ui.setTranslateX(WIDTH - 100);
//        Rectangle uiRect = new Rectangle(100, 50);
//        uiRect.setFill(Paint.valueOf("#FFFFFF"));
//        Label label = new Label("Score: 100");
//        ui.getChildren().addAll(uiRect, label);
//        root.getChildren().addAll(ui);

        return root;
    }

    private class Tile extends StackPane {
        private int x, y;
        private boolean isHighlighted;
        private static final String DEFAULT_COLOR = "#89c4f4";
        private static final String HIGHLIGHT_COLOR = "#f7ca18";

        private Rectangle tileRect = new Rectangle(TILE_SIZE, TILE_SIZE);

        public Tile(int x, int y, String image) {
            this.x = x;
            this.y = y;

            tileRect.setStroke(Color.rgb(0, 0, 0, 0.2));
            tileRect.setFill(Paint.valueOf(DEFAULT_COLOR));

            getChildren().addAll(tileRect);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = createContent();
        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
