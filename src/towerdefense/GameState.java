package towerdefense;

import towerdefense.entity.Explosion;
import towerdefense.entity.GameEntity;
import towerdefense.entity.bullet.AbstractBullet;
import towerdefense.entity.enemy.AbstractEnemy;
import towerdefense.entity.enemy.NormalEnemy;
import towerdefense.entity.tile.AbstractTile;
import towerdefense.entity.tile.road.Road;
import towerdefense.entity.tile.tower.AbstractTower;
import towerdefense.entity.tile.tower.MachineGunTower;
import towerdefense.entity.tile.tower.NormalTower;
import towerdefense.entity.tile.tower.SniperTower;
import towerdefense.resourcesloader.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameState extends State implements MouseListener {
    private ArrayList<ArrayList<AbstractTile>> tiles;
    private ArrayList<AbstractTower> towers = new ArrayList<>();
    private ArrayList<AbstractEnemy> enemies = new ArrayList<>();
    private ArrayList<ArrayList<AbstractBullet>> bullets = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private ArrayList<Point> wayPoints;

    private int mouseFlag;
    private int invadedEnemy;
    private int money;

    private long start;

    public GameState(GameController gameController) throws FileNotFoundException {
        //gameListener = new GameListener(this);
        super(gameController);
        start = System.nanoTime();
        mouseFlag = 0;
        invadedEnemy = 0;
        money = 100;
        super.gameOver = false;
        tiles = GameStage.loadMap("src/resources/map1.txt");
        wayPoints = GameStage.loadWayPoints("src/resources/map1.txt");
    }

    public void draw(Graphics2D g2d) {
        //draw UI component
        g2d.drawString(Integer.toString(invadedEnemy) + "/5", 1000, 50);
        g2d.drawString(Integer.toString(money), 1000, 100);
        g2d.drawImage(NormalTower.image, 1000, 200, 32, 32, null);
        g2d.drawString(Integer.toString(GameConfig.NORMAL_TOWER_COST), 1050, 225);
        g2d.drawImage(MachineGunTower.image, 1000, 250, 32, 32, null);
        g2d.drawString(Integer.toString(GameConfig.MACHINE_GUN_TOWER_COST), 1050, 275);
        g2d.drawImage(SniperTower.image, 1000, 300, 32, 32, null);
        g2d.drawString(Integer.toString(GameConfig.SNIPER_TOWER_COST), 1050, 325);
        g2d.drawImage(UILoader.pauseButton, GameConfig.SCREEN_WIDTH + 18, GameConfig.SCREEN_HEIGHT - 70, 142, 62, null);
        if (UILoader.isPauseButton) {
            g2d.drawImage(UILoader.pauseButtonClick, GameConfig.SCREEN_WIDTH + 18, GameConfig.SCREEN_HEIGHT - 70, 142, 62, null);
        }

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

        //draw explosion
        for (Explosion explosion : explosions) {
            explosion.draw(g2d);
        }

    }

    @Override
    public void run() {
        long cur = System.nanoTime();
        //check game over
        //spawn enemies
        if ((cur - start) % 5000 == 0 && enemies.size() <= 100) {
            enemies.add(new NormalEnemy(0, 0 * GameConfig.TILE_SIZE, 2 * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, 1));
        }

        //check if enemies is defeated or destroyed
        for (int i = enemies.size() - 1; i >= 0; i--) {
            for (ArrayList<AbstractBullet> bullet : bullets) {
                for (int i1 = bullet.size() - 1; i1 >= 0; i1--) {
                    if (enemies.get(i).injure(bullet.get(i1))) {
                        bullet.remove(i1);
                    }
                }
            }
            if (enemies.get(i).destroy() || enemies.get(i).defeat()) {
                if (enemies.get(i).defeat()) {
                    money += enemies.get(i).getReward();
                    enemies.get(i).explode(explosions);
                }
                if (enemies.get(i).destroy()) {
                    invadedEnemy++;
                }
                enemies.remove(enemies.get(i));
            }
        }

        //delete bullets when fly out the map
        for (List<AbstractBullet> bullet : bullets) {
            for (int i = bullet.size() - 1; i >= 0; i--) {
                if (bullet.get(i).destroy()) {
                    bullet.remove(i);
                }
            }
        }

        //delete explosion when animation has done
        for (int i = explosions.size() - 1; i >= 0; i--) {
            if (explosions.get(i).destroy()) {
                explosions.remove(i);
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

        //
        if (invadedEnemy > 5) {
            super.gameOver = true;
        }
    }

    public boolean addTower(AbstractTower tower) {
        if (tiles.get((int) tower.getY() / GameConfig.TILE_SIZE).get((int) tower.getX() / GameConfig.TILE_SIZE) instanceof Road) {
            return false;
        }
        towers.add(tower);
        bullets.add(new ArrayList<>());
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() >= 1000 && e.getX() <= 1000 + GameConfig.TILE_SIZE
                && e.getY() >= 200 && e.getY() <= 200 + GameConfig.TILE_SIZE) {
            if (money - GameConfig.NORMAL_TOWER_COST >= 0) {
                mouseFlag = 1;
            }
        }
        if (e.getX() >= 1000 && e.getX() <= 1000 + GameConfig.TILE_SIZE
                && e.getY() >= 250 && e.getY() <= 250 + GameConfig.TILE_SIZE) {
            if (money - GameConfig.MACHINE_GUN_TOWER_COST >= 0) {
                mouseFlag = 2;
            }
        }
        if (e.getX() >= 1000 && e.getX() <= 1000 + GameConfig.TILE_SIZE
                && e.getY() >= 300 && e.getY() <= 300 + GameConfig.TILE_SIZE) {
            if (money - GameConfig.SNIPER_TOWER_COST >= 0) {
                mouseFlag = 3;
            }
        }
        if (e.getX() >= GameConfig.SCREEN_WIDTH + 18 && e.getX() <= GameConfig.SCREEN_WIDTH + 160) {
            if(e.getY() >= GameConfig.SCREEN_HEIGHT - 70 && e.getY() <= GameConfig.SCREEN_HEIGHT - 8) {
                UILoader.isPauseButton = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (mouseFlag) {
            case 1: {
                NormalTower normalTower = new NormalTower(0, e.getX() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, e.getY() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                if (addTower(normalTower)) {
                    money -= GameConfig.NORMAL_TOWER_COST;
                }
                break;
            }
            case 2: {
                MachineGunTower machineGunTower = new MachineGunTower(0, e.getX() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, e.getY() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                if (addTower(machineGunTower)) {
                    money -= GameConfig.MACHINE_GUN_TOWER_COST;
                }
                break;
            }
            case 3: {
                SniperTower sniperTower = new SniperTower(0, e.getX() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, e.getY() / GameConfig.TILE_SIZE * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                if (addTower(sniperTower)) {
                    money -= GameConfig.SNIPER_TOWER_COST;
                }
                break;
            }
        }
        mouseFlag = 0;
        if (UILoader.isPauseButton == true) {
            if (e.getX() >= GameConfig.SCREEN_WIDTH + 18 && e.getX() <= GameConfig.SCREEN_WIDTH + 160) {
                if(e.getY() >= GameConfig.SCREEN_HEIGHT - 70 && e.getY() <= GameConfig.SCREEN_HEIGHT - 8) {
                    //gameController.states.pop();
                    gameController.states.push(new PauseState(gameController));
                }
            }
            UILoader.isPauseButton = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
