package towerdefense.entity.enemy;

import towerdefense.GameConfig;
import towerdefense.entity.*;
import towerdefense.entity.bullet.AbstractBullet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class AbstractEnemy extends AbstractEntity implements Movable, Vulnerable, Destroyable {
    private int hp;
    private double speed;
    private int armor;
    private int reward;
    private int flag;
    private int nextWayPoint;

    public AbstractEnemy(long createdTick, double x, double y, int width, int height, int flag) {
        super(createdTick, x, y, width, height);
        this.flag = flag;
        this.nextWayPoint = 0;
    }

    @Override
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getNextWayPoint() {
        return nextWayPoint;
    }

    public void setNextWayPoint(int nextWayPoint) {
        this.nextWayPoint = nextWayPoint;
    }

    @Override
    public Shape collider() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void move() {
        switch (flag) {
            case 1: {
                setX(getX() + getSpeed());
                break;
            }
            case 2: {
                setX(getX() - getSpeed());
                break;
            }
            case 3: {
                setY(getY() + getSpeed());
                break;
            }
            case 4: {
                setY(getY() - getSpeed());
                break;
            }
        }
        setCenter();
    }

    @Override
    public boolean destroy() {
        if (getX() > GameConfig.SCREEN_WIDTH || getX() < 0 || getY() > GameConfig.SCREEN_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean defeat() {
        if (hp <= 0) {
            return true;
        }
        return false;
    }

    public boolean injure(AbstractBullet bullet) {
        if (collider().intersects((Rectangle2D) bullet.collider())) {
            hp = hp - bullet.getDamage();
            return true;
        }
        return false;
    }

    public void explode(ArrayList<Explosion> explosions) {
        explosions.add(new Explosion(0, getX(), getY(), getWidth(), getHeight()));
    }
}
