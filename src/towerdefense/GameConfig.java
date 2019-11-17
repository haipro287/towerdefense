package towerdefense;

import java.awt.*;
import java.util.ArrayList;

public final class GameConfig {
    public static final String GAME_NAME = "Tower Defense";
    public static final int TILE_SIZE = 32;
    public static final int TILE_HORIZONTAL = 30;
    public static final int TILE_VERTICAL = 20;
    public static final int TILE_COUNT = TILE_HORIZONTAL * TILE_VERTICAL;
    public static final int SCREEN_WIDTH = TILE_HORIZONTAL * TILE_SIZE;
    public static final int SCREEN_HEIGHT = TILE_VERTICAL * TILE_SIZE;

    //game fps config
    public static final int GAME_FPS = 60;
    public static final long GAME_NSPF = Math.round(1000000000.0 / GAME_FPS);

    //game tps config
    public static final int GAME_TPS = 120;
    public static final int GAME_FPT = GAME_FPS / GAME_TPS;
    public static final long GAME_NSPT = Math.round(1000000000.0 / GAME_TPS);

//    public static final int FLARE_TICK_DOWN = GAME_FPS / 2;
//    public static final int FLARE_TICK_SPREAD = FLARE_TICK_DOWN - GAME_FPS / 15;


    public static final int NORMAL_TOWER_COST = 50;
    public static final int NORMAL_TOWER_RADIUS = 250;
    public static final int NORMAL_TOWER_ATTACK_SPEED = 500;

    public static final int SNIPER_TOWER_COST = 200;
    public static final int SNIPER_TOWER_RADIUS = 400;
    public static final int SNIPER_TOWER_ATTACK_SPEED = 2000;

    public static final int MACHINE_GUN_TOWER_COST = 100;
    public static final int MACHINE_GUN_TOWER_RADIUS = 200;
    public static final int MACHINE_GUN_TOWER_ATTACK_SPEED = 100;

    public static final double NORMAL_BULLET_SPEED = 400.0 / GAME_TPS;
    public static final int NORMAL_BULLET_DAMAGE = 50;

    public static final double SNIPER_BULLET_SPEED = 700.0 / GAME_TPS;
    public static final int SNIPER_BULLET_DAMAGE = 200;

    public static final double MACHINE_GUN_BULLET_SPEED = 400.0 / GAME_TPS;
    public static final int MACHINE_GUN_BULLET_DAMAGE = 10;



    public static final int NORMAL_ENEMY_HP = 100;
    public static final double NORMAL_ENEMY_SPEED = 100.0 / GAME_TPS;
    public static final int NORMAL_ENEMY_ARMOR = 20;
    public static final int NORMAL_ENEMY_REWARD = 5;

    public static final int SMALLER_ENEMY_HP = 50;
    public static final double SMALLER_ENEMY_SPEED = 150 / GAME_TPS;
    public static final int SMALLER_ENEMY_ARMOR = 10;
    public static final int SMALLER_ENEMY_REWARD = 15;

    public static final int TANKER_ENEMY_HP = 500;
    public static final double TANKER_ENEMY_SPEED = 50 / GAME_TPS;
    public static final int TANKER_ENEMY_ARMOR = 50;
    public static final int TANKER_ENEMY_REWARD = 30;

    public static final int BOSS_ENEMY_HP = 5000;
    public static final double BOSS_ENEMY_SPEED = 10 / GAME_TPS;
    public static final int BOSS_ENEMY_ARMOR = 100;
    public static final int BOSS_ENEMY_REWARD = 30;

}
