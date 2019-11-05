package towerdefense.entity.enemy;

import towerdefense.resourcesloader.ImageLoader;

import java.awt.*;

public class NormalEnemy extends AbstractEnemy {
    public NormalEnemy(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
        super.setHp(100);
        super.setSpeed(0.8);
        super.setArmor(10);
    }

    private static Image image = ImageLoader.getImage("src/resources/Sprites/run/player-run-1.png");

    @Override
    public void draw(Graphics2D g2d) {
//        g2d.setPaint(Color.YELLOW);
//        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        g2d.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void move() {
        setX(getX() + getSpeed());
    }
}
