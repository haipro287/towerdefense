package towerdefense;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        final Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        final GameController gameController = new GameController(graphicsContext);

        stage.setTitle(Config.GAME_NAME);
        stage.setResizable(false);

        stage.setScene(new Scene(new Pane(canvas)));
        stage.show();

        gameController.start();
    }
}
