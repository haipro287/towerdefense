package towerdefense.state;

import towerdefense.GameConfig;
import towerdefense.GameController;
import towerdefense.resourcesloader.SoundLoader;
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
        g2d.drawImage(UILoader.mainMenu, GameConfig.SCREEN_WIDTH / 3 + 10, 50, 500, 500, null);
        g2d.drawImage(UILoader.loadGameButton, 445, 140, 270, 115, null);
        g2d.drawImage(UILoader.newGameButton, 445, 275, 270, 115, null);
        g2d.drawImage(UILoader.quitButton, 445, 410, 270, 115, null);
        if (UILoader.isLoadGameButton) {
            g2d.drawImage(UILoader.loadGameButtonClick, 445, 140, 270, 115, null);
        }
        if (UILoader.isNewGameButton) {
            g2d.drawImage(UILoader.newGameButtonClick, 445, 275, 270, 115, null);
        }
        if (UILoader.isQuitButton) {
            g2d.drawImage(UILoader.quitButtonClick, 445, 410, 270, 115, null);
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
            if (e.getY() >= 140 && e.getY() <= 140 + 115) {
                UILoader.isLoadGameButton = true;
            } else if (e.getY() >= 275 && e.getY() <= 275 + 115) {
                UILoader.isNewGameButton = true;
            } else if (e.getY() >= 410 && e.getY() <= 410 + 115) {
                UILoader.isQuitButton = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (UILoader.isLoadGameButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 140 && e.getY() <= 140 + 115) {
                    //SoundLoader.play(SoundLoader.BGSFX);
                    gameController.states.push(new GameState(gameController, "src/resources/save.json"));
                }
            }
            UILoader.isLoadGameButton = false;
        } else if (UILoader.isNewGameButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 275 && e.getY() <= 275 + 115) {
                    //SoundLoader.play(SoundLoader.BGSFX);
                    gameController.states.push(new GameState(gameController, "src/resources/map.json"));
                }
            }
            UILoader.isNewGameButton = false;
        } else if (UILoader.isQuitButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 410 && e.getY() <= 410 + 115) {
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
