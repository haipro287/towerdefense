package towerdefense.entity.tile.tower;

import towerdefense.entity.tile.AbstractTile;

public abstract class AbstractTower extends AbstractTile {
    public AbstractTower(long createdTick, int x, int y, int width, int height) {
        super(createdTick, x, y, width, height);
    }
}
