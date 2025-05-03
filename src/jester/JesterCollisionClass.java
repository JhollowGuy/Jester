package jester;

import java.util.*;

/**
 * Represents a collision class that can be assigned to colliders.
 * Used to control which objects can collide with each other.
 * For example, you might want bullets to pass through other bullets
 * but collide with enemies.
 */
public class JesterCollisionClass {
    private String name;
    private Set<String> ignores;

    /**
     * Creates a new collision class
     * @param name The name of this collision class (e.g., "Player", "Enemy", "Bullet")
     */
    public JesterCollisionClass(String name) {
        this.name = name;
        this.ignores = new HashSet<>();
    }

    /**
     * Makes this collision class ignore another class
     * @param otherClass The name of the class to ignore
     */
    public void ignore(String otherClass) {
        ignores.add(otherClass);
    }

    /**
     * Checks if this class should ignore collisions with another class
     * @param otherClass The name of the class to check
     * @return true if collisions should be ignored
     */
    public boolean shouldIgnore(String otherClass) {
        return ignores.contains(otherClass);
    }

    /**
     * Gets the name of this collision class
     * @return The collision class name
     */
    public String getName() {
        return name;
    }
}