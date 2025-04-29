package jester;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * SignalEmitter allows objects to emit and listen for signals.
 * It supports a publish-subscribe pattern for event handling.
 */
public class SignalEmitter {
    private final Map<String, Set<Consumer<Object>>> listeners = new HashMap<>();

    /**
     * Subscribes a callback to a specific signal.
     * @param signalName The name of the signal to listen for.
     * @param callback The callback to invoke when the signal is emitted.
     */
    public void on(String signalName, Consumer<Object> callback) {
        listeners.computeIfAbsent(signalName, k -> new HashSet<>()).add(callback);
    }

    /**
     * Unsubscribes a callback from a specific signal.
     *
     * @param signalName The name of the signal to stop listening to.
     * @param callback The callback to remove from the signal.
     */
    public void off(String signalName, Consumer<Object> callback) {
        if (listeners.containsKey(signalName)) {
            listeners.get(signalName).remove(callback);
        }
    }

    /**
     * Emits a signal, invoking all subscribed callbacks with the provided data.
     * @param signalName The name of the signal to emit.
     * @param data The data to pass to the callbacks.
     */
    public void emit(String signalName, Object data) {
        if (listeners.containsKey(signalName)) {
            for (Consumer<Object> callback : listeners.get(signalName)) {
                callback.accept(data);
            }
        }
    }

    /**
     * Clears all listeners for all signals.
     */
    public void clearAll() {
        listeners.clear();
    }
}
