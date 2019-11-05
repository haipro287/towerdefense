package towerdefense.entity.tile.tower;

import towerdefense.GameConfig;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.bullet.NormalBullet;

import java.awt.*;
import java.util.ArrayList;

public class NormalTower extends AbstractTower {

    public NormalTower(long createdTick, double x, double y, int width, int height) {
        super(createdTick, x, y, width, height);
        super.setRadius(300);
        super.setAttackSpeed(300);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        super.draw(g2d);
    }

    @Override
    public void attack(ArrayList<AbstractBullet> bullets, int attackSpeed) {
        super.attack(bullets, attackSpeed);
    }
}
