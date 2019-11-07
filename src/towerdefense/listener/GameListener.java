package towerdefense.listener;

import towerdefense.GameConfig;
import towerdefense.GameState;
import towerdefense.entity.tile.tower.NormalTower;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameListener {
    private int mouseFlag = 0;
    private GameState gameState;

    public GameListener(GameState gameState) {
        this.gameState = gameState;
    }

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX());
            System.out.println(e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getX() >= GameConfig.TILE_SIZE * 17 && e.getX() <= GameConfig.TILE_SIZE * 18
                    && e.getY() >= GameConfig.TILE_SIZE * 18 && e.getY() <= GameConfig.TILE_SIZE * 19) {
                mouseFlag = 1;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (mouseFlag == 1) {
                gameState.addTower(new NormalTower(0, e.getX() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, e.getY() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE));
            }
            mouseFlag = 0;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    private KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public int getMouseFlag() {
        return mouseFlag;
    }

    public void setMouseFlag(int mouseFlag) {
        this.mouseFlag = mouseFlag;
    }
}
