package towerdefense;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Stack;

public class GameController {
    public Stack<State> states;

    public GameController() throws FileNotFoundException {
        this.states = new Stack<>();
        this.states.push(new MenuState(this));
    }

    public void run() {
        this.states.peek().run();
    }

    public void draw(Graphics2D g2d) {
        this.states.peek().draw(g2d);
    }

//    public void mousePressed(MouseEvent e) {
//        states.peek().mousePressed(e);
//    }
//    public void mouseReleased(MouseEvent e) {
//        states.peek().mouseReleased(e);
//    }
}
