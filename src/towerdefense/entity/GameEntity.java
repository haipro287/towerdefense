package towerdefense.entity;

public interface GameEntity extends Drawable, Updatable {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
}
