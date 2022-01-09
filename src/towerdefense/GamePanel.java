package towerdefense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {
    private final GameController gameController;

    public GamePanel() {
        setBackground(Color.GRAY);
        gameController = new GameController();
        MouseListener mouseListener = new MouseListener() {
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
        addMouseListener(mouseListener);
        start();
    }

    public void start() {
        Thread thread = new Thread(runTick);
        thread.start();

        Thread thread2 = new Thread(runFrame);
        thread2.start();

        System.out.println(Thread.activeCount());
    }

    Runnable runTick = () -> {
        while (true) {
            tick();
        }
    };

    Runnable runFrame = () -> {
        while (true) {
            repaint();
        }
    };

    @Override
    public void repaint() {
        long start = System.nanoTime();
        super.repaint();
        long elapsed = System.nanoTime() - start;
        long wait = (GameConfig.GAME_NSPF - elapsed) / 1000000;
        if (wait <= 0) {
            wait = GameConfig.GAME_NSPF / 1000000;
        }
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName());
    }

    protected void tick() {
        long start = System.nanoTime();
        gameController.run();
        long elapsed = System.nanoTime() - start;
        long wait = (GameConfig.GAME_NSPT - elapsed) / 1000000;
        if (wait <= 0) {
            wait = GameConfig.GAME_NSPT / 1000000;
        }
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(Thread.currentThread().getName());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameController.draw(g2d);
    }


}
