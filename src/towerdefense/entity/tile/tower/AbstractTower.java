package towerdefense.entity.tile.tower;

import towerdefense.GameConfig;
import towerdefense.entity.Attackable;
import towerdefense.entity.Collidable;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.bullet.NormalBullet;
import towerdefense.entity.enemy.AbstractEnemy;
import towerdefense.entity.tile.AbstractTile;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class AbstractTower extends AbstractTile implements Attackable, Collidable {
    private int radius;
    private int attackSpeed;

    public AbstractTower(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }


    private long t;

    @Override
    public void attack(ArrayList<AbstractBullet> bullets, int attackSpeed) {
        long T = System.currentTimeMillis();
        if (T - t <= attackSpeed) {
            return;
        }
        t = T;
        bullets.add(new NormalBullet(0, getX(), getY(), 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
    }

    public boolean checkInvasion(ArrayList<AbstractEnemy> enemies) {
        int count = 0;
        for (int i = 0; i < enemies.size(); i++) {
            if (collider().intersects((Rectangle2D) enemies.get(i).collider())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
        g2d.setPaint(Color.RED);
        g2d.drawOval((int) getX() - getRadius() / 2 + getWidth() / 2, (int) getY() - getRadius() / 2 + getHeight() / 2, getRadius(), getRadius());
    }

    @Override
    public Shape collider() {
        return new Ellipse2D.Double(getX() - getRadius() / 2 + getWidth() / 2, getY() - getRadius() / 2 + getHeight() / 2, getRadius(), getRadius());
    }
}
