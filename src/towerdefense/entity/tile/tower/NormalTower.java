package towerdefense.entity.tile.tower;

import java.awt.*;

public class NormalTower extends AbstractTower {

    public NormalTower(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
