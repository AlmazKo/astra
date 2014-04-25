package ru.alexlen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by almazko on 23.04.14.
 */
public class Data {

    static List<Planet> populate() {
        ArrayList<Planet> data = new ArrayList<>();

        Subject sun = new Subject(2392e+4);
////        Subject sun = new Subject(1392e+6);
//        sun.meta.color =   new Color(0xFEE640);
//        sun.meta.name = "Sun";
//        data.add(sun);

        Subject mercury = new Subject(2439e+3, 87.97/365, new PolarCoordinate(0, 58e+9));
        mercury.meta.name = "Mercury";
        mercury.meta.period = 115;
        mercury.meta.color = new Color(0xFEC07D);
        mercury.center = sun.center;


        data.add(new Planet(mercury));

        Subject venus = new Subject(6051e+3, 224.7/365, new PolarCoordinate(0, 108e+9));
        venus.meta.name = "Venus";
        venus.meta.period = 224;
        venus.meta.color = new Color(0x919D34);
        venus.center = sun.center;

        data.add(new Planet(venus)); // venus 224

        Subject earth = new Subject(6371e+3, 1,  new PolarCoordinate(0, 149e+9));
        earth.meta.name = "Earth";
        earth.meta.period = 365;
        earth.meta.color = new Color(0x15ABFF);
        earth.center = sun.center;

        Subject moon = new Subject(1738e+3, 29.5/365, new PolarCoordinate(0, 384e+6));
        moon.meta.name = "Moon";
        moon.meta.period = 29;
        moon.meta.color = new Color(0xE9E4EA);


        data.add(new Planet(earth, moon));
//
        Subject mars = new Subject(4439e+3, 779.0/365, new PolarCoordinate(0, 200e+9));
        mars.meta.name = "Mars";
        mars.meta.color = new Color(0xffff0000);
        mars.meta.period = 779;
        data.add(new Planet(mars)); // mars 779
//
        Subject jupiter = new Subject(69_911e+3, 4332.6/365, new PolarCoordinate(0, 770e+9));
        jupiter.meta.name = "Jupiter";
        jupiter.meta.period = 4_332;
        jupiter.meta.color = new Color(0x900000);
        data.add(new Planet(jupiter)); // jupiter
//
//
//        Subject saturn = new Subject(13, 148, 450, new Color(0xFFEB13));
//        saturn.meta.name = "Saturn";
//        saturn.meta.period = 10_759;
//        planets.add(5, saturn);
//
//        Subject uranus = new Subject(6, 400, 1200, new Color(0x8DFFFD));
//        uranus.meta.name = "Uranus";
//        uranus.meta.period = 30_685;
//        planets.add(6, uranus); // uran
//
//        Subject neptune = new Subject(6, 800, 1800, new Color(0x0502BD));
//        neptune.meta.name = "Neptune";
//        neptune.meta.period = 60_190;
//        planets.add(7, neptune); // neptun
//
//        planets.getChild(2).add(0, new Subject(2, 5 / 13.0, 5, new Color(0xDADDD8)));
//        planets.getChild(3).add(0, new Subject(1, 0.2, 3, new Color(0xDADDD8)));
//
//        planets.getChild(4).add(0, new Subject(2, 1, 10, new Color(0xDADDD8)));
//        planets.getChild(4).add(0, new Subject(2, 0.5, 12, new Color(0xDADDD8)));
//        planets.getChild(4).add(0, new Subject(2, 1.2, 14, new Color(0xDADDD8)));
//        planets.getChild(4).add(0, new Subject(2, 0.7, 16, new Color(0xDADDD8)));
//
//
//        planets.getChild(5).add(0, new Subject(2, 0.6, 10, new Color(0xDADDD8)));
//        planets.getChild(5).add(0, new Subject(1, 0.5, 11, new Color(0xDADDD8)));
//        planets.getChild(5).add(0, new Subject(1, 1.2, 13, new Color(0xDADDD8)));
//        planets.getChild(5).add(0, new Subject(2, 0.7, 15, new Color(0xDADDD8)));
//        planets.getChild(5).add(0, new Subject(1, 1.9, 16, new Color(0xDADDD8)));
//        planets.getChild(5).add(0, new Subject(2, 2.5, 17, new Color(0xDADDD8)));
//        planets.getChild(5).add(0, new Subject(1, 1.4, 19, new Color(0xDADDD8)));
//
//
//        asteroids.calcPositions(CENTER);
//        double size;
//        double speed;
//        int radius;
//
//        for (int i = 0; i < 2000; i++) {
//
//            size = Math.random() * Math.random() * 1.1;
//            speed = 10 + Math.random();
//            radius = (int) Math.round(140 + Math.random() * 60 * Math.random() *(Math.random()-0.5));
//
//            Subject aster = new Subject(size, speed, radius, new Color(0xDADDD8));
//            aster.angle = Math.random() *Math.PI * 2;
//            asteroids.add(0, aster);
//        }

        return data;
    }
}
