package towerdefense;

import towerdefense.listener.GameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel {

    private GameField gameField;
    private GameListener gameListener;

    public GamePanel() throws FileNotFoundException {
        setBackground(Color.BLACK);
        gameField = new GameField();
        gameListener = new GameListener(gameField);

        addMouseListener(gameListener.getMouseListener());
        addKeyListener(gameListener.getKeyListener());
        Thread thread = new Thread(runnable);
        thread.start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                gameField.run();
                repaint();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameField.draw(g2d);
    }


}
