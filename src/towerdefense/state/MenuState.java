package towerdefense.state;

import towerdefense.GameController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static towerdefense.GameConfig.SCREEN_HEIGHT;
import static towerdefense.GameConfig.SCREEN_WIDTH;
import static towerdefense.resourcesloader.UILoader.*;

public class MenuState extends State implements MouseListener {

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
                    gameController.states.push(new GameState(gameController, "src/resources/map.json"));
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
