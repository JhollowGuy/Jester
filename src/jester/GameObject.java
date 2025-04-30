package jester;

import java.util.function.Consumer;

/**
 * Represents a generic game object with position, size, and visibility.
 * This class serves as a base for all game entities in the Jester framework.
 */
public class GameObject {
    protected SignalEmitter signals = new SignalEmitter(); // Signal emitter for this game object
    protected float x, y; // Position of the game object
    protected float width, height; // Size of the game object
    protected boolean visible = true; // Visibility status of the game object

    /**
     * Constructs a new GameObject with the specified position and size.
     * @param x The x-coordinate of the game object.
     * @param y The y-coordinate of the game object.
     * @param width The width of the game object.
     * @param height The height of the game object.
     */
    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Initializes the game object. This method can be overridden in subclasses.
     */
    public void initialize() {
        // Override this in subclasses if needed
    }

    /**
     * Updates the game object's state (e.g., movement, animation).
     * This method should be overridden in subclasses to implement specific logic.
     * @param dt The time delta since the last update.
     */
    public void update(float dt) {
        // Override this in subclasses
    }

    /**
     * Renders the game object using the specified graphics context.
     * This method should be overridden in subclasses to implement specific rendering logic.
     *
     * @param g      The graphics context used for rendering.
     * @param camera
     */
    public void render(JesterGraphics g, JesterCamera camera) {
        // Override this in subclasses
    }

    /**
     * Checks if the game object is visible.
     * @return true if the game object is visible, false otherwise.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the visibility status of the game object.
     * @param visible The new visibility status.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // Getters and setters for position and size

    /**
     * Gets the x-coordinate of the game object.
     * @return The x-coordinate.
     */
    public float getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the game object.
     * @return The y-coordinate.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the game object.
     * @param x The new x-coordinate.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the game object.
     * @param y The new y-coordinate.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Gets the width of the game object.
     * @return The width.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets the height of the game object.
     * @return The height.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the width of the game object.
     * @param width The new width.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Sets the height of the game object.
     * @param height The new height.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Subscribes a callback to a specific signal for this game object.
     * @param signalName The name of the signal to listen for.
     * @param callback The callback to invoke when the signal is emitted.
     */
    public void onSignal(String signalName, Consumer<Object> callback) {
        signals.on(signalName, callback);
    }

    /**
     * Emits a signal, invoking all subscribed callbacks with the provided data.
     * @param signalName The name of the signal to emit.
     * @param data The data to pass to the callbacks.
     */
    public void emitSignal(String signalName, Object data) {
        signals.emit(signalName, data);
    }

    // Additional methods for transformations, collision detection, etc. can be added here
}