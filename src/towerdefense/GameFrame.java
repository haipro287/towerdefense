package towerdefense;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GameFrame extends JFrame {
    public GameFrame() throws HeadlessException, FileNotFoundException {
        setTitle(GameConfig.GAME_NAME);
        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }
}
