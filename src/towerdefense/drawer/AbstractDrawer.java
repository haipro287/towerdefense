package towerdefense.drawer;

import towerdefense.entity.GameEntity;

import java.awt.*;

public abstract class AbstractDrawer implements EntityDrawer {
    public static void draw(Graphics2D g2d, GameEntity entity) {
        g2d.fillRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    }
}
