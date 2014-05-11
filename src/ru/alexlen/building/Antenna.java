package ru.alexlen.building;

import java.net.URL;

/**
 * @author Almazko
 */
public class Antenna extends AbstractBuilding {

    final static URL url = Spaceport.class.getResource("/img/icon/antenna_50x50.jpg");
    final static String name = "Antenna";

    public Antenna() {
        build_time = 100 * DAY;
    }

    public URL getImage() {
        return url;
    }

    public String getName() {
        return name;
    }

}
