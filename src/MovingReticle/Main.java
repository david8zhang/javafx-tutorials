package MovingReticle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class Main extends Application {

    private static final int TILE_SIZE = 40;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 600;

    private static final int NUM_X_TILES = HEIGHT / TILE_SIZE;
    private static final int NUM_Y_TILES = WIDTH / TILE_SIZE;

    private Tile[][] board = new Tile[NUM_X_TILES][NUM_Y_TILES];

    private Timeline horizontalAnim;
    private Timeline verticalAnim;
    private Timeline flashAnim;

    private boolean freezeHorizontalReticle = false;
    private boolean freezeVerticalReticle = false;

    private int horizontalReticleDirection = 0;
    private int verticalReticleDirection = 0;
    private int highlightedRow = 0;
    private int highlightedCol = 0;

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createGrid() {
        Pane root = new Pane();
        root.setPrefSize(Main.HEIGHT, Main.WIDTH);

        for (int i = 0; i < NUM_X_TILES; i++) {
            for (int j = 0; j < NUM_Y_TILES; j++) {
                Tile tile = new Tile(i, j, highlightedCol == i || highlightedRow == j);
                board[i][j] = tile;
                root.getChildren().addAll(tile);
            }
        }
        return root;
    }


    public void setRowHighlightState(int row, boolean isHighlight) {
        for (int i = 0; i < NUM_X_TILES; i++) {
            if (i != this.highlightedCol) {
                Tile highlightedTile = this.board[i][row];
                highlightedTile.setIsHighlighted(isHighlight);
            }
        }
    }

    public void setColHighlightState(int col, boolean isHighlight) {
        for (int i = 0; i < NUM_Y_TILES; i++) {
            if (i != this.highlightedRow) {
                Tile highlightedTile = this.board[col][i];
                highlightedTile.setIsHighlighted(isHighlight);
            }
        }
    }

    public void moveReticleUp() {
        this.setRowHighlightState(this.highlightedRow, false);
        this.highlightedRow = ((this.highlightedRow - 1) + NUM_Y_TILES) % NUM_Y_TILES;
        this.setRowHighlightState(this.highlightedRow, true);
    }

    public void moveReticleDown() {
        this.setRowHighlightState(this.highlightedRow, false);
        this.highlightedRow = (this.highlightedRow + 1) % NUM_Y_TILES;
        this.setRowHighlightState(this.highlightedRow, true);
    }

    public void moveReticleRight() {
        this.setColHighlightState(this.highlightedCol, false);
        this.highlightedCol = (this.highlightedCol + 1) % NUM_X_TILES;
        this.setColHighlightState(this.highlightedCol, true);
    }

    public void moveReticleLeft() {
        this.setColHighlightState(this.highlightedCol, false);
        this.highlightedCol = ((this.highlightedCol - 1) + NUM_X_TILES) % NUM_X_TILES;
        this.setColHighlightState(this.highlightedCol, true);
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

    private void cycleHorizontalReticle() {
        if (this.highlightedRow == NUM_Y_TILES - 1) {
            this.horizontalReticleDirection = 0;
        }
        if (this.highlightedRow == 0) {
            this.horizontalReticleDirection = 1;
        }
        if (this.horizontalReticleDirection == 0) {
            this.moveReticleUp();
        }
        if (this.horizontalReticleDirection == 1) {
            this.moveReticleDown();
        }
    }

    private void cycleVerticalReticle() {
        if (this.highlightedCol == NUM_X_TILES - 1) {
            this.verticalReticleDirection = 0;
        }
        if (this.highlightedCol == 0) {
            this.verticalReticleDirection = 1;
        }
        if (this.verticalReticleDirection == 0) {
            this.moveReticleLeft();
        }
        if (this.verticalReticleDirection == 1) {
            this.moveReticleRight();
        }
    }

    private void startHorizontalReticleCycle() {
        horizontalAnim = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            cycleHorizontalReticle();
        }));
        horizontalAnim.setCycleCount(Timeline.INDEFINITE);
        horizontalAnim.play();
    }

    private void stopHorizontalReticleCycle() {
        horizontalAnim.stop();
    }

    private void startVerticalReticleCycle() {
        verticalAnim = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            cycleVerticalReticle();
        }));
        verticalAnim.setCycleCount(Timeline.INDEFINITE);
        verticalAnim.play();
    }

    private void stopVerticalReticleCycle() {
        verticalAnim.stop();
    }

    private void startFlashIntersection() {
        flashAnim = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            Tile intersectTile = this.board[this.highlightedCol][this.highlightedRow];
            intersectTile.setIsHighlighted(!intersectTile.getIsHighlighted());
        }));
        flashAnim.setCycleCount(Timeline.INDEFINITE);
        flashAnim.play();
    }

    private void stopFlashIntersection() {
        Tile intersectTile = this.board[this.highlightedCol][this.highlightedRow];
        intersectTile.setIsHighlighted(true);
        flashAnim.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        startHorizontalReticleCycle();
        Scene scene = new Scene(createGrid());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case SPACE:
                        if (!freezeHorizontalReticle) {
                            freezeHorizontalReticle = true;
                            stopHorizontalReticleCycle();
                            startVerticalReticleCycle();
                        }
                        else if (!freezeVerticalReticle) {
                            freezeVerticalReticle = true;
                            stopVerticalReticleCycle();
                            startFlashIntersection();
                        }
                        break;
                    case R:
                        if (freezeHorizontalReticle && freezeVerticalReticle) {
                            freezeHorizontalReticle = false;
                            freezeVerticalReticle = false;
                            startHorizontalReticleCycle();
                            stopFlashIntersection();
                        }
                    default:
                        break;
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
