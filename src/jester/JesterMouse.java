package jester;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

/**
 * JesterMouse provides mouse position and button input tracking for the Jester engine.
 * It tracks current mouse coordinates and which mouse buttons are held down.
 * This class is responsible for handling mouse events and providing access to mouse state.
 */
public class JesterMouse implements MouseListener, MouseMotionListener {

    // Current mouse x and y coordinates
    private static int x = 0;
    private static int y = 0;

    // Set of currently pressed mouse buttons
    private static final HashSet<Integer> buttonsDown = new HashSet<>();

    /**
     * Called when a mouse button is pressed.
     * Adds the button to the set of currently held buttons.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        buttonsDown.add(e.getButton());
    }

    /**
     * Called when a mouse button is released.
     * Removes the button from the set of held buttons.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        buttonsDown.remove(e.getButton());
    }

    /**
     * Called when the mouse is moved (without holding buttons).
     * Updates the current mouse position.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Called when the mouse is moved while a button is held down.
     * Also updates the mouse position.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Gets the current x coordinate of the mouse.
     * @return current x position
     */
    public static synchronized int getX() {
        return x;
    }

    /**
     * Gets the current y coordinate of the mouse.
     * @return current y position
     */
    public static synchronized int getY() {
        return y;
    }

    /**
     * Checks if a given mouse button is currently being held down.
     * @param button the button to check (e.g., MouseEvent.BUTTON1)
     * @return true if the button is down
     */
    public static synchronized boolean isDown(int button) {
        return buttonsDown.contains(button);
    }

    /**
     * Resets the mouse state at the beginning of each frame.
     * This clears the set of pressed buttons.
     */
    public static synchronized void reset() {
        buttonsDown.clear();
    }

    // These events are not used but must be implemented
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
