package towerdefense;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import towerdefense.entity.tile.mountain.Mountain;
import towerdefense.entity.tile.road.Road;
import towerdefense.entity.tile.road.Spawner;
import towerdefense.entity.tile.road.Target;
import towerdefense.entity.tile.tower.MachineGunTower;
import towerdefense.entity.tile.tower.NormalTower;
import towerdefense.entity.tile.tower.SniperTower;
import towerdefense.state.GameState;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameStage {
    public static void load(GameState gameState, String filepath) {
        JSONParser jsonParser = new JSONParser();
        JSONObject result = new JSONObject();
        try (FileReader reader = new FileReader(filepath)) {
            Object obj = jsonParser.parse(reader);
            result = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        //parse tiles
        JSONArray tiles = (JSONArray) result.get("tiles");
        for (int i = 0; i < tiles.size(); i++) {
            gameState.getTiles().add(new ArrayList<>());
            JSONArray tile = (JSONArray) tiles.get(i);
            for (int j = 0; j < tile.size(); j++) {
                switch (Integer.parseInt(tile.get(j).toString())) {
                    case 0: {
                        gameState.getTiles().get(i).add(new Mountain(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 1: {
                        gameState.getTiles().get(i).add(new Road(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 2: {
                        gameState.getTiles().get(i).add(new Spawner(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                    case 3: {
                        gameState.getTiles().get(i).add(new Target(0, j * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE, 1 * GameConfig.TILE_SIZE));
                        break;
                    }
                }
            }
        }
        //parse waypoints
        JSONArray waypoints = (JSONArray) result.get("waypoints");
        System.out.println(waypoints.size());
        for (int i = 0; i < waypoints.size(); i++) {
            JSONArray waypoint = (JSONArray) waypoints.get(i);
            gameState.getWayPoints().add(new Point(Integer.parseInt(waypoint.get(0).toString()), Integer.parseInt(waypoint.get(1).toString())));
        }
        //parse towers
        JSONArray towers = (JSONArray) result.get("towers");
        System.out.println(towers.size());
        for (int i = 0; i < towers.size(); i++) {
            JSONObject tower = (JSONObject) towers.get(i);
            switch (tower.get("type").toString()) {
                case "normal":
                    gameState.getTowers().add(new NormalTower(0, Integer.parseInt(tower.get("x").toString()), Integer.parseInt(tower.get("x").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE));
                    break;
                case "machine":
                    gameState.getTowers().add(new MachineGunTower(0, Integer.parseInt(tower.get("x").toString()), Integer.parseInt(tower.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE));
                    break;
                case "sniper":
                    gameState.getTowers().add(new SniperTower(0, Integer.parseInt(tower.get("x").toString()), Integer.parseInt(tower.get("x").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE));
                    break;
            }
            gameState.getBullets().add(new ArrayList<>());
        }
        gameState.setMoney(Integer.parseInt(result.get("money").toString()));
        gameState.setTick((Long) result.get("tick"));
    }

    public static void save(GameState gameState, String filepath) {
        JSONObject result = new JSONObject();
        //save tiles
        JSONArray tiles = new JSONArray();
        for (int i = 0; i < gameState.getTiles().size(); i++) {
            JSONArray tile = new JSONArray();
            for (int j = 0; j < gameState.getTiles().get(i).size(); j++) {
                if (gameState.getTiles().get(i).get(j) instanceof Spawner) {
                    tile.add(2);
                } else if (gameState.getTiles().get(i).get(j) instanceof Target) {
                    tile.add(3);
                } else if (gameState.getTiles().get(i).get(j) instanceof Road) {
                    tile.add(1);
                } else if (gameState.getTiles().get(i).get(j) instanceof Mountain) {
                    tile.add(0);
                }
            }
            tiles.add(tile);
        }
        result.put("tiles", tiles);
        //save waypoints
        JSONArray waypoints = new JSONArray();
        for (int i = 0; i < gameState.getWayPoints().size(); i++) {
            JSONArray waypoint = new JSONArray();
            waypoint.add(gameState.getWayPoints().get(i).x);
            waypoint.add(gameState.getWayPoints().get(i).y);
            waypoints.add(waypoint);
        }
        result.put("waypoints", waypoints);
        //save towers
        JSONArray towers = new JSONArray();
        for (int i = 0; i < gameState.getTowers().size(); i++) {
            JSONObject tower = new JSONObject();
            if (gameState.getTowers().get(i) instanceof NormalTower) {
                tower.put("type", "normal");
            }
            if (gameState.getTowers().get(i) instanceof MachineGunTower) {
                tower.put("type", "machine");
            }
            if (gameState.getTowers().get(i) instanceof SniperTower) {
                tower.put("type", "sniper");
            }
            tower.put("x", gameState.getTowers().get(i).getX());
            tower.put("y", gameState.getTowers().get(i).getY());
            towers.add(tower);
        }
        result.put("towers", towers);

        try (FileWriter file = new FileWriter(filepath)) {
            file.write(result.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
