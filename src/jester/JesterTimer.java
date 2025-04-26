package jester;

public class JesterTimer {
    private long lastFrameTime;
    private long startTime;
    private double delta;
    private double averageDelta;
    private int frameCount;
    private double totalDelta;

    public JesterTimer() {
        lastFrameTime = System.nanoTime();
        startTime = lastFrameTime;
        delta = 0;
        averageDelta = 0;
        frameCount = 0;
        totalDelta = 0;
    }

    /**
     * Measures the time between two frames and updates the delta time.
     */
    public void step() {
        long currentTime = System.nanoTime();
        delta = (currentTime - lastFrameTime) / 1_000_000_000.0; // Convert to seconds
        lastFrameTime = currentTime;

        // Update average delta
        totalDelta += delta;
        frameCount++;

        // Calculate average delta for the last second
        if (System.nanoTime() - startTime >= 1_000_000_000) {
            averageDelta = totalDelta / frameCount;
            totalDelta = 0;
            frameCount = 0;
        }
    }

    /**
     * Returns the average delta time over the last second.
     * @return average delta time in seconds
     */
    public double getAverageDelta() {
        return averageDelta;
    }

    /**
     * Returns the time between the last two frames.
     * @return delta time in seconds
     */
    public double getDelta() {
        return delta;
    }

    /**
     * Returns the current frames per second.
     * @return frames per second
     */
    public double getFPS() {
        return 1.0 / delta;
    }

    /**
     * Returns the value of a timer with microsecond precision.
     * @return current time in microseconds
     */
    public long getMicroTime() {
        return System.nanoTime() / 1_000; // Convert to microseconds
    }

    /**
     * Returns the precise amount of time since some time in the past.
     * @param pastTime the time in nanoseconds to compare against
     * @return time in seconds since the past time
     */
    public double getTime(long pastTime) {
        return (System.nanoTime() - pastTime) / 1_000_000_000.0; // Convert to seconds
    }

    /**
     * Pauses the current thread for the specified amount of time.
     * @param milliseconds the amount of time to sleep in milliseconds
     */
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}