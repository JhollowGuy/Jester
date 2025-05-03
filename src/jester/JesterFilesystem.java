package jester;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class JesterFilesystem {
    private String saveDirectory;

    /**
     * Creates a new JesterFilesystem instance.
     */
    public JesterFilesystem() {
        // Initialize the filesystem
        init();
    }

    /**
     * Initializes the filesystem, setting the default save directory.
     */
    public void init() {
        setIdentity("default");
    }

    /**
     * Sets the game's save directory.
     * @param name The name of the game to set as the save directory.
     */
    public void setIdentity(String name) {
        this.saveDirectory = System.getProperty("user.home") + File.separator + name;
        createDirectory(this.saveDirectory);
    }

    public String getSaveDirectory() {
        return saveDirectory;
    }

    public String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    public String getUserDirectory() {
        return System.getProperty("user.home");
    }

    public String getIdentity() {
        return saveDirectory;
    }

    public String getSourceDirectory() {
        // This could be modified to return the source directory of the game files
        return getWorkingDirectory();
    }

    public String getRealDirectory(String path) {
        return Paths.get(path).toAbsolutePath().toString();
    }

    // File and Directory Management
    public boolean exists(String path) {
        return Files.exists(Paths.get(path));
    }

    public boolean isFile(String path) {
        return Files.isRegularFile(Paths.get(path));
    }

    public boolean isDirectory(String path) {
        return Files.isDirectory(Paths.get(path));
    }

    public boolean createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(String path) {
        try {
            if (isDirectory(path)) {
                Files.deleteIfExists(Paths.get(path));
            } else {
                Files.deleteIfExists(Paths.get(path));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listing
    public List<String> enumerate(String dirPath) {
        try {
            return Files.list(Paths.get(dirPath))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public FileInfo getInfo(String path) {
        try {
            Path filePath = Paths.get(path);
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            return new FileInfo(attrs.size(), attrs.lastModifiedTime().toMillis(), isFile(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Reading/Writing
    public String read(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean write(String path, String content) {
        try {
            Files.write(Paths.get(path), content.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean append(String path, String content) {
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Iterable<String> lines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Strict .sav format handling
    public Map<String, String> readSavFile(String path) throws IOException {
        Map<String, String> data = new HashMap<>();
        List<String> lines = (List<String>) lines(path);
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue; // Skip comments
            if (!line.contains("=")) throw new IOException("Invalid line in save file: " + line);
            String[] parts = line.split("=", 2);
            String key = parts[0].trim();
            String value = parts[1].trim();
            if (key.isEmpty()) throw new IOException("Empty key in save file: " + line);
            data.put(key, value);
        }
        return data;
    }

    public void writeSavFile(String path, Map<String, String> data) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getKey().contains("=")) throw new IOException("Invalid key: " + entry.getKey());
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
        }
        write(path, builder.toString());
    }

    // Inner class for file information
    public static class FileInfo {
        private long size;
        private long lastModified;
        private boolean isFile;

        public FileInfo(long size, long lastModified, boolean isFile) {
            this.size = size;
            this.lastModified = lastModified;
            this.isFile = isFile;
        }

        public long getSize() {
            return size;
        }

        public long getLastModified() {
            return lastModified;
        }

        public boolean isFile() {
            return isFile;
        }
    }
}