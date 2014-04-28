package ru.alexlen;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by almazko on 23.04.14.
 */
public class Data {

    static Subject populate() {
        ArrayList<Subject> data = new ArrayList<>();

        Subject sun = new Subject(2392e+4);
////        Subject system = new Subject(1392e+6);
        sun.meta.color = new Color(0xFEE640);
        sun.meta.name = "Sun";
        sun.meta.type = SubjectType.STAR;


        Subject mercury = new Subject(2439e+3, 87.97 * 86_400, new PolarCoordinate(0, 58e+9));
        mercury.meta.name = "Mercury";
        mercury.meta.type = SubjectType.PLANET;
        mercury.meta.period = 115;
        mercury.meta.color = new Color(0xFEC07D);
        sun.add(mercury);

        Subject venus = new Subject(6051e+3, 224.7 * 86_400, new PolarCoordinate(0, 108e+9));
        venus.meta.name = "Venus";
        venus.meta.type = SubjectType.PLANET;
        venus.meta.period = 224;
        venus.meta.color = new Color(0x919D34);
        sun.add(venus);
//
        Subject earth = new Subject(6371e+3, 365.0 * 86_400, new PolarCoordinate(0, 149e+9));
        earth.meta.name = "Earth";
        earth.meta.type = SubjectType.PLANET;
        earth.meta.period = 365;
        earth.meta.color = new Color(0x15ABFF);

        Subject moon = new Subject(1738e+3, 29.5 * 86_400, new PolarCoordinate(0, 384e+6));
        moon.meta.name = "Moon";
        moon.meta.type = SubjectType.MOON;
        moon.meta.period = 29;
        moon.meta.color = new Color(0xE9E4EA);
        earth.add(moon);
        sun.add(earth);

        Subject mars = new Subject(4439e+3, 779.0 * 86_400, new PolarCoordinate(0, 200e+9));
        mars.meta.name = "Mars";
        mars.meta.type = SubjectType.PLANET;
        mars.meta.color = new Color(0xffff0000);
        mars.meta.period = 779;
        sun.add(mars);
//
        Subject jupiter = new Subject(69_911e+3, 4332.6 * 86_400, new PolarCoordinate(0, 770e+9));
        jupiter.meta.name = "Jupiter";
        jupiter.meta.type = SubjectType.PLANET;
        jupiter.meta.period = 4_332;
        jupiter.meta.color = new Color(0x900000);
        sun.add(jupiter);

        Subject io = new Subject(1821e+3, 42.5 * 3600, new PolarCoordinate(0, 421e+6));
        io.meta.name = "Io";
        io.meta.type = SubjectType.MOON;
        io.meta.color = new Color(0xD2CE1E);
        jupiter.add(io);

        Subject europa = new Subject(1560e+3, 3.551 * 86400, new PolarCoordinate(0, 671e+6));
        europa.meta.name = "Europa";
        europa.meta.type = SubjectType.MOON;
        europa.meta.color = new Color(0xDBAEAD);
        jupiter.add(europa);

        Subject ganymede = new Subject(2634e+3, 7.1 * 86400, new PolarCoordinate(0, 1070e+6));
        ganymede.meta.name = "Ganymede";
        ganymede.meta.type = SubjectType.MOON;
        ganymede.meta.color = new Color(0xC6B4B3);
        jupiter.add(ganymede);

        Subject callisto = new Subject(2410e+3, 16.7 * 86400, new PolarCoordinate(0, 1882e+6));
        callisto.meta.name = "Callisto";
        callisto.meta.type = SubjectType.MOON;
        callisto.meta.color = new Color(0xB4A891);
        jupiter.add(callisto);


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
//        asteroids.calcPositions(SCREEN_CENTER);
        double size;
        double rate;
        double radius;
        double angle;
        PolarCoordinate p;
        Subject aster;
//
        for (int i = 0; i < 2000; i++) {

            size = 2000e+3 * Math.random() * Math.random();
            rate = (800 + Math.random() * 100) * 86_400;
            radius = 400e9 + Math.random() * Math.random() * 60e9 * (Math.random() - 0.5);

            angle = Math.random() * Math.PI * 2;

            aster = new Subject(size, rate, new PolarCoordinate(angle, radius));
            aster.meta.type = SubjectType.ASTEROID;
            sun.add(aster);
        }

        return sun;
    }
}
