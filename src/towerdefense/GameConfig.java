package towerdefense;

import java.awt.*;
import java.util.ArrayList;

public final class GameConfig {
    public static final String GAME_NAME = "Tower Defense";
    public static final int TILE_SIZE = 32;
    public static final int TILE_HORIZONTAL = 30;
    public static final int TILE_VERTICAL = 20;
    public static final int TILE_COUNT = TILE_HORIZONTAL*TILE_VERTICAL;
    public static final int SCREEN_WIDTH = TILE_HORIZONTAL*TILE_SIZE;
    public static final int SCREEN_HEIGHT = TILE_VERTICAL*TILE_SIZE;


    public static final int GAME_TPS = 30;
    public static final long GAME_NSPT = Math.round(1000000000.0 / GAME_TPS);
    //Other config related to other entities in the game.

    public static final int FLARE_TICK_DOWN = GAME_TPS / 2;
    public static final int FLARE_TICK_SPREAD = FLARE_TICK_DOWN - GAME_TPS / 15;
    public static final float PLAYER_SPEED = 5.0f / GAME_TPS;

}
