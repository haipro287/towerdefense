package towerdefense.entity.tile.mountain;

import towerdefense.entity.tile.AbstractTile;

import java.awt.*;

public class Mountain extends AbstractTile {
    public Mountain(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.GREEN);
        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
