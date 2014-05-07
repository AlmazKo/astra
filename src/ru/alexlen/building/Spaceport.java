package ru.alexlen.building;

import java.net.URL;

/**
 * @author Almazko
 */
public class Spaceport extends AbstractBuilding {
    final URL url;
    final static String name = "Spaceport";

    public Spaceport() {
        url = Spaceport.class.getResource("/img/icon/spaceport_50x50.jpg");
        build_time = 500 * DAY;
    }

    @Override
    public URL getImage() {
        return url;
    }

    public String getName() {
        return name;
    }
}
