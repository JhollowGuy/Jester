package jester;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class JesterKeyboard implements KeyListener {

    private static final HashSet<Integer> keysDown = new HashSet<>();
    private static final HashSet<Integer> keysPressed = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!keysDown.contains(key)) {
            keysPressed.add(key);  // Only register as "pressed" once
        }
        keysDown.add(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keysDown.remove(key);
        keysPressed.remove(key);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    public static boolean isDown(int keyCode) {
        return keysDown.contains(keyCode);
    }

    public static boolean isPressed(int keyCode) {
        return keysPressed.contains(keyCode);
    }

    // ⚠️ Call this at the end of each frame in the engine to clear pressed states
    public static void endFrame() {
        keysPressed.clear();
    }
}
