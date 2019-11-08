package towerdefense.entity;

import java.awt.*;

public interface GameEntity extends Drawable, Collidable {
    double getX();
    double getY();
    int getWidth();
    int getHeight();
    Point getCenter();
}
