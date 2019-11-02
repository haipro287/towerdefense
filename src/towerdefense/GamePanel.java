package towerdefense;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel {

    private GameField gameField;

    public GamePanel() throws FileNotFoundException {
        setBackground(Color.BLACK);
        gameField = new GameField();

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
                    Thread.sleep(10);
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
