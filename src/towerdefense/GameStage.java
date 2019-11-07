package towerdefense;

import towerdefense.entity.GameEntity;
import towerdefense.entity.tile.mountain.Mountain;
import towerdefense.entity.tile.road.Road;
import towerdefense.entity.tile.road.Spawner;
import towerdefense.entity.tile.road.Target;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameStage {
    public static List<GameEntity> loadMap(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        List<GameEntity> gameEntities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                int temp = sc.nextInt();
                switch (temp) {
                    case 0: {
                        gameEntities.add(new Mountain(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 1: {
                        gameEntities.add(new Road(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 2: {
                        gameEntities.add(new Spawner(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 3: {
                        gameEntities.add(new Target(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                }
            }
        }
        return gameEntities;
    }

    public static List<Point> loadWayPoints(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        List<Point> wayPoints = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            sc.nextLine();
        }
        while (sc.hasNext()) {
            wayPoints.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        return wayPoints;
    }
}
