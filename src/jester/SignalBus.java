package jester;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class SignalBus {
    private static final Map<String, Set<Consumer<Object>>> listeners = new HashMap<>();

    public static void on(String signalName, Consumer<Object> callback) {
        listeners.computeIfAbsent(signalName, k -> new HashSet<>()).add(callback);
    }

    public static void off(String signalName, Consumer<Object> callback) {
        if (listeners.containsKey(signalName)) {
            listeners.get(signalName).remove(callback);
        }
    }

    public static void emit(String signalName, Object data) {
        if (listeners.containsKey(signalName)) {
            for (Consumer<Object> callback : listeners.get(signalName)) {
                callback.accept(data);
            }
        }
    }

    public static void clearAll() {
        listeners.clear();
    }
}
