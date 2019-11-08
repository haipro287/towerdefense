package towerdefense.entity.tile.tower;

import towerdefense.GameConfig;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.bullet.NormalBullet;
import towerdefense.entity.bullet.SniperBullet;

import java.awt.*;
import java.util.ArrayList;

public class SniperTower extends AbstractTower {
    public SniperTower(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
        super.setRadius(GameConfig.SNIPER_TOWER_RADIUS);
        super.setAttackSpeed(GameConfig.SNIPER_TOWER_ATTACK_SPEED);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.RED);
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
        AbstractBullet bullet = new SniperBullet(this, 0, getCenter().x, getCenter().y, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, getAngle());
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
