package jester;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Font;
import java.io.InputStream;
import java.io.IOException;
import java.util.Objects;

public class JesterAssets {

    public static Image loadImage(String path) {
        return new ImageIcon(
                Objects.requireNonNull(JesterAssets.class.getClassLoader().getResource(path))
        ).getImage();
    }

    public static Font loadFont(String path, float size) {
        try {
            InputStream is = JesterAssets.class.getClassLoader().getResourceAsStream("assets/" + path);
            if (is == null) throw new IOException("Font not found: " + path);
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int)size); // fallback font
        }
    }

    // Add more asset types here in future
}

