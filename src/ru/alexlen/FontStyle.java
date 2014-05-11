package ru.alexlen;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by almazko on 11.05.14.
 */
public class FontStyle {

    Font font;
    Color color;
    float size;

    public FontStyle(String fontName, int color, float size) {
        createFont(fontName, size);
        this.color = new Color(color);
        this.size = size;
    }

    private void createFont(String name, float size) {

        InputStream fontStream = FontStyle.class.getResourceAsStream("/font/" + name);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            font = font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }
}
