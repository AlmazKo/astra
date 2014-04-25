package ru.alexlen;

import java.awt.*;

import static java.lang.Math.*;
import static ru.alexlen.Main.*;

/**
 * Created by almazko on 23.04.14.
 */
public class Drawer {

    Graphics2D g;

    final public static Color PATH_COLOR = new Color(0x373737);
    final public static Color SELECTED_COLOR = new Color(0xE8E8E8);

    public static double SCALE = 0.000000005;
    final public static double SIZE_SCALE = SCALE * 10;
    final public static double RADIUS_SCALE = SCALE * 0.2;
    final public static double RADIUS_MOON_SCALE = RADIUS_SCALE * 20;

    public static Coordinate SCREEN_CENTER = new Coordinate(400, 350);
    public static GeoCoordinate GCENTER = new GeoCoordinate(400, 350);
    public static Subject SCENTER;


    final static float dash1[] = {1.0f};
    final static BasicStroke dashed =
            new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    1.0f, dash1, 0.0f);


    void draw(Graphics2D g) {
        this.g = g;

        drawSubject(Main.sun, 0, 0);


//        if (Main.sun == Main.SELECTED_SUBJECT) {
//
//        }

//        drawOrbitalSubject(Main.sun);
//
//
//        for (Planet planet : Main.planets) {
//            drawPlanet(g, planet);
//        }
    }

    void drawSubject(Subject s, double gx, double gy) {

        GeoCoordinate gc = drawOrbitalSubject(s,gx,gy);
        for (Subject child : s.children) {
            drawSubject(child, gc.x, gc.y);
        }
    }

    static void drawOrbitalSubject0(Graphics2D g, Subject s) {
        if (s.p.radius > 0) {
            g.setColor(PATH_COLOR);

            double radius = s.p.radius * RADIUS_SCALE;
//            g.setStroke(dashed);
            g.drawOval(SCREEN_CENTER.x - (int) radius, SCREEN_CENTER.y - (int) radius, (int) (2 * radius), (int) (2 * radius));
        }

//        if (s.size * SCALE < 1) {
//            return;
//        }

        int x, y, diameter;
        double gx, gy;

        gx = s.p.radius * cos(s.p.angle) * RADIUS_SCALE;
        gy = s.p.radius * sin(s.p.angle) * RADIUS_SCALE;

        x = (int) (SCREEN_CENTER.x - gx - s.size / 2 * SIZE_SCALE);
        y = (int) (SCREEN_CENTER.y - gy - s.size / 2 * SIZE_SCALE);
        diameter = (int) (s.size * SIZE_SCALE);


        if (s.size * SIZE_SCALE < 1) {
            diameter = 1;
        }
//        if (isSelected && isBlinked) {
//            g.setColor(SELECTED_COLOR);
//            g.fillOval((int) (x - 1 * SCALE), (int) (y - 1 * SCALE), (int) (diameter + 2 * SCALE), (int) (diameter + 2 * SCALE));
//
//        }

        g.setStroke(new BasicStroke());
        g.setColor(s.meta.color);
        g.fillOval(x, y, diameter, diameter);
    }

    GeoCoordinate drawOrbitalSubject(Subject s, double gx, double gy) {

        int x, y, diameter;

        double scale = RADIUS_SCALE;
        if (s.depth() > 2) {
            scale *=10;
        }

        gx += s.p.radius * cos(s.p.angle) * scale;
        gy += s.p.radius * sin(s.p.angle) * scale;


//        /*else {
//            gx = s.p.radius * cos(s.p.angle) * RADIUS_MOON_SCALE;
//            gy = s.p.radius * sin(s.p.angle) * RADIUS_MOON_SCALE;
//
//            gx += s.parent.p.radius * cos(s.parent.p.angle) * RADIUS_SCALE;
//            gy += s.parent.p.radius * sin(s.parent.p.angle) * RADIUS_SCALE;
//        }*/

        x = (int) (SCREEN_CENTER.x - gx - s.size * SIZE_SCALE);
        y = (int) (SCREEN_CENTER.y - gy - s.size * SIZE_SCALE);

        diameter = (int) (s.size * 2 * SIZE_SCALE);

        if ((s.size * 2 * SIZE_SCALE) < 1) {
            diameter = 1;
        }

        g.setStroke(new BasicStroke());
        g.setColor(s.meta.color);
        g.fillOval(x, y, diameter, diameter);

        return new GeoCoordinate(gx, gy);
    }

    static void drawPlanetOrbit(Graphics2D g, Planet p) {
        double radius = p.planet.p.radius * RADIUS_SCALE;


        //  g.setStroke(dashed);
        g.setColor(PATH_COLOR);
        g.drawOval((int) (SCREEN_CENTER.x - radius - 0.5), (int) (SCREEN_CENTER.y - radius - 0.5), (int) (2 * radius), (int) (2 * radius));

    }


//    static void drawPlanet(Graphics2D g, Planet p) {
//
//        drawPlanetOrbit(g, p);
//        drawOrbitalSubject(g, p.planet);
//        for (Subject moon : p.moons) {
//            drawOrbitalSubject(g, moon);
//        }
//
////
////            g.setColor(PATH_COLOR);
////
//
////
////        int x, y, diameter;
////        double gx, gy;
////
////        gx =p.planet.p.radius * cos(s.p.angle) * RADIUS_SCALE;
////        gy =s.p.radius * sin(s.p.angle) * RADIUS_SCALE;
////
////        x = (int) (SCREEN_CENTER.x - gx - s.size / 2 * SIZE_SCALE);
////        y = (int) (SCREEN_CENTER.y - gy - s.size / 2 * SIZE_SCALE);
////        diameter = (int) (s.size * SIZE_SCALE);
//
////        if (isSelected && isBlinked) {
////            g.setColor(SELECTED_COLOR);
////            g.fillOval((int) (x - 1 * SCALE), (int) (y - 1 * SCALE), (int) (diameter + 2 * SCALE), (int) (diameter + 2 * SCALE));
////
////        }
//
////        g.setStroke(new BasicStroke());
////        g.setColor(s.meta.color);
////        g.fillOval(x, y, diameter, diameter);
//    }


}
