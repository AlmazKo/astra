package ru.alexlen;

import java.awt.Color;

/**
 * Created by almazko on 26.04.14.
 */
public class Owner {
    private static int INCREMENT;
    boolean isPlayer;
    String name;
    Color color;


    private Owner(boolean isPlayer, String name, Color color) {
        this.isPlayer = isPlayer;
        this.name = name;
        this.color = color;
    }

    static Owner createBot(final String name, Color color) {
        return new Owner(false, name, color);
    }


    static Owner createPlayer(final String name, Color color) {
        return new Owner(true, name, color);
    }
}
