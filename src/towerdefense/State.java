package towerdefense;

import java.awt.*;

public abstract class State {
    public abstract void run();
    public abstract void draw(Graphics2D g2d);
}
