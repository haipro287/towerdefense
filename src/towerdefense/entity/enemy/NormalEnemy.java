package towerdefense.entity.enemy;

import java.awt.*;

public class NormalEnemy extends AbstractEnemy {

    public NormalEnemy(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.YELLOW);
        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void move() {

    }
}
