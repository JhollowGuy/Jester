package jester;

import java.util.Random;

/**
 * JesterMath provides various mathematical functions and utilities,
 * including random number generation, trigonometric functions, and more.
 */
public class JesterMath {
    private static Random random = new Random();

    // Mathematical constants
    public static final float PI = (float) Math.PI; // Pi constant
    public static final float TWO_PI = 2 * PI; // 2 * Pi
    public static final float HALF_PI = PI / 2; // Pi / 2
    public static final float E = (float) Math.E; // Euler's number
    public static final float GOLDEN_RATIO = (1 + (float) Math.sqrt(5)) / 2; // (1 + √5) / 2, Golden ratio
    public static final float SQRT_2 = (float) Math.sqrt(2); // √2

    // Ex. JesterTransform transform = Jester.math.newTransform();
    public static JesterTransform newTransform() {
        return new JesterTransform();
    }

    /**
     * Gets the seed of the random number generator.
     * @return The current seed of the random number generator.
     */
    public static int getRandomSeed() {
        return random.nextInt();
    }

    /**
     * Gets the current state of the random number generator.
     * @return The current Random object representing the state.
     */
    public static Object getRandomState() {
        return random; // Just return the Random object (simple for now)
    }

    /**
     * Sets the seed of the random number generator.
     * @param seed The seed to set for the random number generator.
     */
    public static void setRandomSeed(long seed) {
        random.setSeed(seed);
    }

    /**
     * Sets the current state of the random number generator.
     * @param newRandom The new Random object to set as the current state.
     */
    public static void setRandomState(Random newRandom) {
        random = newRandom;
    }

    /**
     * Generates a random integer between the specified min and max values (inclusive).
     * @param min The minimum value (inclusive).
     * @param max The maximum value (inclusive).
     * @return A random integer between min and max.
     */
    public static int random(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Gets a uniformly distributed pseudo-random number between 0.0 and 1.0.
     * @return A random float between 0.0 (inclusive) and 1.0 (exclusive).
     */
    public static float random() {
        return random.nextFloat();
    }

    /**
     * Generates a normally distributed pseudo-random number.
     * @param mean The mean of the distribution.
     * @param deviation The standard deviation of the distribution.
     * @return A random float following a normal distribution.
     */
    public static float randomNormal(float mean, float deviation) {
        return (float) (mean + random.nextGaussian() * deviation);
    }

    /**
     * Clamps a value between a minimum and maximum value.
     * @param value The value to clamp.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The clamped value.
     */
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Performs linear interpolation between two values.
     * @param a The start value.
     * @param b The end value.
     * @param t The interpolation factor (0 to 1).
     * @return The interpolated value.
     */
    public static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    /**
     * Gets the absolute value of a float.
     * @param value The value to get the absolute of.
     * @return The absolute value.
     */
    public static float abs(float value) {
        return Math.abs(value);
    }

    /**
     * Calculates the sine of an angle in radians.
     * @param radians The angle in radians.
     * @return The sine of the angle.
     */
    public static float sin(float radians) {
        return (float) Math.sin(radians);
    }

    /**
     * Calculates the cosine of an angle in radians.
     * @param radians The angle in radians.
     * @return The cosine of the angle.
     */
    public static float cos(float radians) {
        return (float) Math.cos(radians);
    }

    /**
     * Calculates the tangent of an angle in radians.
     * @param radians The angle in radians.
     * @return The tangent of the angle.
     */
    public static float tan(float radians) {
        return (float) Math.tan(radians);
    }

    /**
     * Calculates the distance between two points in 2D space.
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static float distanceTo(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculates the direction from one point to another in radians.
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the target point.
     * @param y2 The y-coordinate of the target point.
     * @return The angle in radians from the starting point to the target point.
     */
    public static float directionTo(float x1, float y1, float x2, float y2) {
        return (float) Math.atan2(y2 - y1, x2 - x1);
    }

    /**
     * Rounds a float to the nearest integer.
     * @param value The value to round.
     * @return The rounded value.
     */
    public static float round(float value) {
        return Math.round(value);
    }

    /**
     * Floors a float to the nearest integer less than or equal to the value.
     * @param value The value to floor.
     * @return The floored value.
     */
    public static float floor(float value) {
        return (float) Math.floor(value);
    }

    /**
     * Ceils a float to the nearest integer greater than or equal to the value.
     * @param value The value to ceil.
     * @return The ceiled value.
     */
    public static float ceil(float value) {
        return (float) Math.ceil(value);
    }

    /**
     * Converts degrees to radians.
     * @param degrees The angle in degrees.
     * @return The angle in radians.
     */
    public static float degToRad(float degrees) {
        return (float) Math.toRadians(degrees);
    }

    /**
     * Converts radians to degrees.
     * @param radians The angle in radians.
     * @return The angle in degrees.
     */
    public static float radToDeg(float radians) {
        return (float) Math.toDegrees(radians);
    }

    /**
     * Calculates the square root of a value.
     * @param value The value to calculate the square root of.
     * @return The square root of the value.
     */
    public static float sqrt(float value) {
        return (float) Math.sqrt(value);
    }

    /**
     * Raises a base to the power of an exponent.
     * @param base The base value.
     * @param exponent The exponent value.
     * @return The result of base raised to the power of exponent.
     */
    public static float pow(float base, float exponent) {
        return (float) Math.pow(base, exponent);
    }

    /**
     * Returns the minimum of two values.
     * @param a The first value.
     * @param b The second value.
     * @return The minimum value.
     */
    public static float min(float a, float b) {
        return (a < b) ? a : b;
    }

    /**
     * Returns the maximum of two values.
     * @param a The first value.
     * @param b The second value.
     * @return The maximum value.
     */
    public static float max(float a, float b) {
        return (a > b) ? a : b;
    }

    /**
     * Returns the sign of a number.
     * @param x The input number.
     * @return -1 if x is negative, 1 if x is positive, and 0 if x is zero.
     */
    public static int sign(float x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    /**
     * Clamps a value between 0 and 1.
     * @param value The value to clamp.
     * @return The clamped value between 0 and 1.
     */
    public static float clamp01(float value) {
        return clamp(value, 0, 1);
    }

    /**
     * Performs smooth interpolation between two values.
     * @param edge0 The lower edge of the interpolation.
     * @param edge1 The upper edge of the interpolation.
     * @param x The value to interpolate.
     * @return The interpolated value.
     */
    public static float smoothstep(float edge0, float edge1, float x) {
        // Scale, clamp, and interpolate
        float t = clamp01((x - edge0) / (edge1 - edge0));
        return t * t * (3 - 2 * t);
    }
}