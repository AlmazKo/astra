package ru.alexlen;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by almazko on 23.04.14.
 */
public class Data {

    final static int DAY = 86_400;
    final static int HOUR = 3_600;
    final static int MINUTE = 60;
    final static int SECOND = 1;
    final static int DEFAULT_COLOR = 0xDDDDDD;

    /**
     * @link http://ssd.jpl.nasa.gov/?sat_elem
     */
    static Subject populate() {
        ArrayList<Subject> data = new ArrayList<>();

        Subject sun = new Subject(1392e+6);
        sun.meta.color = new Color(0xFEE640);
        sun.meta.name = "Sun";
        sun.meta.type = SubjectType.STAR;
        sun.meta.scale = 0.5f;


        Subject mercury = new Subject(2439e+3, 87.97 * DAY, new PolarCoordinate(0, 58e+9));
        mercury.meta.name = "Mercury";
        mercury.meta.type = SubjectType.PLANET;
        mercury.meta.period = 115;
        mercury.meta.color = new Color(0xFEC07D);
        sun.add(mercury);

        Subject venus = new Subject(6051e+3, 224.7 * DAY, new PolarCoordinate(0, 108e+9));
        venus.meta.name = "Venus";
        venus.meta.type = SubjectType.PLANET;
        venus.meta.period = 224;
        venus.meta.color = new Color(0x919D34);
        sun.add(venus);

        Subject earth = new Subject(6051e+3, 365.0 * DAY, new PolarCoordinate(0, 149e+9));
        earth.meta.name = "Earth";
        earth.meta.type = SubjectType.PLANET;
        earth.meta.period = 365;
        earth.meta.color = new Color(0x15ABFF);

        Subject moon = new Subject(1738e+3, 29.5 * DAY, new PolarCoordinate(0, 384e+6));
        moon.meta.name = "Moon";
        moon.meta.type = SubjectType.MOON;
        moon.meta.period = 29;
        moon.meta.color = new Color(0xE9E4EA);
        earth.add(moon);
        sun.add(earth);

        Subject mars = new Subject(4439e+3, 779.0 * DAY, new PolarCoordinate(0, 200e+9));
        mars.meta.name = "Mars";
        mars.meta.type = SubjectType.PLANET;
        mars.meta.color = new Color(0xffff0000);
        mars.meta.period = 779;
        sun.add(mars);

        Subject fobos = new Subject(11.1e+3, 7 * HOUR + 39 * MINUTE + 14, new PolarCoordinate(0, 9.377e+6));
        fobos.meta.name = "Fobos";
        fobos.meta.type = SubjectType.MOON;
        fobos.meta.color = new Color(0x9FACB6);
        fobos.meta.scale = 10;
        mars.add(fobos);


        Subject deimos = new Subject(12e+3, 30 * HOUR + 17 * MINUTE + 55, new PolarCoordinate(0, 23.458e+6));
        deimos.meta.name = "Deimos";
        deimos.meta.type = SubjectType.MOON;
        deimos.meta.color = new Color(0xB1B6AE);
        deimos.meta.scale = 10;
        mars.add(deimos);


        Subject jupiter = new Subject(69_911e+3, 4332.6 * DAY, new PolarCoordinate(0, 770e+9));
        jupiter.meta.name = "Jupiter";
        jupiter.meta.type = SubjectType.PLANET;
        jupiter.meta.period = 4_332;
        jupiter.meta.color = new Color(0x900000);
        jupiter.meta.scale = 1;
        sun.add(jupiter);

        Subject io = new Subject(1821e+3, 42.5 * DAY, new PolarCoordinate(0, 421e+6));
        io.meta.name = "Io";
        io.meta.type = SubjectType.MOON;
        io.meta.color = new Color(0xD2CE1E);
        io.meta.scale = 2;
        jupiter.add(io);

        Subject europa = new Subject(1560e+3, 3.551 * DAY, new PolarCoordinate(0, 671e+6));
        europa.meta.name = "Europa";
        europa.meta.type = SubjectType.MOON;
        europa.meta.color = new Color(0xDBAEAD);
        europa.meta.scale = 2;
        jupiter.add(europa);

        Subject ganymede = new Subject(2634e+3, 7.1 * DAY, new PolarCoordinate(0, 1070e+6));
        ganymede.meta.name = "Ganymede";
        ganymede.meta.type = SubjectType.MOON;
        ganymede.meta.color = new Color(0xC6B4B3);
        ganymede.meta.scale = 2;
        jupiter.add(ganymede);

        Subject callisto = new Subject(2410e+3, 16.7 * DAY, new PolarCoordinate(0, 1882e+6));
        callisto.meta.name = "Callisto";
        callisto.meta.type = SubjectType.MOON;
        callisto.meta.color = new Color(0xB4A891);
        callisto.meta.scale = 2;
        jupiter.add(callisto);

// 60 268
        Subject saturn = new Subject(60_268e+3, 10_759 * DAY, new PolarCoordinate(0, 1_433e+9));
        saturn.meta.name = "Saturn";
        saturn.meta.type = SubjectType.PLANET;
        saturn.meta.color = new Color(0xDFD864);
        saturn.meta.period = 10_759;
        sun.add(saturn);

        Subject mimas = new Subject(397e+3, 0.942 * DAY, new PolarCoordinate(0, 185.4e+6));
        mimas.meta.name = "Mimas";
        mimas.meta.type = SubjectType.MOON;
        mimas.meta.color = new Color(0xDFD864);
        saturn.add(mimas);

        Subject enceladus = new Subject(499e+3, 1.37 * DAY, new PolarCoordinate(0, 237.4e+6));
        enceladus.meta.name = "Enceladus";
        enceladus.meta.type = SubjectType.MOON;
        enceladus.meta.color = new Color(0xBAE8EA);
        saturn.add(enceladus);

        Subject tethys = new Subject(1060e+3, 1.888 * DAY, new PolarCoordinate(0, 294.6e+6));
        enceladus.meta.name = "Tethys";
        enceladus.meta.type = SubjectType.MOON;
        enceladus.meta.color = new Color(0xE3DBE2);
        saturn.add(tethys);


        Subject dione = new Subject(1118e+3, 2.737 * DAY, new PolarCoordinate(0, 377.4e+6));
        dione.meta.name = "Dione";
        dione.meta.type = SubjectType.MOON;
        dione.meta.color = new Color(0xE3DBE2);
        saturn.add(dione);

        Subject rhea = new Subject(1528e+3, 4.518 * DAY, new PolarCoordinate(0, 527e+6));
        rhea.meta.name = "Rhea";
        rhea.meta.type = SubjectType.MOON;
        rhea.meta.color = new Color(0xE3DBE2);
        saturn.add(rhea);


        Subject titan = new Subject(5150e+3, 15.95 * DAY, new PolarCoordinate(0, 1_221e+6));
        titan.meta.name = "Titan";
        titan.meta.type = SubjectType.MOON;
        titan.meta.color = new Color(0xE3D8A1);
        saturn.add(titan);


        Subject iapetus = new Subject(1436e+3, 79.33 * DAY, new PolarCoordinate(0, 3_560e+6));
        iapetus.meta.name = "Iapetus";
        iapetus.meta.type = SubjectType.MOON;
        iapetus.meta.color = new Color(0xE3E2CF);
        saturn.add(iapetus);

        Subject uranus = new Subject(25_559e+3, 30_685d * DAY, new PolarCoordinate(0, 2_876e+9));
        uranus.meta.name = "Uranus";
        uranus.meta.type = SubjectType.PLANET;
        uranus.meta.color = new Color(0x93D6DF);
        uranus.meta.period = 10_759;
        sun.add(uranus);


        Subject miranda = new Subject(235.8e+3, 1.413 * DAY, new PolarCoordinate(0, 129e+6));
        miranda.meta.name = "Miranda";
        miranda.meta.type = SubjectType.MOON;
        miranda.meta.color = new Color(DEFAULT_COLOR);
        uranus.add(miranda);

        Subject ariel = new Subject(578.9e+3, 2.520 * DAY, new PolarCoordinate(0, 190e+6));
        ariel.meta.name = "Ariel";
        ariel.meta.type = SubjectType.MOON;
        ariel.meta.color = new Color(DEFAULT_COLOR);
        uranus.add(ariel);

        Subject umbriel = new Subject(1169.4e+3, 4.144 * DAY, new PolarCoordinate(0, 266e+6));
        umbriel.meta.name = "Umbriel";
        umbriel.meta.type = SubjectType.MOON;
        umbriel.meta.color = new Color(DEFAULT_COLOR);
        uranus.add(umbriel);

        Subject titania = new Subject(1576.8e+3, 8.706 * DAY, new PolarCoordinate(0, 436e+6));
        titania.meta.name = "Titania";
        titania.meta.type = SubjectType.MOON;
        titania.meta.color = new Color(DEFAULT_COLOR);
        uranus.add(titania);

        Subject oberon = new Subject(1522.8e+3, 13.46 * DAY, new PolarCoordinate(0, 583e+6));
        oberon.meta.name = "Oberon";
        oberon.meta.type = SubjectType.MOON;
        oberon.meta.color = new Color(DEFAULT_COLOR);
        uranus.add(oberon);


        Subject neptune = new Subject(24_764e+3, 60_190d * DAY, new PolarCoordinate(0, 4_503e+9));
        neptune.meta.name = "Neptune";
        neptune.meta.type = SubjectType.PLANET;
        neptune.meta.color = new Color(0x335CE9);
        neptune.meta.period = 60_190;
        sun.add(neptune);


        Subject triton = new Subject(2706.8e+3, 5.877 * DAY, new PolarCoordinate(0, 354e+6));
        triton.meta.name = "Triton";
        triton.meta.type = SubjectType.MOON;
        triton.meta.color = new Color(DEFAULT_COLOR);
        neptune.add(triton);

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
