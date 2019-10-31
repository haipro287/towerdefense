package towerdefense.entity.enemy;

import towerdefense.entity.AbstractEntity;

public abstract class AbstractEnemy extends AbstractEntity {

    public AbstractEnemy(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }
}
