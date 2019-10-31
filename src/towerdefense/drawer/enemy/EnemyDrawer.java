package towerdefense.drawer.enemy;

import towerdefense.drawer.AbstractDrawer;
import towerdefense.drawer.EntityDrawer;
import towerdefense.entity.GameEntity;

import java.awt.*;

public class EnemyDrawer extends AbstractDrawer {
    public static void draw(Graphics2D g2d, GameEntity entity) {
        g2d.setPaint(Color.RED);
        g2d.fillRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    }
}
