package jester;

/**
 * JesterInput provides global static access to input handling classes.
 *
 * It holds instances of JesterKeyboard and JesterMouse,
 * allowing developers to easily check input states such as key presses and mouse actions.
 *
 * Example usage:
 * if (Jester.input.keyboard.isDown(JesterKeys.DOWN)) {
 *     // Jump or perform an action
 * }
 */
public class JesterInput {
    /**
     * Static instance of the keyboard input handler.
     * Handles key press/release state checking.
     */
    public static final JesterKeyboard keyboard = initializeKeyboard();
    /**
     * Static instance of the mouse input handler.
     * Handles mouse position, clicks, and other mouse events.
     */
    public static final JesterMouse mouse = initializeMouse();

    /**
     * Initializes the JesterKeyboard instance.
     * @return A new instance of JesterKeyboard.
     * @throws IllegalStateException if initialization fails.
     */
    private static JesterKeyboard initializeKeyboard() {
        JesterKeyboard kb = new JesterKeyboard();
        // Add checks to ensure kb is initialized properly
        if (kb == null) {
            throw new IllegalStateException("Failed to initialize JesterKeyboard.");
        }
        return kb;
    }

    /**
     * Initializes the JesterMouse instance.
     * @return A new instance of JesterMouse.
     * @throws IllegalStateException if initialization fails.
     */
    private static JesterMouse initializeMouse() {
        JesterMouse mouse = new JesterMouse();
        // Add checks to ensure mouse is initialized properly
        if (mouse == null) {
            throw new IllegalStateException("Failed to initialize JesterMouse.");
        }
        return mouse;
    }

    // Example of synchronized access for thread safety
    public static synchronized JesterKeyboard getKeyboard() {
        return keyboard;
    }

    public static synchronized JesterMouse getMouse() {
        return mouse;
    }
}
