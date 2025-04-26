package jester;

import java.awt.Color;
import java.util.Random;

/**
 * JesterColor provides pre-defined and custom color utilities for use with the Jester framework.
 */
public class JesterColor {
    // Predefined color constants for quick access
    public static final Color BLACK = Color.BLACK;
    public static final Color WHITE = Color.WHITE;
    public static final Color RED = Color.RED;
    public static final Color GREEN = Color.GREEN;
    public static final Color BLUE = Color.BLUE;
    public static final Color YELLOW = Color.YELLOW;
    public static final Color CYAN = Color.CYAN;
    public static final Color MAGENTA = Color.MAGENTA;
    public static final Color ORANGE = Color.ORANGE;
    public static final Color PINK = Color.PINK;
    public static final Color GRAY = Color.GRAY;
    public static final Color DARK_GRAY = Color.DARK_GRAY;
    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
    public static final Color PURPLE = new Color(128, 0, 128);

    /**
     * Creates a new Color using RGB values.
     * @param r Red (0-255)
     * @param g Green (0-255)
     * @param b Blue (0-255)
     * @return A new Color object
     * @throws IllegalArgumentException if any value is out of range
     */
    public static Color rgb(int r, int g, int b) {
        validateColorValue(r);
        validateColorValue(g);
        validateColorValue(b);
        return new Color(r, g, b);
    }

    /**
     * Creates a new Color using RGBA values.
     * @param r Red (0-255)
     * @param g Green (0-255)
     * @param b Blue (0-255)
     * @param a Alpha (0-255)
     * @return A new Color object with alpha transparency
     * @throws IllegalArgumentException if any value is out of range
     */
    public static Color rgba(int r, int g, int b, int a) {
        validateColorValue(r);
        validateColorValue(g);
        validateColorValue(b);
        validateColorValue(a);
        return new Color(r, g, b, a);
    }

    /**
     * Validates that a color value is within the range of 0-255.
     * @param value The color value to validate
     * @throws IllegalArgumentException if the value is out of range
     */
    private static void validateColorValue(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Color value must be between 0 and 255. Value: " + value);
        }
    }

    /**
     * Blends two colors based on a ratio.
     * @param color1 The first color
     * @param color2 The second color
     * @param ratio A value between 0 and 1 indicating the blend ratio
     * @return A new blended Color object
     */
    public static Color blend(Color color1, Color color2, float ratio) {
        ratio = Math.max(0, Math.min(1, ratio)); // Clamp ratio between 0 and 1
        int r = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int g = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int b = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
        return new Color(r, g, b);
    }

    /**
     * Converts a color to grayscale.
     * @param color The original color
     * @return A new Color object representing the grayscale equivalent
     */
    public static Color toGrayscale(Color color) {
        int gray = (int) (0.3 * color.getRed() + 0.59 * color.getGreen() + 0.11 * color.getBlue());
        return new Color(gray, gray, gray);
    }

    /**
     * Generates a random Color.
     * @return A new Color object with random RGB values
     */
    public static Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(256); // Generates a random value between 0 and 255
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
