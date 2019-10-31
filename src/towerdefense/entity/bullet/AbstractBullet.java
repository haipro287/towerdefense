package towerdefense.entity.bullet;

import towerdefense.entity.AbstractEntity;

public abstract class AbstractBullet extends AbstractEntity {

    public AbstractBullet(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }
}
