package towerdefense;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import towerdefense.entity.enemy.BossEnemy;
import towerdefense.entity.enemy.NormalEnemy;
import towerdefense.entity.enemy.SmallerEnemy;
import towerdefense.entity.enemy.TankerEnemy;
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
        for (int i = 0; i < waypoints.size(); i++) {
            JSONArray waypoint = (JSONArray) waypoints.get(i);
            gameState.getWayPoints().add(new Point(Integer.parseInt(waypoint.get(0).toString()), Integer.parseInt(waypoint.get(1).toString())));
        }
        //parse towers
        JSONArray towers = (JSONArray) result.get("towers");
        for (int i = 0; i < towers.size(); i++) {
            JSONObject tower = (JSONObject) towers.get(i);
            switch (tower.get("type").toString()) {
                case "normal":
                    gameState.getTowers().add(new NormalTower(0, Integer.parseInt(tower.get("x").toString()), Integer.parseInt(tower.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE));
                    break;
                case "machine":
                    gameState.getTowers().add(new MachineGunTower(0, Integer.parseInt(tower.get("x").toString()), Integer.parseInt(tower.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE));
                    break;
                case "sniper":
                    gameState.getTowers().add(new SniperTower(0, Integer.parseInt(tower.get("x").toString()), Integer.parseInt(tower.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE));
                    break;
            }
            gameState.getBullets().add(new ArrayList<>());
        }
        gameState.setMoney(Integer.parseInt(result.get("money").toString()));
        gameState.setTick((Long) result.get("tick"));
        JSONArray waves = (JSONArray) result.get("waves");
        for (int i = 0; i < waves.size(); i++) {
            JSONObject wave = (JSONObject) waves.get(i);

            if (Integer.parseInt(wave.get("id").toString()) == gameState.getWave()) {
                JSONArray enemies = (JSONArray) wave.get("enemies");
                for (int j = 0; j < enemies.size(); j++) {
                    JSONObject enemy = (JSONObject) enemies.get(j);
                    switch (enemy.get("type").toString()) {
                        case "normal":
                            System.out.println(1);
                            gameState.getEnemies().add(new NormalEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            System.out.println(gameState.getEnemies().get(j).getCreatedTick());
                            break;
                        case "smaller":
                            gameState.getEnemies().add(new SmallerEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            break;
                        case "tanker":
                            gameState.getEnemies().add(new TankerEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            break;
                        case "boss":
                            gameState.getEnemies().add(new BossEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            break;
                    }
                }
            }
        }
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
            tower.put("x", (int) gameState.getTowers().get(i).getX());
            tower.put("y", (int) gameState.getTowers().get(i).getY());
            towers.add(tower);
        }
        result.put("towers", towers);
        //save enemies
        JSONArray waves = new JSONArray();
        JSONObject wave = new JSONObject();
        JSONArray enemies = new JSONArray();
        for (int i = 0; i < gameState.getEnemies().size(); i++) {
            JSONObject enemy = new JSONObject();
            if (gameState.getEnemies().get(i) instanceof NormalEnemy) {
                enemy.put("type", "normal");
            } else if (gameState.getEnemies().get(i) instanceof SmallerEnemy) {
                enemy.put("type", "smaller");
            } else if (gameState.getEnemies().get(i) instanceof TankerEnemy) {
                enemy.put("type", "tanker");
            } else if (gameState.getEnemies().get(i) instanceof BossEnemy) {
                enemy.put("type", "boss");
            }
            enemy.put("createdtick", gameState.getEnemies().get(i).getCreatedTick());
            enemy.put("x", (int) gameState.getEnemies().get(i).getX());
            enemy.put("y", (int) gameState.getEnemies().get(i).getY());
            enemy.put("flag", gameState.getEnemies().get(i).getFlag());
            enemies.add(enemy);
        }
        wave.put("enemies", enemies);
        wave.put("id", gameState.getWave());
        waves.add(wave);
        result.put("waves", waves);

        result.put("money", gameState.getMoney());
        result.put("tick", gameState.getTick());

        try (FileWriter file = new FileWriter(filepath)) {
            file.write(result.toJSONString());
            file.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadWave(GameState gameState, String filepath) {
        JSONParser jsonParser = new JSONParser();
        JSONObject result = new JSONObject();
        try (FileReader reader = new FileReader(filepath)) {
            Object obj = jsonParser.parse(reader);
            result = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONArray waves = (JSONArray) result.get("waves");
        for (int i = 0; i < waves.size(); i++) {
            JSONObject wave = (JSONObject) waves.get(i);
            if (Integer.parseInt(wave.get("id").toString()) == gameState.getWave()) {
                JSONArray enemies = (JSONArray) wave.get("enemies");
                for (int j = 0; j < enemies.size(); j++) {
                    JSONObject enemy = (JSONObject) enemies.get(j);
                    switch (enemy.get("type").toString()) {
                        case "normal":
                            System.out.println(1);
                            gameState.getEnemies().add(new NormalEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            System.out.println(gameState.getEnemies().get(j).getCreatedTick());
                            break;
                        case "smaller":
                            gameState.getEnemies().add(new SmallerEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            break;
                        case "tanker":
                            gameState.getEnemies().add(new TankerEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            break;
                        case "boss":
                            gameState.getEnemies().add(new BossEnemy((Long) enemy.get("createdtick"), Integer.parseInt(enemy.get("x").toString()), Integer.parseInt(enemy.get("y").toString()), GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, Integer.parseInt(enemy.get("flag").toString())));
                            break;
                    }

                }
            }
        }
    }
}
