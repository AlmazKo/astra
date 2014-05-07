package ru.alexlen.building;

import java.net.URL;

/**
 * @author Almazko
 */
public class Antenna extends AbstractBuilding {
    final URL url;
    final static String name = "Antenna";

    public Antenna() {
        url = Spaceport.class.getResource("/img/icon/antenna_50x50.jpg");
        build_time = 100 * DAY;
    }

    public URL getImage() {
        return url;
    }

    public String getName() {
        return name;
    }

}
