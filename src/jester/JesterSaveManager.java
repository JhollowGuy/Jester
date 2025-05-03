package jester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JesterSaveManager handles saving and loading game data using JesterFilesystem.
 */
public class JesterSaveManager {
    private JesterFilesystem filesystem;
    private String saveFilePath;

    public JesterSaveManager(String gameName) {
        filesystem = new JesterFilesystem();
        filesystem.setIdentity(gameName);
        saveFilePath = filesystem.getSaveDirectory() + "/saves/save1.sav";
    }

    /**
     * Saves the game state to a file.
     * @param data A map containing key-value pairs to save.
     */
    public void saveGame(Map<String, String> data) {
        try {
            filesystem.writeSavFile(saveFilePath, data);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     * Loads the game state from a file.
     * @return A map containing the loaded key-value pairs.
     */
    public Map<String, String> loadGame() {
        try {
            return filesystem.readSavFile(saveFilePath);
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return new HashMap<>(); // Return empty map on error
        }
    }

    /**
     * Checks if a save file exists.
     * @return true if the save file exists, false otherwise.
     */
    public boolean saveExists() {
        return filesystem.exists(saveFilePath);
    }
}