package jester;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the game state, allowing for storage and retrieval of game data.
 * This class provides methods to set, get, remove, and clear data,
 * as well as notifying listeners of changes.
 */
public class GameState {
    private final Map<String, Object> data = new HashMap<>();

    /**
     * Sets a value in the game state associated with the specified key.
     * @param key The key to associate with the value.
     * @param value The value to store.
     */
    public void set(String key, Object value) {
        data.put(key, value);
        SignalBus.emit(key, value); // Notify listeners that this key changed
    }

    /**
     * Retrieves the value associated with the specified key.
     * @param key The key associated with the desired value.
     * @param <T> The expected type of the value.
     * @return The value associated with the key, or null if the key does not exist.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
//        if (!data.containsKey(key)) {
//            throw new NoSuchElementException("No value found for key: " + key);
//        }
//        return (T) data.get(key);
        return data.containsKey(key) ? (T) data.get(key) : null; // Return null if key doesn't exist
    }

    /**
     * Checks if the specified key exists in the game state.
     * @param key The key to check for existence.
     * @return true if the key exists, false otherwise.
     */
    public boolean has(String key) {
        return data.containsKey(key);
    }

    /**
     * Removes the value associated with the specified key from the game state.
     * @param key The key to remove.
     */
    public void remove(String key) {
        data.remove(key);
        SignalBus.emit(key, null); // You can also emit a special 'removed' signal if you want
    }

    /**
     * Clears all data from the game state.
     */
    public void clear() {
        data.clear();
    }
}
