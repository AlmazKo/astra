package ru.alexlen.build;

import ru.alexlen.Building;

import java.net.URL;

/**
 * @author Almazko
 */
public class Spaceport implements Building {
    final URL url;

    public Spaceport() {
        url = Spaceport.class.getResource("/img/icon/spaceport_50x50.jpg");
    }

    @Override
    public URL getImage() {
        return url;
    }
}
