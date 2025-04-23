package jester;

public class GameObject {
    protected float x, y; // position
    protected float width, height;
    protected boolean visible = true;

    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Update logic (like movement, animation, etc.)
    public void update(float dt) {
        // Override this in subclasses
    }

    // Render the object
    public void render(JesterGraphics g) {
        // Override this in subclasses
    }

    // Check if visible
    public boolean isVisible() {
        return visible;
    }

    // Set visibility
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // Getters and setters for position and size
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public void setWidth(float width) { this.width = width; }
    public void setHeight(float height) { this.height = height; }
}
