package ru.alexlen;

import java.util.Map;

/**
 * Created by almazko on 21.04.14.
 */
public class Meta {
     String name;
     int period = 0;
     Map<Resource, Double> composition;

    public Meta() {
        name= "";
    }

    public Meta(String name) {
        this.name = name;
    }
}
