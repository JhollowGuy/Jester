package jester;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private final Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) {
        data.put(key, value);
        SignalBus.emit(key, value); // Notify listeners that this key changed
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) data.get(key);
    }

    public boolean has(String key) {
        return data.containsKey(key);
    }

    public void remove(String key) {
        data.remove(key);
        SignalBus.emit(key, null); // You can also emit a special 'removed' signal if you want
    }

    public void clear() {
        data.clear();
    }
}
