package BoardWithReticle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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

    private int highlightedRow = 0;
    private int highlightedCol = 0;

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Main.HEIGHT, Main.WIDTH);

        for (int i = 0; i < NUM_X_TILES; i++) {
            for (int j = 0; j < NUM_Y_TILES; j++) {
                Tile tile = new Tile(i, j, highlightedCol == i || highlightedCol == j);
                board[i][j] = tile;
                root.getChildren().addAll(tile);
            }
        }
        return root;
    }

    private class Tile extends StackPane {
        private int x, y;
        private boolean isHighlighted;
        private static final String DEFAULT_COLOR = "#89c4f4";
        private static final String HIGHLIGHT_COLOR = "#f7ca18";

        private Rectangle tileRect = new Rectangle(TILE_SIZE, TILE_SIZE);

        public Tile(int x, int y, boolean isHighlighted) {
            this.x = x;
            this.y = y;
            this.isHighlighted = isHighlighted;

            tileRect.setStroke(Color.rgb(0, 0, 0, 0.2));
            tileRect.setFill(Paint.valueOf(isHighlighted ? HIGHLIGHT_COLOR : DEFAULT_COLOR));
            getChildren().addAll(tileRect);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);
        }

        public boolean getIsHighlighted() {
            return this.isHighlighted;
        }

        public void setIsHighlighted(boolean isHighlighted) {
            this.isHighlighted = isHighlighted;
            if (isHighlighted) {
                tileRect.setFill(Paint.valueOf(HIGHLIGHT_COLOR));
            } else {
                tileRect.setFill(Paint.valueOf(DEFAULT_COLOR));
            }
        }
    }

    public void setRowHighlightState(int row, boolean isHighlight) {
        for (int i = 0; i < Main.NUM_X_TILES; i++) {
            if (i != this.highlightedCol) {
                Tile highlightedTile = this.board[i][row];
                highlightedTile.setIsHighlighted(isHighlight);
            }
        }
    }

    public void setColHighlightState(int col, boolean isHighlight) {
        for (int i = 0; i < Main.NUM_Y_TILES; i++) {
            if (i != this.highlightedRow) {
                Tile highlightedTile = this.board[col][i];
                highlightedTile.setIsHighlighted(isHighlight);
            }
        }
    }

    public void moveReticleUp() {
        this.setRowHighlightState(this.highlightedRow, false);
        this.highlightedRow = ((this.highlightedRow - 1) + Main.NUM_Y_TILES) % Main.NUM_Y_TILES;
        this.setRowHighlightState(this.highlightedRow, true);
    }

    public void moveReticleDown() {
        this.setRowHighlightState(this.highlightedRow, false);
        this.highlightedRow = (this.highlightedRow + 1) % Main.NUM_Y_TILES;
        this.setRowHighlightState(this.highlightedRow, true);
    }

    public void moveReticleRight() {
        this.setColHighlightState(this.highlightedCol, false);
        this.highlightedCol = (this.highlightedCol + 1) % Main.NUM_X_TILES;
        this.setColHighlightState(this.highlightedCol, true);
    }

    public void moveReticleLeft() {
        this.setColHighlightState(this.highlightedCol, false);
        this.highlightedCol = ((this.highlightedCol - 1) + Main.NUM_X_TILES) % Main.NUM_X_TILES;
        this.setColHighlightState(this.highlightedCol, true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = createContent();
        scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        moveReticleUp();
                        break;
                    case DOWN:
                        moveReticleDown();
                        break;
                    case RIGHT:
                        moveReticleRight();
                        break;
                    case LEFT:
                        moveReticleLeft();
                        break;
                    default:
                        break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
