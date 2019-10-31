package towerdefense;

import towerdefense.entity.enemy.NormalEnemy;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel {

    private GameField gameField;

    public GamePanel() throws FileNotFoundException {
        setBackground(Color.BLACK);
        gameField = new GameField();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameField.draw(g2d);
    }
}
