package towerdefense;

import towerdefense.entity.GameEntity;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.enemy.AbstractEnemy;
import towerdefense.entity.enemy.NormalEnemy;
import towerdefense.entity.tile.tower.AbstractTower;
import towerdefense.entity.tile.tower.NormalTower;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameField {
    private GameStage gameStage;
    private List<GameEntity> tiles;
    private List<AbstractTower> towers = new ArrayList<>();
    private List<AbstractEnemy> enemies = new ArrayList<>();
    private List<ArrayList<AbstractBullet>> bullets = new ArrayList<>();

    private int tick;

    public GameField() throws FileNotFoundException {
        tick = 0;
        tiles = GameStage.loadMap("src/resources/map1.txt");
        towers.add(new NormalTower(0, 17 * GameConfig.TILE_SIZE, 18 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
        bullets.add(new ArrayList<>());
        towers.add(new NormalTower(0, 5 * GameConfig.TILE_SIZE, 5 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
        bullets.add(new ArrayList<>());
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public void draw(Graphics2D g2d) {
        for (GameEntity entity : tiles) {
            entity.draw(g2d);
        }
        for (GameEntity entity : enemies) {
            entity.draw(g2d);
        }
        for (AbstractTower tower : towers) {
            tower.draw(g2d);
        }
        for (List<AbstractBullet> bullet : bullets) {
            for (AbstractBullet b : bullet) {
                b.draw(g2d);
            }
        }
    }

    public void addTower(AbstractTower tower) {
        towers.add(tower);
        bullets.add(new ArrayList<>());
    }

    public void run() {
        tick++;
        if (tick % 100 == 0) {
            enemies.add(new NormalEnemy(tick, -2 * GameConfig.TILE_SIZE, 2 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
        }
        for (AbstractEnemy enemy : enemies) {
            enemy.move();
        }
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).checkInvasion((ArrayList<AbstractEnemy>) enemies)) {
                towers.get(i).attack(bullets.get(i), towers.get(i).getAttackSpeed());
            }
        }
        for (List<AbstractBullet> bullet : bullets) {
            for (AbstractBullet b : bullet) {
                b.move();
            }
        }
    }
}
