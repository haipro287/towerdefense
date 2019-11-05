package towerdefense.entity.bullet;

import java.awt.*;

public class NormalBullet extends AbstractBullet {
    private int speed;
    private int damage;

    public NormalBullet(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
        setSpeed(2);
        setDamage(100);
    }

    @Override
    public void move() {
        setX(getX() + getSpeed());
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.CYAN);
        g2d.fillOval((int) getX(), (int) getY(), getWidth() / 4, getHeight() / 4);
    }
}
