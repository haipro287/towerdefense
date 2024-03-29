package towerdefense.entity;

import java.awt.*;

public abstract class AbstractEntity implements GameEntity {
    private long createdTick;
    private double x;
    private double y;
    private int width;
    private int height;
    private Point center;

    public AbstractEntity(long createdTick, double x, double y, int width, int height) {
        this.createdTick = createdTick;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.center = new Point((int) this.x + this.width / 2, (int) this.y + this.height / 2);
    }

    public long getCreatedTick() {
        return createdTick;
    }

    public void setCreatedTick(long createdTick) {
        this.createdTick = createdTick;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setCenter() {
        this.center.x = (int) this.x + this.width / 2;
        this.center.y = (int) this.y + this.height / 2;
    }
    @Override
    public void draw(Graphics2D g2d) {

    }
}
