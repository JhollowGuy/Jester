package jester;

import java.awt.*;

public class JesterGraphics {
    private Graphics2D g;

    // Called internally to set the current Graphics2D context
    public void setGraphics(Graphics2D g) {
        this.g = g;
    }

    // Draws a filled rectangle
    public void drawRect(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Draws a string of text
    public void drawText(String text, int x, int y, Color color) {
        g.setColor(color);
        g.drawString(text, x, y);
    }

    // Draw a filled circle
    public void drawCircle(int x, int y, int radius, Color color) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    // Draw a line
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    // Clears the screen with a given color
    public void clear(Color color, int width, int height) {
        g.setColor(color);
        g.fillRect(0, 0, width, height);
    }

    public void setColor(Color color) {
        if (g != null) g.setColor(color);
    }
}
