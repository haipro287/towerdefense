package towerdefense.state;

import towerdefense.GameConfig;
import towerdefense.GameController;
import towerdefense.resourcesloader.UILoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuState extends State implements MouseListener {

    public MenuState(GameController gameController) {
        super(gameController);
    }

    public void run() {

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(UILoader.imageOfMenu, 0, 0, GameConfig.SCREEN_WIDTH + 200, GameConfig.SCREEN_HEIGHT + 39, null);
        g2d.drawImage(UILoader.mainMenu, GameConfig.SCREEN_WIDTH / 3 + 10, 100, 500, 368, null);
        g2d.drawImage(UILoader.playButton, 445, 195, 270, 115, null);
        if (UILoader.isPlayButton) {
            g2d.drawImage(UILoader.playButtonClick, 445, 195, 270, 115, null);
        }
        g2d.drawImage(UILoader.quitButton, 445, 330, 270, 115, null);
        if (UILoader.isQuitButton) {
            g2d.drawImage(UILoader.quitButtonClick, 445, 330, 270, 115, null);
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
                UILoader.isPlayButton = true;
            } else if (e.getY() >= 330 && e.getY() <= 330 + 115) {
                UILoader.isQuitButton = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (UILoader.isPlayButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 195 && e.getY() <= 195 + 115) {
                    gameController.states.push(new GameState(gameController, "src/resources/map.json"));
                }
            }
            UILoader.isPlayButton = false;
        } else if (UILoader.isQuitButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 330 && e.getY() <= 330 + 115) {
                    System.exit(0);
                }
            }
            UILoader.isQuitButton = false;
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
