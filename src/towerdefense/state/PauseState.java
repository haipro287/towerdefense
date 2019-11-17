package towerdefense.state;

import towerdefense.GameController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static towerdefense.GameConfig.SCREEN_HEIGHT;
import static towerdefense.GameConfig.SCREEN_WIDTH;
import static towerdefense.resourcesloader.UILoader.*;
import static towerdefense.resourcesloader.UILoader.isQuitButton;

public class PauseState extends State implements MouseListener {

    public PauseState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void run() {

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(imageOfPause, 0, 0, SCREEN_WIDTH + 185, SCREEN_HEIGHT + 2, null);
        g2d.drawImage(Pause, SCREEN_WIDTH / 3 + 10, 50, 500, 500, null);
        g2d.drawImage(resumeButton, 445, 140, 270, 115, null);
        if (isResumeButton) {
            g2d.drawImage(resumeButtonClick, 445, 140, 270, 115, null);
        }
        g2d.drawImage(menuButton, 445, 275, 270, 115, null);
        if (isMenuButton) {
            g2d.drawImage(menuButtonClick, 445, 275, 270, 115, null);
        }
        g2d.drawImage(quitOfPause, 445, 415, 270, 115, null);
        if (isQuitButton) {
            g2d.drawImage(quitOfPauseClick, 445, 415, 270, 115, null);
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
            if (e.getY() >= 190 && e.getY() <= 190 + 115) {
                isResumeButton = true;
            }else if (e.getY() >= 325 && e.getY() <= 325 + 115) {
                isMenuButton = true;
            }else if (e.getY() >= 465 && e.getY() <= 465 + 115) {
                isQuitButton = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isResumeButton) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 190 && e.getY() <= 140 + 115) {
                    gameController.states.pop();
                }
            }
            isResumeButton = false;
        }else if (isMenuButton) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 325 && e.getY() <= 275 + 115) {
                    gameController.states.pop();
                    gameController.states.pop();
                }
            }
            isMenuButton = false;
        }else if (isQuitButton == true) {
            if (e.getX() >= 445 && e.getX() <= 445 + 270) {
                if (e.getY() >= 465 && e.getY() <= 415 + 115) {
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
