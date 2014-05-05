package ru.alexlen.build;

import ru.alexlen.Building;

import java.net.URL;

/**
 * @author Almazko
 */
public class RocketFactory implements Building {
    final URL url;

    public RocketFactory() {
        url = RocketFactory.class.getResource("/img/icon/factory_rocket_50x50.jpg");
    }

    @Override
    public URL getImage() {
        return url;
    }
}
