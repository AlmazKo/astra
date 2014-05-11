package ru.alexlen.building;

import java.net.URL;

/**
 * @author Almazko
 */
public class RocketFactory extends AbstractBuilding {

    final static String name = "Rocket factory";
    final static URL url = RocketFactory.class.getResource("/img/icon/factory_rocket_50x50.jpg");

    public RocketFactory() {
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
