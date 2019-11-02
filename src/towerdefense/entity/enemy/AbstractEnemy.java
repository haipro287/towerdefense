package towerdefense.entity.enemy;

import towerdefense.entity.AbstractEntity;
import towerdefense.entity.Movable;

public abstract class AbstractEnemy extends AbstractEntity implements Movable {

    public AbstractEnemy(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }
}
