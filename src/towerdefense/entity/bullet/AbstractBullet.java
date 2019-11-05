package towerdefense.entity.bullet;

import towerdefense.entity.AbstractEntity;
import towerdefense.entity.Movable;

public abstract class AbstractBullet extends AbstractEntity implements Movable {
    private int speed;
    private int damage;

    public AbstractBullet(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
