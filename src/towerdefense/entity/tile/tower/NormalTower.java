package towerdefense.entity.tile.tower;

import towerdefense.GameConfig;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.bullet.NormalBullet;

import java.awt.*;
import java.util.ArrayList;

public class NormalTower extends AbstractTower {

    public NormalTower(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
        super.setRadius(GameConfig.NORMAL_TOWER_RADIUS);
        super.setAttackSpeed(GameConfig.NORMAL_TOWER_ATTACK_SPEED);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        super.draw(g2d);
    }

    private long t;

    @Override
    public void attack(ArrayList<AbstractBullet> bullets, int attackSpeed) {
        long T = System.currentTimeMillis();
        if (T - t <= attackSpeed) {
            return;
        }
        t = T;
        AbstractBullet bullet = new NormalBullet(this, 0, getCenter().x, getCenter().y, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, getAngle());
        if (getxDistance() <= 0 && getyDistance() <= 0) {
            bullet.setFlag(1);
        } else if (getxDistance() <= 0 && getyDistance() >= 0) {
            bullet.setFlag(2);
        } else if (getxDistance() >= 0 && getyDistance() <= 0) {
            bullet.setFlag(3);
        } else if (getxDistance() >= 0 && getyDistance() >= 0) {
            bullet.setFlag(4);
        }
        bullets.add(bullet);
    }
}
