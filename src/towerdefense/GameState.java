package towerdefense;

import towerdefense.entity.GameEntity;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.enemy.AbstractEnemy;
import towerdefense.entity.enemy.NormalEnemy;
import towerdefense.entity.tile.tower.AbstractTower;
import towerdefense.entity.tile.tower.MachineGunTower;
import towerdefense.entity.tile.tower.NormalTower;
import towerdefense.entity.tile.tower.SniperTower;
import towerdefense.listener.GameListener;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameState extends State {
    private GameStage gameStage;
    private GameListener gameListener;
    private List<GameEntity> tiles;
    private List<AbstractTower> towers = new ArrayList<>();
    private List<AbstractEnemy> enemies = new ArrayList<>();
    private List<ArrayList<AbstractBullet>> bullets = new ArrayList<>();
    private List<Point> wayPoints = new ArrayList<>();

    private int invadedEnemy;

    private long start;

    public GameState() throws FileNotFoundException {
        gameListener = new GameListener(this);
        start = System.nanoTime();
        invadedEnemy = 0;
        tiles = GameStage.loadMap("src/resources/map1.txt");
        wayPoints = GameStage.loadWayPoints("src/resources/map1.txt");
        towers.add(new NormalTower(0, 17 * GameConfig.TILE_SIZE, 18 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
        bullets.add(new ArrayList<>());
//        towers.add(new SniperTower(0, 5 * GameConfig.TILE_SIZE, 5 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
//        bullets.add(new ArrayList<>());
        towers.add(new MachineGunTower(0, 12 * GameConfig.TILE_SIZE, 5 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
        bullets.add(new ArrayList<>());
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    @Override
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

    @Override
    public void run() {
        long cur = System.nanoTime();
        System.out.println(cur - start);
        if ((cur - start) % 1000 == 0 && enemies.size() <= 100) {
            enemies.add(new NormalEnemy(0, 0 * GameConfig.TILE_SIZE, 2 * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, 1));
        }
        for (int i = enemies.size() - 1; i >= 0; i--) {
            for (ArrayList<AbstractBullet> bullet : bullets) {
                for (int i1 = bullet.size() - 1; i1 >= 0; i1--) {
                    if (enemies.get(i).injure(bullet.get(i1))) {
                        bullet.remove(bullet.get(i1));
                    }
                }
            }
            if (enemies.get(i).destroy() || enemies.get(i).defeat()) {
                enemies.remove(enemies.get(i));
            }
        }
        for (List<AbstractBullet> bullet : bullets) {
            for (int i = bullet.size() - 1; i >= 0; i--) {
                if (bullet.get(i).destroy()) {
                    bullet.remove(bullet.get(i));
                }
            }
        }
        for (AbstractEnemy enemy : enemies) {
            int nextX = wayPoints.get(enemy.getNextWayPoint()).x * GameConfig.TILE_SIZE;
            int nextY = wayPoints.get(enemy.getNextWayPoint()).y * GameConfig.TILE_SIZE;
            if (nextX - enemy.getX() > enemy.getSpeed()) {
                enemy.setFlag(1);
            } else if (nextX - enemy.getX() < -enemy.getSpeed()) {
                enemy.setFlag(2);
            } else if (nextY - enemy.getY() > enemy.getSpeed()) {
                enemy.setFlag(3);
            } else if (nextY - enemy.getY() < -enemy.getSpeed()) {
                enemy.setFlag(4);
            } else enemy.setNextWayPoint(enemy.getNextWayPoint() + 1);
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
