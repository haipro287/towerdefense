package towerdefense;

import towerdefense.state.MenuState;
import towerdefense.state.State;
import towerdefense.resourcesloader.SoundLoader;

import java.awt.*;
import java.util.Stack;

public class GameController {
    public Stack<State> states;

    public GameController() {
        this.states = new Stack<>();
        this.states.push(new MenuState(this));
        SoundLoader.play(SoundLoader.inGameSFX);
    }

    public void run() {
        this.states.peek().run();
    }

    public void draw(Graphics2D g2d) {
        this.states.peek().draw(g2d);
    }
}
