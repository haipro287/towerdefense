package towerdefense.entity.bullet;

import towerdefense.GameConfig;
import towerdefense.entity.AbstractEntity;
import towerdefense.entity.Destroyable;
import towerdefense.entity.Movable;
import towerdefense.entity.tile.tower.AbstractTower;

import java.awt.*;

public abstract class AbstractBullet extends AbstractEntity implements Movable, Destroyable {
    private double speed;
    private int damage;
    private double angle;
    private Point center;
    private AbstractTower tower;

    public AbstractBullet(AbstractTower tower, long createdTick, double x, double y, int width, int height, double angle) {
        super(createdTick, x, y, width, height);
        this.angle = angle;
        this.tower = tower;
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

    @Override
    public boolean destroy() {
        if (getX() > GameConfig.SCREEN_WIDTH - 15 || getX() < 0 || getY() > GameConfig.SCREEN_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    @Override
    public void move() {
        if (getCenter().x < tower.getCenter().x) {
            setX(getX() - getSpeed() * Math.cos(angle));
            if (getCenter().y < tower.getCenter().y) {
                setY(getY() - getSpeed() * Math.sin(angle));
            } else if (getCenter().y > tower.getCenter().y) {
                setY(getY() + getSpeed() * Math.sin(angle));
            }
        } else if (getCenter().x > tower.getCenter().x) {
            setX(getX() + getSpeed() * Math.cos(angle));
            if (getCenter().y < tower.getCenter().y) {
                setY(getY() - getSpeed() * Math.sin(angle));
            } else if (getCenter().y > tower.getCenter().y) {
                setY(getY() + getSpeed() * Math.sin(angle));
            }
        }


        setCenter();
    }
}
