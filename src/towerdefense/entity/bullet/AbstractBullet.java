package towerdefense.entity.bullet;

import towerdefense.GameConfig;
import towerdefense.entity.AbstractEntity;
import towerdefense.entity.Destroyable;
import towerdefense.entity.Movable;
import towerdefense.entity.tile.tower.AbstractTower;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class AbstractBullet extends AbstractEntity implements Movable, Destroyable {
    private double speed;
    private int damage;
    private double angle;
    private Point center;
    private AbstractTower tower;
    private int flag;

    public AbstractBullet(AbstractTower tower, long createdTick, double x, double y, int width, int height, double angle) {
        super(createdTick, x, y, width, height);
        this.angle = angle;
        this.tower = tower;
        this.flag = 0;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public AbstractTower getTower() {
        return tower;
    }

    public void setTower(AbstractTower tower) {
        this.tower = tower;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public boolean destroy() {
        if (getX() > GameConfig.SCREEN_WIDTH - 15 || getX() < 0 || getY() > GameConfig.SCREEN_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    @Override
    public void move() {
        switch (flag) {
            case 1: {
                setX(getX() - getSpeed() * Math.cos(angle));
                setY(getY() - getSpeed() * Math.sin(angle));
                break;
            }
            case 2: {
                setX(getX() - getSpeed() * Math.cos(angle));
                setY(getY() + getSpeed() * Math.sin(angle));
                break;
            }
            case 3: {
                setX(getX() + getSpeed() * Math.cos(angle));
                setY(getY() - getSpeed() * Math.sin(angle));
                break;
            }
            case 4: {
                setX(getX() + getSpeed() * Math.cos(angle));
                setY(getY() + getSpeed() * Math.sin(angle));
                break;
            }
        }
        setCenter();
    }

    @Override
    public Shape collider() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }
}
