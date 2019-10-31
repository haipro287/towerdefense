package towerdefense;

import towerdefense.drawer.GameDrawer;
import towerdefense.drawer.tile.RoadDrawer;
import towerdefense.entity.GameEntity;
import towerdefense.entity.tile.road.Road;
import towerdefense.entity.tile.tower.NormalTower;
import towerdefense.listener.GameListener;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameField {
    private GameStage gameStage;
    private List<GameEntity> gameEntities;
    private GameDrawer gameDrawer;
    private GameListener gameListener;

    public GameField() throws FileNotFoundException {
        gameEntities = GameStage.loadMap("src/resources/map1.txt");
        gameEntities.add(new NormalTower(0, 17*GameConfig.TILE_SIZE, 17*GameConfig.TILE_SIZE,32*2, 32*2));
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public GameDrawer getGameDrawer() {
        return gameDrawer;
    }

    public void setGameDrawer(GameDrawer gameDrawer) {
        this.gameDrawer = gameDrawer;
    }

    public GameListener getGameListener() {
        return gameListener;
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public void draw(Graphics2D g2d) {
        for (GameEntity entity : gameEntities) {
            entity.getX();
            entity.draw(g2d);
        }
    }
}
