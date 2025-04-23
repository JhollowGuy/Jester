package jester;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

public class JesterMouse implements MouseListener, MouseMotionListener {

    private static int x = 0;
    private static int y = 0;
    private static final HashSet<Integer> buttonsDown = new HashSet<>();

    @Override
    public void mousePressed(MouseEvent e) {
        buttonsDown.add(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttonsDown.remove(e.getButton());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static boolean isDown(int button) {
        return buttonsDown.contains(button);
    }

    // Not used
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
