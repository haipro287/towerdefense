package towerdefense.entity.tile.road;

import java.awt.*;

public class Spawner extends Road {
    public Spawner(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLACK);
        g2d.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
    }
}
