package towerdefense.state;

import towerdefense.GameController;
import towerdefense.entity.tile.tower.NormalTower;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class State implements MouseListener {

    public boolean gameOver;
    protected GameController gameController;

    public State(GameController gameController) {
        this.gameController = gameController;
        gameOver = false;
    }

    protected State() {
    }

    public abstract void run();

    public abstract void draw(Graphics2D g2d);

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
