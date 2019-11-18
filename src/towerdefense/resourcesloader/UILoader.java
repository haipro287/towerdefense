package towerdefense.resourcesloader;

import towerdefense.resourcesloader.ImageLoader;
import towerdefense.resourcesloader.SoundLoader;

import java.awt.*;

public class UILoader {
    //image MenuState
    public static Image mainMenu = ImageLoader.getImage("src/resources/Sprites/mainMenu.png");
    public static Image loadGameButton = ImageLoader.getImage("src/resources/Sprites/loadGameButton.png");
    public static Image loadGameButtonClick = ImageLoader.getImage("src/resources/Sprites/loadGameButtonClick.png");
    public static Image newGameButton = ImageLoader.getImage("src/resources/Sprites/newGameButton.png");
    public static Image newGameButtonClick = ImageLoader.getImage("src/resources/Sprites/newGameButtonClick.png");

    //image GameState
    public static Image startButton = ImageLoader.getImage("src/resources/Sprites/startButton.png");
    public static Image startButtonClick = ImageLoader.getImage("src/resources/Sprites/startButtonClick.png");
    public static Image pauseButton = ImageLoader.getImage("src/resources/Sprites/pauseButton.png");
    public static Image pauseButtonClick = ImageLoader.getImage("src/resources/Sprites/pauseButtonClick.png");
    public static Image heart = ImageLoader.getImage("src/resources/Sprites/heart.png");
    public static Image gold = ImageLoader.getImage("src/resources/Sprites/$.png");

    //image PauseState
    public static Image Pause = ImageLoader.getImage("src/resources/Sprites/Pause.png");
    public static Image resumeButton = ImageLoader.getImage("src/resources/Sprites/resumeButton.png");
    public static Image resumeButtonClick = ImageLoader.getImage("src/resources/Sprites/resumeButtonClick.png");
    public static Image menuButton = ImageLoader.getImage("src/resources/Sprites/menuButton.png");
    public static Image menuButtonClick = ImageLoader.getImage("src/resources/Sprites/menuButtonClick.png");

    //image GameOverState
    public static Image gameOver = ImageLoader.getImage("src/resources/Sprites/gameOver.png");
    public static Image restartButton = ImageLoader.getImage("src/resources/Sprites/restartButton.png");
    public static Image restartButtonClick = ImageLoader.getImage("src/resources/Sprites/restartButtonClick.png");

    public static Image imageOfMenu = ImageLoader.getImage("src/resources/Sprites/imageMenu.png");
    public static Image BG = ImageLoader.getImage("src/resources/Sprites/BG.png");

    public static Image quitButton = ImageLoader.getImage("src/resources/Sprites/quitButton.png");
    public static Image quitButtonClick = ImageLoader.getImage("src/resources/Sprites/quitButtonClick.png");

    //check MousePressed
    public static boolean isLoadGameButton = false;
    public static boolean isNewGameButton = false;
    public static boolean isStartButton = false;
    public static boolean isPauseButton = false;
    public static boolean isResumeButton = false;
    public static boolean isRestartButton = false;
    public static boolean isMenuButton = false;
    public static boolean isQuitButton = false;

}
