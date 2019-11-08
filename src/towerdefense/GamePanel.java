package towerdefense;

import towerdefense.listener.GameListener;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Stack;

public class GamePanel extends JPanel implements Runnable {

    private GameState gameState;
    private GameListener gameListener;
    private Stack<State> states;

    public GamePanel() throws FileNotFoundException {
        setBackground(Color.BLACK);
        gameState = new GameState();
        gameListener = new GameListener(gameState);
        states = new Stack<>();
        states.add(gameState);

        addMouseListener(gameListener.getMouseListener());
        addKeyListener(gameListener.getKeyListener());
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
//            System.out.println(elapsed);
            wait = (GameConfig.GAME_NSPF - elapsed)/1000000;
            if (wait <= 0) {
                wait = GameConfig.GAME_NSPF/1000000;
            }
//            System.out.println(wait);
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void tick() {
        states.peek().run();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameState.draw(g2d);
    }


}
