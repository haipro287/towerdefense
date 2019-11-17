package towerdefense.resourcesloader;

import towerdefense.resourcesloader.ImageLoader;

import java.awt.*;

public class UILoader {
    //MenuState
    public static Image imageOfMenu = ImageLoader.getImage("src/resources/Sprites/imageMenu.png");
    public static Image mainMenu = ImageLoader.getImage("src/resources/Sprites/mainMenuCut.png");
    public static Image playButton = ImageLoader.getImage("src/resources/Sprites/playButton.png");
    public static Image playButtonClick = ImageLoader.getImage("src/resources/Sprites/playButtonClick.png");
    public static Image quitButton = ImageLoader.getImage("src/resources/Sprites/quitButton.png");
    public static Image quitButtonClick = ImageLoader.getImage("src/resources/Sprites/quitButtonClick.png");
    //

    //image GameState
    public static Image pauseButton = ImageLoader.getImage("src/resources/Sprites/pauseButton.png");
    public static Image pauseButtonClick = ImageLoader.getImage("src/resources/Sprites/pauseButtonClick.png");
    public static Image heart = ImageLoader.getImage("src/resources/Sprites/heart.png");
    public static Image gold = ImageLoader.getImage("src/resources/Sprites/$.png");

    //image PauseState
    public static Image imageOfPause = ImageLoader.getImage("src/resources/Sprites/PauseState/imagePause.png");
    public static Image Pause = ImageLoader.getImage("src/resources/Sprites/PauseState/Pause.png");
    public static Image resumeButton = ImageLoader.getImage("src/resources/Sprites/PauseState/resumeButton.png");
    public static Image resumeButtonClick = ImageLoader.getImage("src/resources/Sprites/PauseState/resumeButtonClick.png");
    public static Image menuButton = ImageLoader.getImage("src/resources/Sprites/PauseState/menuButton.png");
    public static Image menuButtonClick = ImageLoader.getImage("src/resources/Sprites/PauseState/menuButtonClick.png");
    public static Image quitOfPause = ImageLoader.getImage("src/resources/Sprites/PauseState/quitOfPause.png");
    public static Image quitOfPauseClick = ImageLoader.getImage("src/resources/Sprites/PauseState/quitOfPauseClick.png");


    //image GameOverState
    public static Image gameOver = ImageLoader.getImage("src/resources/Sprites/gameOver.png");
    public static Image restartButton = ImageLoader.getImage("src/resources/Sprites/restartButton.png");
    public static Image restartButtonClick = ImageLoader.getImage("src/resources/Sprites/restartButtonClick.png");

    //check MousePressed
    public static boolean isPlayButton = false;
    public static boolean isPauseButton = false;
    public static boolean isResumeButton = false;
    public static boolean isRestartButton = false;
    public static boolean isMenuButton = false;
    public static boolean isQuitButton = false;





}
