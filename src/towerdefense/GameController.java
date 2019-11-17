package towerdefense;

import towerdefense.state.MenuState;
import towerdefense.state.State;

import java.awt.*;
import java.util.Stack;

public class GameController {
    public Stack<State> states;

    public GameController() {
        this.states = new Stack<>();
        this.states.push(new MenuState(this));
    }

    public void run() {
        if (!this.states.peek().gameOver)
            this.states.peek().run();
        else
            this.states.pop();
    }

    public void draw(Graphics2D g2d) {
        this.states.peek().draw(g2d);
    }
}
