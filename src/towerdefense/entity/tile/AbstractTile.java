package towerdefense.entity.tile;

import towerdefense.entity.AbstractEntity;

public abstract class AbstractTile extends AbstractEntity {
    public AbstractTile(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }
}
