package ru.alexlen.building;

import java.net.URL;

/**
 * @author Almazko
 */
public class RocketFactory extends AbstractBuilding {
    final URL url;

    final static String name = "Rocket factory";

    public RocketFactory() {
        url = RocketFactory.class.getResource("/img/icon/factory_rocket_50x50.jpg");
        build_time = 300 * DAY;
    }

    @Override
    public URL getImage() {
        return url;
    }

    public String getName() {
        return name;
    }
}
