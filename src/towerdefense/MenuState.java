package towerdefense;

import towerdefense.resourcesloader.ImageLoader;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import static towerdefense.GameConfig.SCREEN_HEIGHT;
import static towerdefense.GameConfig.SCREEN_WIDTH;
import static towerdefense.UILoader.*;

public class MenuState extends State implements MouseListener {

    //private GameController gameController;
    public MenuState(GameController gameController) {
        super(gameController);
    }

    public void run() {

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(imageOfMenu, 0, 0, SCREEN_WIDTH + 200, SCREEN_HEIGHT + 39, null);
        g2d.drawImage(mainMenu, SCREEN_WIDTH / 3 + 10, 100, 500, 368, null);
        g2d.drawImage(playButton, 445, 195, 270, 115, null);
        if (isPlayButton) {
            g2d.drawImage(playButtonClick, 445, 195, 270, 115, null);
        }
        g2d.drawImage(quitButton, 445, 330, 270, 115, null);
        if (isQuitButton) {
            g2d.drawImage(quitButtonClick, 445, 330, 270, 115, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
    }
    @Override
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
                        gameController.states.push(new GameState(gameController));
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
