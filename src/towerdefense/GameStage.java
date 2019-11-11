package towerdefense;

import towerdefense.entity.GameEntity;
import towerdefense.entity.tile.AbstractTile;
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
    public static ArrayList<ArrayList<AbstractTile>> loadMap(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        ArrayList<ArrayList<AbstractTile>> tiles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            tiles.add(new ArrayList<>());
            for (int j = 0; j < 30; j++) {
                int temp = sc.nextInt();
                switch (temp) {
                    case 0: {
                        tiles.get(i).add(new Mountain(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 1: {
                        tiles.get(i).add(new Road(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 2: {
                        tiles.get(i).add(new Spawner(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 3: {
                        tiles.get(i).add(new Target(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                }
            }
        }
        return tiles;
    }

    public static ArrayList<Point> loadWayPoints(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        ArrayList<Point> wayPoints = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            sc.nextLine();
        }
        while (sc.hasNext()) {
            wayPoints.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        return wayPoints;
    }
}
