package jester;

import javax.swing.*;
import java.awt.*;

public class JesterWindow {
    public static JFrame frame; // Make frame static

    public static void setFrame(JFrame frame) {
        JesterWindow.frame = frame; // Access static field directly
    }

    public void maximize() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void minimize() {
        frame.setState(Frame.ICONIFIED);
    }

    public void close() {
        frame.dispatchEvent(new java.awt.event.WindowEvent(frame, java.awt.event.WindowEvent.WINDOW_CLOSING));
    }

    public Dimension getDimensions() {
        return frame.getSize();
    }

    public Dimension getDesktopDimensions() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public boolean getFullscreen() {
        return frame.getExtendedState() == JFrame.MAXIMIZED_BOTH;
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public Image getIcon() {
        return frame.getIconImage();
    }

    public Point getPosition() {
        return frame.getLocationOnScreen();
    }

    public String getTitle() {
        return frame.getTitle();
    }

    public boolean isMinimized() {
        return frame.getState() == Frame.ICONIFIED;
    }

    public boolean isMaximized() {
        return frame.getExtendedState() == JFrame.MAXIMIZED_BOTH;
    }

    public boolean isVisible() {
        return frame.isVisible();
    }

    public boolean isResizable() {
        return frame.isResizable();
    }

    public void setResizable(boolean resizable) {
        frame.setResizable(resizable);
    }

    public void restore() {
        frame.setExtendedState(JFrame.NORMAL);
    }

    public void setFullscreen(boolean fullscreen) {
        if (fullscreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        } else {
            frame.setExtendedState(JFrame.NORMAL);
            frame.setUndecorated(false);
        }
    }

    public void setIcon(Image icon) {
        frame.setIconImage(icon);
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public void setPosition(int x, int y) {
        frame.setLocation(x, y);
    }

    public void showMessageBox(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
