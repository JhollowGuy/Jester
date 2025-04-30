package jester;

/**
 * The base class for all scenes in the Jester framework.
 * Developers extend this class to build their game's logic and visuals.
 */
public abstract class Scene {

    /**
     * Called once when the scene starts.
     * Use this for loading resources, setting up variables, etc.
     */
    public void init() {
        // Optional: Override this method in subclasses for initialization
    }

    /**
     * Called every frame (~60 times per second) to update game logic.
     * @param dt Delta time (in seconds) since the last frame.
     */
    public abstract void update(float dt);

    /**
     * Called every frame to draw things on screen.
     */
    public abstract void render();

    /**
     * Called when the scene is paused.
     * Override this method in subclasses to handle pause behavior.
     */
    public void pause() {
        // Optional: Override this method in subclasses
    }

    /**
     * Called when the scene is resumed.
     * Override this method in subclasses to handle resume behavior.
     */
    public void resume() {
        // Optional: Override this method in subclasses
    }

    /**
     * Called when the scene is disposed or removed.
     * Override this method in subclasses to handle cleanup.
     */
    public void dispose() {
        // Optional: Override this method in subclasses
    }

    /**
     * Called to handle input events specific to this scene.
     * @param keyCode The key code of the pressed/released key.
     * @param pressed True if the key is pressed, false if released.
     */
    public void handleInput(int keyCode, boolean pressed) {
        // Optional: Override this method in subclasses for input handling
    }
}
