package jester;

import javax.swing.*;
import java.awt.*;

/**
 * JesterWindow provides an abstraction over the main JFrame window used by the Jester framework.
 * It allows easy manipulation of window state, size, position, and appearance.
 */
public class JesterWindow {
    /**
     * The window frame used by the Jester framework.
     */
    public static JFrame frame; // Make frame static

    /**
     * Sets the internal JFrame reference used by the window.
     *
     * @param frame The JFrame instance used by the framework.
     */
    public static void setFrame(JFrame frame) {
        JesterWindow.frame = frame; // Access static field directly
    }

    /**
     * Maximizes the window to fullscreen (windowed mode).
     */
    public void maximize() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Minimizes the window to the taskbar.
     */
    public void minimize() {
        frame.setState(Frame.ICONIFIED);
    }

    /**
     * Closes the window by dispatching a close event.
     */
    public void close() {
        frame.dispatchEvent(new java.awt.event.WindowEvent(frame, java.awt.event.WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Returns the current size of the window.
     *
     * @return A Dimension object representing the width and height.
     */
    public Dimension getDimensions() {
        return frame.getSize();
    }

    /**
     * Returns the size of the user's desktop screen.
     *
     * @return A Dimension object representing the desktop width and height.
     */
    public Dimension getDesktopDimensions() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * Checks if the window is currently in fullscreen mode.
     *
     * @return true if fullscreen, false otherwise.
     */
    public boolean getFullscreen() {
        return frame.getExtendedState() == JFrame.MAXIMIZED_BOTH && frame.isUndecorated();
    }

    /**
     * Returns the current width of the window.
     *
     * @return Width in pixels.
     */
    public int getWidth() {
        return frame.getWidth();
    }

    /**
     * Returns the current height of the window.
     *
     * @return Height in pixels.
     */
    public int getHeight() {
        return frame.getHeight();
    }

    /**
     * Returns the icon currently set on the window.
     *
     * @return An Image object, or null if none is set.
     */
    public Image getIcon() {
        return frame.getIconImage();
    }

    /**
     * Returns the current screen position of the window.
     *
     * @return A Point object representing the x and y screen position.
     */
    public Point getPosition() {
        return frame.getLocationOnScreen();
    }

    /**
     * Returns the current title of the window.
     *
     * @return The window title.
     */
    public String getTitle() {
        return frame.getTitle();
    }

    /**
     * Checks if the window is minimized.
     *
     * @return true if minimized, false otherwise.
     */
    public boolean isMinimized() {
        return frame.getState() == Frame.ICONIFIED;
    }

    /**
     * Checks if the window is maximized.
     *
     * @return true if maximized, false otherwise.
     */
    public boolean isMaximized() {
        return frame.getExtendedState() == JFrame.MAXIMIZED_BOTH;
    }

    /**
     * Checks if the window is currently visible.
     *
     * @return true if visible, false otherwise.
     */
    public boolean isVisible() {
        return frame.isVisible();
    }

    /**
     * Checks if the window is resizable by the user.
     *
     * @return true if resizable, false otherwise.
     */
    public boolean isResizable() {
        return frame.isResizable();
    }

    /**
     * Sets whether the window can be resized by the user.
     *
     * @param resizable true to allow resizing, false to lock size.
     */
    public void setResizable(boolean resizable) {
        frame.setResizable(resizable);
    }

    /**
     * Restores the window to normal state (not minimized or maximized).
     */
    public void restore() {
        frame.setExtendedState(JFrame.NORMAL);
    }

    /**
     * Enables or disables fullscreen mode (borderless maximized window).
     *
     * @param fullscreen true to enable, false to disable.
     */
    public void setFullscreen(boolean fullscreen) {
        frame.dispose(); // Needed to apply undecorated flag
        frame.setUndecorated(fullscreen);
        frame.setVisible(true);

        if (fullscreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            frame.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Toggles fullscreen mode on or off.
     */
    public void toggleFullscreen() {
        setFullscreen(!getFullscreen());
    }

    /**
     * Sets the icon displayed in the window title bar.
     *
     * @param icon The Image to use as the icon.
     */
    public void setIcon(Image icon) {
        frame.setIconImage(icon);
    }

    /**
     * Sets the window title.
     *
     * @param title A string representing the window title.
     */
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    /**
     * Sets the screen position of the window.
     *
     * @param x X position in pixels.
     * @param y Y position in pixels.
     */
    public void setPosition(int x, int y) {
        frame.setLocation(x, y);
    }

    /**
     * Sets the window's dimensions (width and height).
     *
     * @param width  The desired width in pixels.
     * @param height The desired height in pixels.
     */
    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    /**
     * Moves the window to the center of the screen.
     */
    public void centerOnScreen() {
        Dimension screen = getDesktopDimensions();
        int x = (screen.width - frame.getWidth()) / 2;
        int y = (screen.height - frame.getHeight()) / 2;
        setPosition(x, y);
    }

    /**
     * Displays a message box with a title and message.
     *
     * @param title   The title of the message box.
     * @param message The message content.
     */
    public void showMessageBox(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
