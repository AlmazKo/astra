package ru.alexlen;

import java.awt.*;

import static java.lang.Math.*;

/**
 * Created by almazko on 23.04.14.
 */
public class Drawer {

    Graphics2D g;

    final public static Color PATH_COLOR = new Color(0x373737);
    final public static Color SELECTED_COLOR = new Color(0xE8E8E8);

    double scale;
    double sizeScale;
    double radiusScale;
    double radiusMoonScale;

    public static Coordinate GCENTER = new Coordinate(400, 350);

    final static float dash1[] = {1.0f};
    final static BasicStroke dashed =
            new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    1.0f, dash1, 0.0f);


    void setScale(double value) {
        scale = value;
        sizeScale = value * 20;
        radiusScale = value * 0.2;
        radiusMoonScale = radiusScale * 10;
    }


    void draw(Graphics2D g) {
        this.g = g;

        if (Main.SELECTED_SUBJECT != null){
            reversDrawSubject(Main.SELECTED_SUBJECT, null, GCENTER);
        } else {
            drawSubject(Main.sun, GCENTER);
        }
    }

    void drawSubject(final Subject s, final Coordinate sgc) {


        Coordinate gc = drawOrbitalSubject(s, sgc, calcRelativePosition(s));
        for (Subject child : s.children) {

            drawSubject(child, gc);
        }
    }

    void reversDrawSubject(Subject s, Subject initChild, final Coordinate gc) {
        drawOrbitalSubject(s, gc, new Coordinate());
        for (Subject child : s.children) {
            if (child != initChild) {
                drawSubject(child, gc);
            }

        }


        if (s.parent != null) {

            Coordinate sgc = calcRelativePosition(s);
            reversDrawSubject(s.parent, s, new Coordinate(gc.x - sgc.x, gc.y - sgc.y));
        }
    }

    Coordinate drawOrbitalSubject(final Subject subject, final Coordinate parentPos, Coordinate relPos) {

        if (subject.depth() >= 2) {
            drawOrbit(subject, parentPos);
        }

        Coordinate sgc = new Coordinate(parentPos.x - relPos.x, parentPos.y - relPos.y);

        final double sSize = subject.size * sizeScale;
        final int x = (int) (sgc.x - sSize);
        final int y = (int) (sgc.y - sSize);
        int diameter = (int) (2 * sSize);

        if (diameter < 2) {
            diameter = 2;
        }

        g.setStroke(new BasicStroke());
        g.setColor(subject.meta.color);
        g.fillOval(x, y, diameter, diameter);

        return sgc;
    }

    private void drawOrbit(Subject subject, Coordinate parentPos) {
        double currentScale = radiusScale;
        if (subject.depth() > 2) {
            currentScale = radiusMoonScale;
        }


        double radius = subject.p.radius * currentScale;
        //  g.setStroke(dashed);
        g.setColor(PATH_COLOR);
        g.drawOval((int) (parentPos.x - radius - 0.5), (int) (parentPos.y - radius - 0.5),
                (int) (2 * radius), (int) (2 * radius));
    }

    Coordinate calcRelativePosition(Subject subject) {
        double currentScale = radiusScale;
        if (subject.depth() > 2) {
            currentScale = radiusMoonScale;
        }

        double gx = subject.p.radius * cos(subject.p.angle) * currentScale;
        double gy = subject.p.radius * sin(subject.p.angle) * currentScale;

        return new Coordinate(gx, gy);
    }
}
