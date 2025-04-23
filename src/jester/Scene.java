package jester;

/**
 * The base class for all scenes.
 * Developers extend this to build their game's logic and visuals.
 */
public abstract class Scene {

    /**
     * Called once when the scene starts.
     * Use this for loading resources, setting up variables, etc.
     */
    public void init() {
        // Optional
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
}
