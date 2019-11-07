package towerdefense.entity;

import java.awt.*;

public interface GameEntity extends Drawable {
    double getX();
    double getY();
    int getWidth();
    int getHeight();
    Point getCenter();
}
