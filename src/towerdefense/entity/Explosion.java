package towerdefense.entity;

import towerdefense.GameConfig;
import towerdefense.resourcesloader.ImageLoader;

import java.awt.*;

public class Explosion extends AbstractEntity {

    public static Image[] images = {
            ImageLoader.getImage("src/resources/Retina/explosion1.png"),
            ImageLoader.getImage("src/resources/Retina/explosion2.png"),
            ImageLoader.getImage("src/resources/Retina/explosion3.png"),
            ImageLoader.getImage("src/resources/Retina/explosion4.png"),
            ImageLoader.getImage("src/resources/Retina/explosion5.png")
    };
    private int index;
    private int count;

    public Explosion(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
        index = 0;
        count = 0;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(images[index], (int) getX(), (int) getY(), getWidth(), getHeight(), null);
        count++;
        if (count > 10) {
            index++;
            count = 0;
        }
    }

    public boolean destroy() {
        if (index >= images.length) {
            return true;
        }
        return false;
    }

    @Override
    public Shape collider() {
        return null;
    }
}
