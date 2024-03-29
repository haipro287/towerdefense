package towerdefense.state;

import towerdefense.GameConfig;
import towerdefense.GameController;
import towerdefense.resourcesloader.UILoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;


public class GameOverState extends State implements MouseListener {

    public GameOverState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void run() {

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(UILoader.BG, 0, 0, GameConfig.SCREEN_WIDTH + 200, GameConfig.SCREEN_HEIGHT + 39, null);
        g2d.drawImage(UILoader.gameOver, GameConfig.SCREEN_WIDTH / 3 + 10, 50, 500, 500, null);
        g2d.drawImage(UILoader.restartButton, 445, 140, 270, 115, null);
        g2d.drawImage(UILoader.menuButton, 445, 275, 270, 115, null);
        g2d.drawImage(UILoader.quitButton, 445, 410, 270, 115, null);
        if (UILoader.isRestartButton) {
            g2d.drawImage(UILoader.restartButtonClick, 445, 140, 270, 115, null);
        }
        if (UILoader.isMenuButton) {
            g2d.drawImage(UILoader.menuButtonClick, 445, 275, 270, 115, null);
        }
        if (UILoader.isQuitButton) {
            g2d.drawImage(UILoader.quitButtonClick, 445, 410, 270, 115, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println(e.getX());
//        System.out.println(e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() >= 445 && e.getX() <= 445 + 270) {
            if (e.getY() >= 140 && e.getY() <= 140 + 115) {
                UILoader.isRestartButton = true;
            } else if (e.getY() >= 275 && e.getY() <= 275 + 115) {
                UILoader.isMenuButton = true;
            } else if (e.getY() >= 410 && e.getY() <= 410 + 115) {
                UILoader.isQuitButton = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (UILoader.isRestartButton) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 140 && e.getY() <= 140 + 115) {
                    gameController.states.pop();
                    gameController.states.push(new GameState(gameController, "src/resources/map.json"));
                }
            }
            UILoader.isRestartButton = false;
        } else if (UILoader.isMenuButton) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 275 && e.getY() <= 275 + 115) {
                    gameController.states.pop();
                }
            }
            UILoader.isMenuButton = false;
        } else if (UILoader.isQuitButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 410 && e.getY() <= 410 + 115) {
                    System.exit(0);
                }
            }
            UILoader.isQuitButton = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
