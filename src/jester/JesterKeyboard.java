package jester;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class JesterKeyboard implements KeyListener {

    private static final HashSet<Integer> keysDown = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        keysDown.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysDown.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    public static boolean isDown(int keyCode) {
        return keysDown.contains(keyCode);
    }
}
