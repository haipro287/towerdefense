package towerdefense.entity.bullet;

import towerdefense.GameConfig;
import towerdefense.entity.tile.tower.AbstractTower;

import java.awt.*;

public class MachineBullet extends AbstractBullet {
    public MachineBullet(AbstractTower tower, long createdTick, double x, double y, int width, int height, double angle) {
        super(tower, createdTick, x, y, width, height, angle);
        setSpeed(GameConfig.MACHINE_GUN_BULLET_SPEED);
        setDamage(GameConfig.MACHINE_GUN_BULLET_DAMAGE);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.YELLOW);
        g2d.fillOval((int) getX(), (int) getY(), getWidth() / 4, getHeight() / 4);
    }
}
