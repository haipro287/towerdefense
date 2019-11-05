package towerdefense.entity.tile.mountain;

import towerdefense.resourcesloader.ImageLoader;
import towerdefense.entity.tile.AbstractTile;

import java.awt.*;

public class Mountain extends AbstractTile {
    public Mountain(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
    }

    private static Image image = ImageLoader.getImage("src/resources/Sprites/grass_tile_1.png");

    @Override
    public void draw(Graphics2D g2d) {
//        g2d.setPaint(Color.DARK_GRAY);
//        g2d.fillRect(getX(), getY(), getWidth(), getHeight());

        g2d.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }
}
