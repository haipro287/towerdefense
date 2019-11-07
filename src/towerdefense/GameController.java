package towerdefense;

import java.util.Stack;

public class GameController {
    Stack<State> states;

    public GameController() {
        this.states = new Stack<>();
    }
}
