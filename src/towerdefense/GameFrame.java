package towerdefense;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() throws HeadlessException {
        setTitle(GameConfig.GAME_NAME);
        setSize(GameConfig.SCREEN_WIDTH + 200, GameConfig.SCREEN_HEIGHT + 39);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }
}
