package jester;

import java.awt.*;
import java.awt.geom.*;

/**
 * JesterGraphics provides methods for rendering 2D graphics in the Jester framework.
 * This class wraps around the Graphics2D context to simplify common drawing operations.
 */
public class JesterGraphics {
    private Graphics2D g;
    private Font defaultFont; // Default font for text rendering
    private static JesterFilter defaultFilter = JesterFilter.VECTOR;

    /**
     * Constructor that initializes the default font.
     */
    public JesterGraphics() {
        this.defaultFont = new Font("Arial", Font.PLAIN, 12); // Default font
    }

    /**
     * Sets the current Graphics2D context for rendering.
     * @param g The Graphics2D context to set.
     */
    public void setGraphics(Graphics2D g) {
        this.g = g;
        g.setFont(defaultFont); // Set the default font for the Graphics2D context
    }

    /**
     * Gets the current Graphics2D context.
     * @return The current Graphics2D context.
     */
    public Graphics2D getGraphics() {
        return g; // Return the stored Graphics2D context
    }

    /**
     * Draws a rectangle at the specified position with the given color.
     * @param mode The drawing mode ("fill" or "line").
     * @param x The x-coordinate of the rectangle.
     * @param y The y-coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */
    public void drawRect(String mode, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        if ("fill".equalsIgnoreCase(mode)) {
            g.fillRect(x, y, width, height);
        } else if ("line".equalsIgnoreCase(mode)) {
            g.drawRect(x, y, width, height);
        }
    }

    /**
     * Draws a string of text at the specified position with the given color.
     * If no font is specified, the default font is used.
     * @param text The text to draw.
     * @param x The x-coordinate to draw the text.
     * @param y The y-coordinate to draw the text.
     * @param color The color of the text.
     * @param font The font to use for the text (optional).
     */
    public void drawText(String text, int x, int y, Color color, Font font) {
        g.setColor(color);
        if (font != null) {
            g.setFont(font); // Use specified font if provided
        } else {
            g.setFont(defaultFont); // Use default font
        }
        g.drawString(text, x, y);
    }
    // Draw text using a custom font
    // Font customFont = new Font("Comic Sans MS", Font.BOLD, 16);
    // jesterGraphics.drawText("Custom Font Text", 50, 100, Color.BLUE, customFont); // Using custom font

    /**
     * Draws a filled circle at the specified position with the given color.
     * @param x The x-coordinate of the center of the circle.
     * @param y The y-coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param color The color to fill the circle.
     */
    public void drawCircle(int x, int y, int radius, Color color) {
        g.setColor(color);
        //  g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        g.fill(new Ellipse2D.Float(x - radius, y - radius, radius * 2, radius * 2));
    }

    /**
     * Draws a circle or ellipse based on the mode specified.
     * @param mode The drawing mode ("fill" or "line").
     * @param x The x-coordinate of the center.
     * @param y The y-coordinate of the center.
     * @param radiusX The x-radius (for ellipse) or radius (for circle).
     * @param radiusY The y-radius (for ellipse).
     */
    public void drawEllipse(String mode, int x, int y, int radiusX, int radiusY) {
        if ("fill".equalsIgnoreCase(mode)) {
            g.fill(new Ellipse2D.Float(x - radiusX, y - radiusY, radiusX * 2, radiusY * 2));
        } else if ("line".equalsIgnoreCase(mode)) {
            g.draw(new Ellipse2D.Float(x - radiusX, y - radiusY, radiusX * 2, radiusY * 2));
        }
    }

