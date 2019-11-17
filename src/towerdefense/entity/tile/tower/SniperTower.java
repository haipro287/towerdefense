package towerdefense.entity.tile.tower;

import towerdefense.GameConfig;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.bullet.NormalBullet;
import towerdefense.entity.bullet.SniperBullet;
import towerdefense.resourcesloader.ImageLoader;
import towerdefense.resourcesloader.SoundLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class SniperTower extends AbstractTower {

    public static Image image = ImageLoader.getImage("src/resources/Retina/towerDefense_tile206.png");

    public SniperTower(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
        super.setRadius(GameConfig.SNIPER_TOWER_RADIUS);
        super.setAttackSpeed(GameConfig.SNIPER_TOWER_ATTACK_SPEED);
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform backup = g2d.getTransform();
        super.draw(g2d);
        g2d.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
        g2d.setTransform(backup);
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
            setFlag(1);
        } else if (getxDistance() <= 0 && getyDistance() >= 0) {
            setFlag(2);
        } else if (getxDistance() >= 0 && getyDistance() <= 0) {
            setFlag(3);
        } else if (getxDistance() >= 0 && getyDistance() >= 0) {
            setFlag(4);
        }
        bullet.setFlag(getFlag());
        bullets.add(bullet);
        SoundLoader.play(SoundLoader.sniperTowerSFX);
    }
}
