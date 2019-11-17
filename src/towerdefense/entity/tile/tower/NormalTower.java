package towerdefense.entity.tile.tower;

import towerdefense.GameConfig;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.bullet.NormalBullet;
import towerdefense.resourcesloader.ImageLoader;
import towerdefense.resourcesloader.SoundLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class NormalTower extends AbstractTower {

    public static Image image = ImageLoader.getImage("src/resources/Retina/towerDefense_tile249.png");

    public NormalTower(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
        super.setRadius(GameConfig.NORMAL_TOWER_RADIUS);
        super.setAttackSpeed(GameConfig.NORMAL_TOWER_ATTACK_SPEED);
        super.setCost(GameConfig.NORMAL_TOWER_COST);
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
        AbstractBullet bullet = new NormalBullet(this, 0, getCenter().x, getCenter().y, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, getAngle());
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
        SoundLoader.play("normal.wav");
    }
}
