package jester;

/**
 * Represents a 2D vector with various mathematical operations.
 */
public class JesterVector2 {
    private final double x;
    private final double y;

    // Constants
    public static final JesterVector2 ZERO = new JesterVector2(0, 0);
    public static final JesterVector2 ONE = new JesterVector2(1, 1);
    public static final JesterVector2 UP = new JesterVector2(0, -1);
    public static final JesterVector2 DOWN = new JesterVector2(0, 1);
    public static final JesterVector2 LEFT = new JesterVector2(-1, 0);
    public static final JesterVector2 RIGHT = new JesterVector2(1, 0);

    /**
     * Constructs a new JesterVector2 instance.
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     */
    public JesterVector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the vector.
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the vector.
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Normalizes the vector (returns a new vector).
     * Get a vector of length 1 pointing in same direction
     * @return A new vector with a length of 1 pointing in the same direction.
     */
    public JesterVector2 normalized() {
        double length = length();
        return length > 0 ? new JesterVector2(x / length, y / length) : ZERO;
    }

    /**
     * Gets the magnitude (length) of the vector.
     * @return The length of the vector.
     */
    public double length() {
        return Math.sqrt(length_squared());
    }

    /**
     * Gets the squared length of the vector (faster).
     * @return The squared length of the vector.
     */
    public double length_squared() {
        return x * x + y * y;
    }

    /**
     * Gets the angle of the vector in radians.
     * @return The angle of the vector.
     */
    public double angle() {
        return Math.atan2(y, x);
    }

    /**
     * Gets the angle between this vector and another vector.
     * @param other The other vector.
     * @return The angle in radians between the two vectors.
     */
    public double angle_to(JesterVector2 other) {
        return Math.atan2(other.y, other.x) - angle();
    }

    /**
     * Gets the angle to a specific point.
     * @param point The target point.
     * @return The angle in radians to the point.
     */
    public double angle_to_point(JesterVector2 point) {
        return angle_to(point);
    }

    /**
     * Calculates the dot product with another vector.
     * @param other The other vector.
     * @return The dot product.
     */
    public double dot(JesterVector2 other) {
        return x * other.x + y * other.y;
    }

    /**
     * Calculates the cross product with another vector (scalar in 2D).
     * @param other The other vector.
     * @return The scalar cross product.
     */
    public double cross(JesterVector2 other) {
        return x * other.y - y * other.x;
    }

    /**
     * Gets the absolute value vector.
     * @return A new vector with absolute values of x and y.
     */
    public JesterVector2 abs() {
        return new JesterVector2(Math.abs(x), Math.abs(y));
    }

    /**
     * Rotates the vector by a given angle in radians.
     * @param angle_in_radians The angle to rotate.
     * @return A new rotated vector.
     */
    public JesterVector2 rotated(double angle_in_radians) {
        double cos = Math.cos(angle_in_radians);
        double sin = Math.sin(angle_in_radians);
        return new JesterVector2(x * cos - y * sin, x * sin + y * cos);
    }

    /**
     * Gets the orthogonal vector (perpendicular).
     * @return A new orthogonal vector.
     */
    public JesterVector2 orthogonal() {
        return new JesterVector2(-y, x);
    }

    /**
     * Slides along a surface normal.
     * @param normal The surface normal.
     * @return A new vector that slides along the surface normal.
     */
    public JesterVector2 slide(JesterVector2 normal) {
        return this.subtract(normal.scale(dot(normal)));
    }

    /**
     * Reflects the vector on collision.
     * @param normal The surface normal.
     * @return A new reflected vector.
     */
    public JesterVector2 bounce(JesterVector2 normal) {
        return slide(normal.scale(-2));
    }

    /**
     * Reflects the vector on collision (same as bounce).
     * @param normal The surface normal.
     * @return A new reflected vector.
     */
    public JesterVector2 reflect(JesterVector2 normal) {
        return bounce(normal);
    }

