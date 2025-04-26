package jester;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class JesterSystem {

    /**
     * Gets text from the clipboard.
     * @return The text from the clipboard, or null if no text is available.
     */
    public static String getClipboardText() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);
            if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String) contents.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the current operating system.
     * @return A string representing the current OS.
     */
    public static String getOS() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * Opens a URL with the user's web or file browser.
     * @param url The URL to open.
     */
    public static void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Puts text in the clipboard.
     * @param text The text to put in the clipboard.
     */
    public static void setClipboardText(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Gets the system architecture (e.g., "amd64", "x86").
     * @return A string representing the system architecture.
     */
    public static String getSystemArchitecture() {
        return System.getProperty("os.arch");
    }

    /**
     * Gets the version of the operating system.
     * @return A string representing the OS version.
     */
    public static String getSystemVersion() {
        return System.getProperty("os.version");
    }

    /**
     * Gets the amount of free memory available to the JVM.
     * @return The amount of free memory in bytes.
     */
    public static long getAvailableMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * Gets the total memory currently available to the JVM.
     * @return The total memory in bytes.
     */
    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * Checks if the system has an active internet connection.
     * This method attempts to connect to a reliable URL ([http://www.google.com](http://www.google.com)).
     * @return true if the internet is available, false otherwise.
     */
    public static boolean isInternetAvailable() {
        try {
            final URL url = new URL("[http://www.google.com](http://www.google.com)");
            final HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestProperty("User-Agent", "Mozilla/5.0");
            urlConnect.setRequestProperty("Connection", "close");
            urlConnect.setConnectTimeout(3000); // Timeout in milliseconds
            urlConnect.connect();
            return (urlConnect.getResponseCode() == 200);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Gets the current system time in ISO-8601 format.
     * @return A string representing the current time.
     */
    public static String getCurrentTime() {
        return java.time.LocalDateTime.now().toString();
    }
}