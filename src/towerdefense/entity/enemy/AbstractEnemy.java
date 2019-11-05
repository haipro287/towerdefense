package towerdefense.entity.enemy;

import towerdefense.entity.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class AbstractEnemy extends AbstractEntity implements Movable, Vulnerable, Collidable, Destroyable {
    private int Hp;
    private double speed;
    private int armor;
    private String Item;

    public AbstractEnemy(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
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

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    @Override
    public Shape collider() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destroy() {

    }
}
