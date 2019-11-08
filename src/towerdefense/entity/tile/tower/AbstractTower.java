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

public abstract class AbstractTower extends AbstractTile implements Attackable {
    private int radius;
    private int attackSpeed;
    private double angle;

    private double xDistance;
    private double yDistance;

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


    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getxDistance() {
        return xDistance;
    }

    public void setxDistance(double xDistance) {
        this.xDistance = xDistance;
    }

    public double getyDistance() {
        return yDistance;
    }

    public void setyDistance(double yDistance) {
        this.yDistance = yDistance;
    }

    public boolean checkInvasion(ArrayList<AbstractEnemy> enemies) {
        int count = 0;
        for (int i = 0; i < enemies.size(); i++) {
            if (collider().intersects((Rectangle2D) enemies.get(i).collider())) {
                xDistance = (double) enemies.get(i).getCenter().x - this.getCenter().x;
                yDistance = (double) enemies.get(i).getCenter().y - this.getCenter().y;

                this.angle = Math.atan(Math.abs((double) (enemies.get(i).getCenter().y - this.getCenter().y) / (enemies.get(i).getCenter().x - this.getCenter().x)));
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
