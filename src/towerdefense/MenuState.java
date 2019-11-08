package towerdefense;

import towerdefense.resourcesloader.ImageLoader;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import static towerdefense.GameConfig.SCREEN_HEIGHT;
import static towerdefense.GameConfig.SCREEN_WIDTH;

public class MenuState extends State implements MouseListener {

    //private GameController gameController;
    public MenuState(GameController gameController) {
        super(gameController);
    }

    private static boolean isPlayButton = false;
    private static boolean isQuitButton = false;
    private static Image imageMainMenu = ImageLoader.getImage("src/resources/Sprites/mainMenuCut.png");
    private static Image imagePlayButton = ImageLoader.getImage("src/resources/Sprites/playButton.png");
    private static Image imageQuitButton = ImageLoader.getImage("src/resources/Sprites/quitButton.png");
    private static Image imagePlayButtonClick = ImageLoader.getImage("src/resources/Sprites/playButtonClick.png");
    private static Image imageQuitButtonClick = ImageLoader.getImage("src/resources/Sprites/quitButtonClick.png");

    private static Image imageOfMenu = ImageLoader.getImage("src/resources/Sprites/Sample2.png");

    public void run() {

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(imageOfMenu, 0, 0, SCREEN_WIDTH + 200, SCREEN_HEIGHT + 39, null);
        g2d.drawImage(imageMainMenu, SCREEN_WIDTH / 3 + 10, 100, 500, 368, null);
        g2d.drawImage(imagePlayButton, 445, 195, 270, 115, null);
        g2d.drawImage(imageQuitButton, 445, 330, 270, 115, null);
        if (isPlayButton) {
            g2d.drawImage(imagePlayButtonClick, 445, 195, 270, 115, null);
        }
        if (isQuitButton) {
            g2d.drawImage(imageQuitButtonClick, 445, 330, 270, 115, null);
        }
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
    }

    public void mousePressed(MouseEvent e) {
        if (e.getX() >= 445 && e.getX() <= 445 + 270) {
            if (e.getY() >= 195 && e.getY() <= 195 + 115) {
                isPlayButton = true;
            } else if (e.getY() >= 330 && e.getY() <= 330 + 115) {
                isQuitButton = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isPlayButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 195 && e.getY() <= 195 + 115) {
                    try {
                        gameController.states.push(new GameState());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                }
            }
            isPlayButton = false;
        } else if (isQuitButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 330 && e.getY() <= 330 + 115) {
                    System.exit(0);
                }
            }
            isQuitButton = false;
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
