package towerdefense.entity.tile.road;

import towerdefense.entity.tile.AbstractTile;

import java.awt.*;

public class Road extends AbstractTile {
    public Road(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.DARK_GRAY);
        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