    /**
     * Draws a filled or outlined rounded rectangle at the specified position with the given color.
     * @param mode The drawing mode ("fill" or "line").
     * @param x The x-coordinate of the rectangle.
     * @param y The y-coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param arcWidth The width of the arc at the corners.
     * @param arcHeight The height of the arc at the corners.
     * @param color The color to fill the rectangle.
     */
    public void drawRoundRect(String mode, int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {
        g.setColor(color);
        if ("fill".equalsIgnoreCase(mode)) {
            g.fill(new RoundRectangle2D.Float(x, y, width, height, arcWidth, arcHeight));
        } else if ("line".equalsIgnoreCase(mode)) {
            g.draw(new RoundRectangle2D.Float(x, y, width, height, arcWidth, arcHeight));
        }
    }

//    /**
//     * Draws a line connecting multiple points.
//     * @param color The color of the line.
//     * @param points An array of integers representing point coordinates.
//     */
//    public void drawLine(Color color, int... points) {
//        if (points.length < 4) {
//            throw new IllegalArgumentException("At least two points (x1, y1, x2, y2) are required.");
//        }
//        g.setColor(color); // Set default color or pass as parameter
//        //g.drawLine(x1, y1, x2, y2);
//        // g.draw(new Line2D.Float(x1, y1, x2, y2));
//        for (int i = 0; i < points.length - 2; i += 2) {
//            g.drawLine(points[i], points[i + 1], points[i + 2], points[i + 3]);
//        }
//    }

    /**
     * Draws a line from (x1,y1) to (x2,y2)
     * @param x1 Starting x coordinate
     * @param y1 Starting y coordinate
     * @param x2 Ending x coordinate
     * @param y2 Ending y coordinate
     * @param color The color of the line
     */
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    /**
     * Draws an arc defined by the specified bounding rectangle.
     * @param x The x-coordinate of the bounding rectangle.
     * @param y The y-coordinate of the bounding rectangle.
     * @param width The width of the bounding rectangle.
     * @param height The height of the bounding rectangle.
     * @param startAngle The starting angle of the arc.
     * @param arcAngle The angular extent of the arc.
     * @param color The color of the arc.
     */
    public void drawArc(int x, int y, int width, int height, float startAngle, float arcAngle, Color color) {
        g.setColor(color);
        g.draw(new Arc2D.Float(x, y, width, height, startAngle, arcAngle, Arc2D.OPEN));
    }

    /**
     * Draws a single point.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public void drawPoint(int x, int y) {
        g.fill(new Ellipse2D.Float(x - 1, y - 1, 2, 2)); // Draw a small circle for the point
    }

    /**
     * Draws multiple points.
     * @param points An array of integers representing point coordinates.
     */
    public void drawPoints(int... points) {
        for (int i = 0; i < points.length; i += 2) {
            drawPoint(points[i], points[i + 1]);
        }
    }

    /**
     * Draws a filled triangle defined by three points.
     * @param mode The drawing mode ("fill" or "line").
     * @param x1 The x-coordinate of the first vertex.
     * @param y1 The y-coordinate of the first vertex.
     * @param x2 The x-coordinate of the second vertex.
     * @param y2 The y-coordinate of the second vertex.
     * @param x3 The x-coordinate of the third vertex.
     * @param y3 The y-coordinate of the third vertex.
     */
    public void drawTriangle(String mode, int x1, int y1, int x2, int y2, int x3, int y3) {
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};
        if ("fill".equalsIgnoreCase(mode)) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else if ("line".equalsIgnoreCase(mode)) {
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }

    /**
     * Clears the screen with the specified color.
     * @param color The color to fill the screen with.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    public void clear(Color color, int width, int height) {
        g.setColor(color);
        g.fillRect(0, 0, width, height);
    }

    /**
     * Sets the current color for drawing.
     * @param color The color to set.
     */
    public void setColor(Color color) {
        if (g != null) g.setColor(color);
    }

    /**
     * Draws an image at the specified position.
     * @param image The image to draw.
     * @param x The x-coordinate to draw the image.
     * @param y The y-coordinate to draw the image.
     */
    public void drawImage(Image image, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    /**
     * Draws a polygon defined by an array of points.
     * @param points An array of JesterVector2 representing the vertices of the polygon.
     * @param color The color to fill the polygon.
     */
    public void drawPolygon(JesterVector2[] points, Color color) {
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xPoints[i] = (int) points[i].getX();
            yPoints[i] = (int) points[i].getY();
        }
        g.setColor(color);
        g.fillPolygon(xPoints, yPoints, points.length);
    }

    /**
     * Translates the graphics context by the specified coordinates
     * @param dx The distance to translate along X axis
     * @param dy The distance to translate along Y axis
     */
    public void translate(float dx, float dy) {
        g.translate(dx, dy);
    }

    /**
     * Resets all transformations to the identity transform
     */
    public void resetTransform() {
        g.setTransform(new AffineTransform());
    }

    /**
     * Applies camera transformations
     * @param camera The camera to apply transformations from
     */
    public void applyCameraTransform(JesterCamera camera) {
        translate((float) Jester.window.getWidth() /2 - camera.getX(),
                (float) Jester.window.getHeight() /2 - camera.getY());
    }

    /**
     * Sets the default filter for JesterGraphics.
     * @param filter The filter to set.
     */
    public static void setDefaultFilter(JesterFilter filter) {
        defaultFilter = filter;
    }

    /**
     * Applies the specified filter to the graphics context.
     * @param g The graphics context to apply the filter to.
     */
    public static void applyFilter(Graphics2D g) {
        if (defaultFilter == JesterFilter.PIXEL) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        } else {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
    }

//    /**
//     * Applies the camera transformation to the graphics context.
//     * @param camera The camera to apply transformations from.
//     */
//    public void applyCameraTransform(JesterCamera camera) {
//        // Create a new transformation using JesterTransform
//        JesterTransform cameraTransform = new JesterTransform();
//        cameraTransform.setTransformation((float) camera.getX(), (float) camera.getY(), camera.getRotation(), camera.getZoom(), camera.getZoom());
//
//        // Create an AffineTransform from the matrix
//        float[] matrix = cameraTransform.getMatrix();
//        AffineTransform transform = new AffineTransform();
//        transform.setTransform(matrix[0], matrix[1], matrix[3], matrix[4], matrix[6], matrix[7]);
//
//        // Apply the transformation
//        g.setTransform(transform);
//    }
}