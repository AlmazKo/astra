package ru.alexlen;

import java.awt.*;
import java.util.Map;

/**
 * Created by almazko on 21.04.14.
 */
public class Meta {
    String name;
    int period = 0;
    Map<Resource, Double> composition;
    Color color = new Color(0xffffffff);
    final static String NO_NAME= "No name";

    SubjectType type;


    public Meta() {
        name = NO_NAME;
    }

    public Meta(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
