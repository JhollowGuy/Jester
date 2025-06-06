package jester;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * The SignalBus class provides a simple event bus system for the Jester framework.
 * It allows components to subscribe to signals and emit events without direct coupling.
 */
public class SignalBus {
    // Map to hold signal names and their corresponding listeners
    private static final Map<String, Set<Consumer<?>>> listeners = new HashMap<>();

    /**
     * Subscribes a callback to a specific signal.
     *
     * @param signalName The name of the signal to listen for.
     * @param callback The callback to invoke when the signal is emitted.
     * @param <T> The type of data the callback accepts.
     */
    public static <T> void on(String signalName, Consumer<T> callback) {
        listeners.computeIfAbsent(signalName, k -> new HashSet<>()).add(callback);
    }

    /**
     * Unsubscribes a callback from a specific signal.
     *
     * @param signalName The name of the signal to stop listening to.
     * @param callback The callback to remove from the signal.
     * @param <T> The type of data the callback accepts.
     */
    public static <T> void off(String signalName, Consumer<T> callback) {
        if (listeners.containsKey(signalName)) {
            listeners.get(signalName).remove(callback);
        }
    }

    /**
     * Emits a signal, invoking all subscribed callbacks with the provided data.
     *
     * @param signalName The name of the signal to emit.
     * @param data The data to pass to the callbacks.
     * @param <T> The type of data being emitted.
     */
    @SuppressWarnings("unchecked")
    public static <T> void emit(String signalName, T data) {
        if (listeners.containsKey(signalName)) {
            for (Consumer<?> callback : listeners.get(signalName)) {
                ((Consumer<T>) callback).accept(data); // Safe cast
            }
        }
    }

    /**
     * Clears all listeners for all signals.
     */
    public static void clearAll() {
        listeners.clear();
    }
}
