package jester;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of GameObjects in the Jester framework.
 * This class provides methods to add, update, render, and clear game objects.
 */
public class GameObjectManager {
    private final List<GameObject> objects = new ArrayList<>();

    /**
     * Adds a GameObject to the manager.
     *
     * @param obj The GameObject to add.
     */
    public void add(GameObject obj) {
        objects.add(obj);
    }

    /**
     * Updates all GameObjects in the manager.
     *
     * @param dt The delta time since the last update.
     */
    public void update(float dt) {
        for (GameObject obj : objects) {
            obj.update(dt);
        }
    }

    /**
     * Renders all GameObjects in the manager using the specified graphics context.
     */
    public void render(JesterGraphics g, JesterCamera camera) {
        //JesterGraphics.applyFilter(g.getGraphics()); // Apply once before drawing everything
        for (GameObject obj : objects) {
            obj.render(g, camera); // Pass the camera to each object's render method
        }
    }

    /**
     * Clears all GameObjects from the manager.
     */
    public void clear() {
        objects.clear();
    }

    /**
     * Removes a GameObject from the manager.
     *
     * @param obj The GameObject to remove.
     * @return true if the object was removed, false otherwise.
     */
    public boolean remove(GameObject obj) {
        return objects.remove(obj);
    }

    /**
     * Gets the number of GameObjects managed by this manager.
     *
     * @return The number of GameObjects.
     */
    public int size() {
        return objects.size();
    }
}
