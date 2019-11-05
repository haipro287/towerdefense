package towerdefense.entity.tile.road;

import java.awt.*;

public class Target extends Road {
    public Target(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.RED);
        g2d.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
    }
}
