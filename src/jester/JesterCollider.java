package jester;

import java.awt.Color;

/**
 * A simple physics collider for basic game objects.
 * Handles basic movement, gravity, and collision detection.
 */
public class JesterCollider {
    /** The types of colliders available */
    public enum Type {
        RECTANGLE,
        CIRCLE
    }

    /** The body types that determine physics behavior */
    public enum BodyType {
        STATIC,    // Immovable (platforms, walls)
        RIGID,     // Affected by physics, can be pushed (boxes)
        KINEMATIC  // Moves programmatically, not affected by gravity (moving platforms)
    }

    private JesterPhysics world;
    private Type type;
    private BodyType bodyType;
    private float x, y;
    private float width, height;
    private float radius;
    private float velocityX, velocityY;
    private String collisionClass = "Default";
    private float mass = 1.0f;  // For pushing mechanics

    /**
     * Creates a new collider
     * @param world The physics world this collider belongs to
     * @param type The type of collider (RECTANGLE or CIRCLE)
     * @param x Initial X position
     * @param y Initial Y position
     * @param params Additional parameters (width,height for RECTANGLE, radius for CIRCLE)
     */
    public JesterCollider(JesterPhysics world, Type type, float x, float y, float... params) {
        this.world = world;
        this.type = type;
        this.x = x;
        this.y = y;
        this.bodyType = BodyType.RIGID; // Default to RIGID
        this.velocityX = 0;
        this.velocityY = 0;

        if (type == Type.RECTANGLE) {
            this.width = params[0];
            this.height = params[1];
        } else if (type == Type.CIRCLE) {
            this.radius = params[0];
            this.width = this.height = radius * 2;
        }
    }

    /**
     * Sets the collision class for this collider
     * @param className The name of the collision class
     */
    public void setCollisionClass(String className) {
        this.collisionClass = className;
    }

    /**
     * Gets the collision class of this collider
     * @return The collision class name
     */
    public String getCollisionClass() {
        return collisionClass;
    }

    /**
     * Updates the collider's position based on physics
     * @param dt Delta time since last update
     */
    public void update(float dt) {
        if (bodyType != BodyType.RIGID) return;

        // Apply gravity
        velocityY += world.getGravityY() * dt;

        // Update position
        x += velocityX * dt;
        y += velocityY * dt;
    }

    /**
     * Apply a force to push the object
     * @param forceX The force in the X direction
     * @param forceY The force in the Y direction
     */
    public void push(float forceX, float forceY) {
        if (bodyType != BodyType.RIGID) return;

        velocityX += forceX / mass;
        velocityY += forceY / mass;
    }

    /**
     * Sets the body type of this collider
     * @param type The body type
     */
    public void setBodyType(BodyType type) {
        this.bodyType = type;
    }

    /**
     * Sets the mass of the object (affects pushing)
     * @param mass The mass
     */
    public void setMass(float mass) {
        this.mass = Math.max(0.1f, mass);
    }

    /**
     * Draws the collider (for debugging)
     * @param g The graphics context
     */
    public void draw(JesterGraphics g) {
        g.setColor(Color.GREEN);
        if (type == Type.RECTANGLE) {
            g.drawRect("line", (int)x, (int)y, (int)width, (int)height, Color.GREEN);
        } else if (type == Type.CIRCLE) {
            g.drawCircle((int)(x + radius), (int)(y + radius), (int)radius, Color.GREEN);
        }
    }

    // Getters and setters
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getVelocityY() { return velocityY; }
    public void setVelocityY(float vy) { this.velocityY = vy; }
    public BodyType getBodyType() { return bodyType; }
    public float getMass() { return mass; }
    public float getVelocityX() { return velocityX; }
    public void setVelocityX(float vx) { velocityX = vx; }
}