    /**
     * Clamps the vector's length to a maximum length.
     * @param length The maximum length.
     * @return A new clamped vector.
     */
    public JesterVector2 clamped(double length) {
        if (length_squared() > length * length) {
            return normalized().scale(length);
        }
        return this;
    }

    /**
     * Checks if this vector is approximately equal to another vector.
     * @param other The other vector.
     * @return true if the vectors are approximately equal, false otherwise.
     */
    public boolean is_equal_approx(JesterVector2 other) {
        return Math.abs(x - other.x) < 1e-6 && Math.abs(y - other.y) < 1e-6;
    }

    /**
     * Moves toward another point by a specified delta.
     * @param target The target point.
     * @param delta The distance to move toward the target.
     * @return A new vector moved toward the target.
     */
    public JesterVector2 move_toward(JesterVector2 target, double delta) {
        JesterVector2 direction = target.subtract(this).normalized();
        return this.add(direction.scale(delta));
    }

    /**
     * Adds another vector to this vector.
     * @param other The other vector.
     * @return A new vector that is the sum of this vector and the other vector.
     */
    public JesterVector2 add(JesterVector2 other) {
        return new JesterVector2(this.x + other.x, this.y + other.y);
    }

    /**
     * Subtracts another vector from this vector.
     * @param other The other vector.
     * @return A new vector that is the difference of this vector and the other vector.
     */
    public JesterVector2 subtract(JesterVector2 other) {
        return new JesterVector2(this.x - other.x, this.y - other.y);
    }

    /**
     * Scales the vector by a scalar value.
     * @param scalar The scalar value.
     * @return A new scaled vector.
     */
    public JesterVector2 scale(double scalar) {
        return new JesterVector2(this.x * scalar, this.y * scalar);
    }

    /**
     * Multiplies this vector by another vector (component-wise).
     * @param other The other vector.
     * @return A new vector that is the component-wise product of this vector and the other vector.
     */
    public JesterVector2 multiply(JesterVector2 other) {
        return new JesterVector2(this.x * other.x, this.y * other.y);
    }

    /**
     * Divides this vector by another vector (component-wise).
     * @param other The other vector.
     * @return A new vector that is the component-wise division of this vector by the other vector.
     */
    public JesterVector2 divide(JesterVector2 other) {
        return new JesterVector2(this.x / other.x, this.y / other.y);
    }

    /**
     * Calculates the distance to another vector.
     * @param other The other vector.
     * @return The distance to the other vector.
     */
    public double distance_to(JesterVector2 other) {
        return this.subtract(other).length();
    }

    /**
     * Calculates the squared distance to another vector.
     * @param other The other vector.
     * @return The squared distance to the other vector.
     */
    public double distance_squared_to(JesterVector2 other) {
        return this.subtract(other).length_squared();
    }

    /**
     * Linearly interpolates between this vector and another vector.
     * @param target The target vector.
     * @param t The interpolation factor (0 to 1).
     * @return A new vector that is the result of the interpolation.
     */
    public JesterVector2 lerp(JesterVector2 target, double t) {
        return this.add(target.subtract(this).scale(t));
    }

    /**
     * Scales this vector by another vector's components.
     * @param other The vector to scale by.
     * @return A new vector that is the result of the component-wise scaling.
     */
    public JesterVector2 scale_by(JesterVector2 other) {
        return new JesterVector2(this.x * other.x, this.y * other.y);
    }

    @Override
    public String toString() {
        return "JesterVector2(" + x + ", " + y + ")";
    }

    /**
     * Calculates the angle between this vector and another vector in radians.
     * @param other The other vector.
     * @return The angle in radians.
     */
    public double angle_between(JesterVector2 other) {
        return Math.acos(this.dot(other) / (this.length() * other.length()));
    }

    /**
     * Projects this vector onto another vector.
     * @param other The vector to project onto.
     * @return A new vector that is the projection of this vector onto the other vector.
     */
    public JesterVector2 project(JesterVector2 other) {
        return other.scale(this.dot(other) / other.length_squared());
    }
}