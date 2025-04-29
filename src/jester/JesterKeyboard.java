package jester;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JesterKeyboard manages real-time key input for the Jester framework.
 * It tracks keys that are held down and keys that were pressed in the current frame.
 */
public class JesterKeyboard implements KeyListener {

    // Set of keys currently being held down
    // private static final HashSet<Integer> keysDown = new HashSet<>();
    private static final Set<Integer> keysDown = ConcurrentHashMap.newKeySet();

    // Set of keys that were just pressed this frame
    // private static final HashSet<Integer> keysPressed = new HashSet<>();
    private static final Set<Integer> keysPressed = ConcurrentHashMap.newKeySet();

    /**
     * Triggered when a key is pressed.
     * If it wasn't already held, mark it as pressed for this frame.
     *
     * @param e The KeyEvent containing information about the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!keysDown.contains(key)) {
            keysPressed.add(key);  // Only register as "pressed" once
        }
        keysDown.add(key); // Track that it's held
    }

    /**
     * Triggered when a key is released.
     * Removes it from both the down and pressed sets.
     *
     * @param e The KeyEvent containing information about the key release.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keysDown.remove(key);
        keysPressed.remove(key);
    }

    /**
     * Not used, but required by KeyListener.
     *
     * @param e The KeyEvent containing information about the key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    /**
     * Checks if a key is currently being held down.
     *
     * @param keyCode The key code to check.
     * @return true if the key is held down, false otherwise.
     */
    public static boolean isDown(int keyCode) {
        return keysDown.contains(keyCode);
    }

    /**
     * Checks if a key was just pressed in the current frame.
     *
     * @param keyCode The key code to check.
     * @return true if the key was pressed this frame, false otherwise.
     */
    public static boolean isPressed(int keyCode) {
        return keysPressed.contains(keyCode);
    }

    /**
     * Call this method at the end of each frame to clear all one-time presses.
     * This method must be called once per game loop iteration.
     */
    public static void endFrame() {
        keysPressed.clear();
    }
}
