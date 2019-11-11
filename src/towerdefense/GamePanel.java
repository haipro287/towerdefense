package towerdefense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel implements Runnable {
    private GameController gameController;

    public GamePanel() throws FileNotFoundException {
        setBackground(Color.GRAY);
        gameController = new GameController();
        addMouseListener(mouseListener);
//        addKeyListener(gameListener.getKeyListener());
        start();
    }

    public void start() {
        Thread thread = new Thread(this::run);
        thread.start();
    }

    @Override
    public void run() {
        long start, elapsed, wait;
        while (true) {
            start = System.nanoTime();
            tick();
            repaint();
            elapsed = System.nanoTime() - start;
            wait = (GameConfig.GAME_NSPF - elapsed)/1000000;
            if (wait <= 0) {
                wait = GameConfig.GAME_NSPF / 1000000;
            }
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void tick() {
        gameController.run();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameController.draw(g2d);
    }


    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            gameController.states.peek().mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            gameController.states.peek().mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            gameController.states.peek().mouseReleased(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };
}
