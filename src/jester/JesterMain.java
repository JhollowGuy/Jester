package jester;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class JesterMain extends Canvas implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Jester Game";

    private static Scene currentScene;

    private JFrame frame;
    private boolean running = false;
    private Thread gameThread;

    public JesterMain() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);

        frame = new JFrame(TITLE);
        JesterWindow.setFrame(frame); // Tell JesterWindow what the game window is

        // Enable keyboard input
        addKeyListener(JesterInput.keyboard);

        // Enable mouse input
        addMouseListener(JesterInput.mouse);
        addMouseMotionListener(JesterInput.mouse);

        setFocusable(true);
        requestFocusInWindow();
    }

    // Starts the game loop thread
    public synchronized void start() {
        running = true;
        gameThread = new Thread(this, "Game Loop");
        gameThread.start();
    }

    // Stops the game loop
    public synchronized void stop() {
        if (!running) return; // Prevent stopping if already stopped
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // This is the main game loop
    public void run() {
        createGameWindow();

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int frames = 0;

        // Define target FPS and calculate target frame duration
        final int TARGET_FPS = 60;
        final long TARGET_FRAME_TIME = 1000000000 / TARGET_FPS; // in nanoseconds

        // Check if currentScene is initialized
        if (currentScene != null) {
            currentScene.init(); // Run scene's load method
        } else {
            System.err.println("Error: currentScene is not initialized.");
            return; // Exit if currentScene is null
        }

        while (running) {
            long now = System.nanoTime();
            // float dt = (now - lastTime) / 1_000_000_000f; // Convert to seconds
            long frameTime = now - lastTime; // Time taken for this frame
            lastTime = now;

            // Update and render
            //update(dt); // <--- NEW
            update((float) frameTime / 1_000_000_000f); // Pass delta time in seconds
            render();   // <--- same, Render the game

            frames++;

            // Update the title with FPS every second
            if (System.currentTimeMillis() - timer >= 1000) {
                frame.setTitle(TITLE + " | FPS: " + frames);
                frames = 0; // Reset frames count
                timer += 1000; // Move the timer forward
            }

            // Calculate how long to sleep to maintain the target FPS
            long sleepTime = (TARGET_FRAME_TIME - (System.nanoTime() - now)) / 1_000_000; // Convert to milliseconds
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // Sleep for the remaining time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                }
            }
        }

        stop();
    }

    // Runs the current scene's update
    private void update(float dt) {
        if (currentScene != null) {
            currentScene.update(dt); // Pass delta time
        }
        JesterKeyboard.endFrame();
    }

    // Renders the current scene
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); // triple buffering
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Set current graphics context
        Jester.graphics.setGraphics((Graphics2D) g);  // Set the Graphics2D for Jester.graphics

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

//        // Example usage
//        Jester.graphics.clear(Color.BLACK, getWidth(), getHeight());

        if (currentScene != null) {
            currentScene.render(); // Render the current scene using Jester.graphics
        }

        g.dispose();
        bs.show();
    }

    // Creates and configures the window
    private void createGameWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        requestFocus(); // so input works

//        // Add a window listener for graceful exit
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                stop(); // Stop the game loop when the window is closed
//            }
//        });
    }

    // The dev calls this from their game to start everything
    public static void launch(Scene startScene) {
        currentScene = startScene;
        JesterMain game = new JesterMain();
        game.start();
    }

    // Switch scenes
    public static void setScene(Scene newScene) {
        currentScene = newScene;
        currentScene.init();
    }
}
