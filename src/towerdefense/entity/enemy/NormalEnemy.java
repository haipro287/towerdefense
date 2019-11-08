package towerdefense.entity.enemy;

import towerdefense.GameConfig;
import towerdefense.resourcesloader.ImageLoader;

import java.awt.*;

public class NormalEnemy extends AbstractEnemy {
    private static Image image = ImageLoader.getImage("src/resources/Sprites/run/player-run-1.png");

    public NormalEnemy(long createdTick, int x, int y, int width, int height, int flag) {
        super(createdTick, x, y, width, height, flag);
        super.setHp(GameConfig.NORMAL_ENEMY_HP);
        super.setSpeed(GameConfig.NORMAL_ENEMY_SPEED);
        super.setArmor(GameConfig.NORMAL_ENEMY_ARMOR);
    }

    @Override
    public void draw(Graphics2D g2d) {
//        g2d.setPaint(Color.YELLOW);
//        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        g2d.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

}
