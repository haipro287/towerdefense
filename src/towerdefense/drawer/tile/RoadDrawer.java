package towerdefense.drawer.tile;

import towerdefense.drawer.AbstractDrawer;
import towerdefense.entity.GameEntity;

import java.awt.*;

public class RoadDrawer extends AbstractDrawer {
    public static void draw(Graphics2D g2d, GameEntity entity) {
        g2d.setPaint(Color.DARK_GRAY);
        g2d.fillRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    }
}
