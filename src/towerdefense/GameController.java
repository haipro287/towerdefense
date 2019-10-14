package towerdefense;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

public class GameController extends AnimationTimer {

    private GraphicsContext graphicsContext;

    public GameController(GraphicsContext graphicsContext) throws FileNotFoundException {
        this.graphicsContext = graphicsContext;

//        graphicsContext.drawImage(new Image(new FileInputStream("D:\\UET\\Course\\oop\\towerdefense\\src\\resources\\canvas.jpg")), 0, 0, 1000 ,956/2);


    }


    @Override
    public void handle(long l) {

    }
}
