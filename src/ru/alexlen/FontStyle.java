package ru.alexlen;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by almazko on 11.05.14.
 */
public class FontStyle {

    final Font font;
    final Color color;
    final float size;

    final static public class Builder {

        private Color color;
        private float size;

        private String fontName;

        public Builder() {
        }

        Builder setColor(int color) {
            this.color = new Color(color);
            return this;
        }

        Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        Builder setSize(float size) {
            this.size = size;
            return this;
        }

        Builder setFont(String fontName) {
            this.fontName = fontName;
            return this;
        }

        FontStyle build() {
            return new FontStyle(createFont(fontName, size), color, size);
        }

        private Font createFont(String name, float size) {

            Font font = null;
            InputStream fontStream = Main.class.getResourceAsStream("/font/" + name);
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                font = font.deriveFont(size);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }

            return font;
        }

    }

    FontStyle(Font font, Color color, float size) {
        this.font = font;
        this.color = color;
        this.size = size;
    }
}
