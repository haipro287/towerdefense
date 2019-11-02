package towerdefense;

import towerdefense.entity.GameEntity;
import towerdefense.entity.enemy.AbstractEnemy;
import towerdefense.entity.enemy.NormalEnemy;
import towerdefense.entity.tile.road.Road;
import towerdefense.entity.tile.tower.NormalTower;
import towerdefense.listener.GameListener;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameField {
    private GameStage gameStage;
    private List<GameEntity> tiles;
    private List<AbstractEnemy> enemies = new ArrayList<>();
    private GameListener gameListener;

    public GameField() throws FileNotFoundException {
        tiles = GameStage.loadMap("src/resources/map1.txt");
        tiles.add(new NormalTower(0, 17 * GameConfig.TILE_SIZE, 17 * GameConfig.TILE_SIZE, 32 * 2, 32 * 2));
        enemies.add(new NormalEnemy(0, 0, 0, 32, 32));
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public GameListener getGameListener() {
        return gameListener;
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public void draw(Graphics2D g2d) {
        for (GameEntity entity : tiles) {
            entity.draw(g2d);
        }
        for (GameEntity entity : enemies) {
            entity.draw(g2d);
        }
    }

    public void run() {
        for (AbstractEnemy entity : enemies) {
            entity.setX(entity.getX() + 1);
        }
    }
}
