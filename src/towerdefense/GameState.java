package towerdefense;

import towerdefense.entity.GameEntity;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.enemy.AbstractEnemy;
import towerdefense.entity.enemy.NormalEnemy;
import towerdefense.entity.tile.AbstractTile;
import towerdefense.entity.tile.road.Road;
import towerdefense.entity.tile.tower.AbstractTower;
import towerdefense.entity.tile.tower.MachineGunTower;
import towerdefense.entity.tile.tower.NormalTower;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameState extends State implements MouseListener {
    private GameStage gameStage;
    private List<ArrayList<AbstractTile>> tiles;
    private List<AbstractTower> towers = new ArrayList<>();
    private List<AbstractEnemy> enemies = new ArrayList<>();
    private List<ArrayList<AbstractBullet>> bullets = new ArrayList<>();
    private List<Point> wayPoints = new ArrayList<>();

    private int mouseFlag;
    private int invadedEnemy;

    private long start;

    public GameState() throws FileNotFoundException {
        start = System.nanoTime();
        mouseFlag = 0;
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

    public void draw(Graphics2D g2d) {
        //draw UI component


        //draw tiles map
        for (ArrayList<AbstractTile> tile : tiles) {
            for (AbstractTile t : tile) {
                t.draw(g2d);
            }
        }

        //draw enemies
        for (GameEntity entity : enemies) {
            entity.draw(g2d);
        }

        //draw towers
        for (AbstractTower tower : towers) {
            tower.draw(g2d);
        }

        //draw bullets
        for (List<AbstractBullet> bullet : bullets) {
            for (AbstractBullet b : bullet) {
                b.draw(g2d);
            }
        }
    }

    @Override
    public void run() {
        long cur = System.nanoTime();

        //spawn enemies
        if ((cur - start) % 1000 == 0 && enemies.size() <= 100) {
            enemies.add(new NormalEnemy(0, 0 * GameConfig.TILE_SIZE, 2 * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, 1));
        }

        //check enemies die
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

        //delete bullets when hit enemies
        for (List<AbstractBullet> bullet : bullets) {
            for (int i = bullet.size() - 1; i >= 0; i--) {
                if (bullet.get(i).destroy()) {
                    bullet.remove(bullet.get(i));
                }
            }
        }

        // enemies AI
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

        //check enemies invasion
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).checkInvasion((ArrayList<AbstractEnemy>) enemies)) {
                towers.get(i).attack(bullets.get(i), towers.get(i).getAttackSpeed());
            }
        }
        // move bullets
        for (List<AbstractBullet> bullet : bullets) {
            for (AbstractBullet b : bullet) {
                b.move();
            }
        }
    }

    public void addTower(AbstractTower tower) {
        if (tiles.get((int) tower.getY() / GameConfig.TILE_SIZE).get((int) tower.getX() / GameConfig.TILE_SIZE) instanceof Road) {
            return;
        }
        towers.add(tower);
        bullets.add(new ArrayList<>());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() >= GameConfig.TILE_SIZE * 17 && e.getX() <= GameConfig.TILE_SIZE * 18
                && e.getY() >= GameConfig.TILE_SIZE * 18 && e.getY() <= GameConfig.TILE_SIZE * 19) {
            mouseFlag = 1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (mouseFlag == 1) {
            NormalTower normalTower = new NormalTower(0, e.getX() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, e.getY() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
            addTower(normalTower);
        }
        mouseFlag = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
