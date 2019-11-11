package towerdefense.entity.tile.mountain;

import towerdefense.resourcesloader.ImageLoader;
import towerdefense.entity.tile.AbstractTile;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Mountain extends AbstractTile {
    private static Image image = ImageLoader.getImage("src/resources/Retina/tileGrass1.png");

    public Mountain(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
//        g2d.setPaint(Color.DARK_GRAY);
//        g2d.fillRect(getX(), getY(), getWidth(), getHeight());

        g2d.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

    @Override
    public Shape collider() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }
}
