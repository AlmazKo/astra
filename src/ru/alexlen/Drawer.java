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

    FontStyle miniFont = new FontStyle("JuraLight.ttf", 0xffAAAAAA, 11);

    public static Coordinate GCENTER = new Coordinate(400, 350);

    final static float dash1[] = {1.0f};
    final static BasicStroke dashed =
            new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    1.0f, dash1, 0.0f);


    void setScale(double value) {
        scale = value;
        sizeScale = value;
        radiusScale = value;
        radiusMoonScale = sizeScale;
    }


    void draw(Graphics2D g) {
        this.g = g;

        if (Main.SELECTED_SUBJECT != null) {
            reversDrawSubject(Main.SELECTED_SUBJECT, null, GCENTER);


        } else {
            drawSubject(Main.system, GCENTER);
        }

        drawRuler(100, 1050, 540);
    }

    void drawRuler(final int width, final int posX, final int posY) {

        int x = posX;
        Color even = new Color(0x333333);
        Color odd = new Color(0xCCCCCC);


        for (int i = 0; i <= width; i += 10) {
            if (i % 20 == 0) {
                g.setColor(odd);
            } else {
                g.setColor(even);
            }
            g.fillRect(x, posY, 10, 2);
            x += 10;
        }

        drawString(String.format("%2.0f km", 1 / scale * width / 1000.0), posX, posY + (int) miniFont.size, miniFont);

    }

    void drawSubject(final Subject s, final Coordinate sgc) {


        Coordinate gc = drawOrbitalSubject(s, sgc, calcRelativePosition(s));
        for (Subject child : s.children) {

            drawSubject(child, gc);
        }
    }

    void drawString(String text, int x, int y, FontStyle style) {

        final Color colorBackup = g.getColor();
        final Font fontBackup = g.getFont();

        if (fontBackup != style.font) {
            g.setFont(style.getFont());
        }

        if (colorBackup != style.color) {
            g.setColor(style.color);
        }

        g.drawString(text, x, y);

        if (fontBackup != style.font) {
            g.setFont(fontBackup);
        }

        if (colorBackup != style.color) {
            g.setColor(colorBackup);
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

        if (subject.meta.type == SubjectType.PLANET) {
            drawOrbit(subject, parentPos);
        } else if (subject == Main.SELECTED_SUBJECT) {
            drawOrbit(subject, parentPos);
        }

        Coordinate sgc = new Coordinate(parentPos.x - relPos.x, parentPos.y - relPos.y);


        if (subject instanceof Ship) {
            drawShip((Ship) subject, sgc);
            return sgc;
        }


        final double sSize = subject.size * sizeScale * subject.meta.scale;
        final int x = (int) (sgc.x - sSize);
        final int y = (int) (sgc.y - sSize);
        double diameter = 2 * sSize;

        if (diameter < 0.005) {

            if (subject == Main.SELECTED_SUBJECT) {
                drawSelected(x, y, diameter, diameter);
            }

            return sgc;
        }

        if (diameter < 1) {
            diameter = 1;
        }

//        if (subject == Main.SELECTED_SUBJECT) {
//            drawSelected(x, y, diameter, diameter);
//        }

        g.setStroke(new BasicStroke());
        g.setColor(subject.meta.color);
        g.fillOval(x, y, (int) diameter, (int) diameter);

        return sgc;
    }

    void drawShip(Ship ship, Coordinate sgc) {

        final int x = (int) (sgc.x - 1);
        final int y = (int) (sgc.y - 1);
        final int diameter = 3;


        if (Main.isFastBlinked) {

            g.setStroke(new BasicStroke());
            g.setColor(ship.meta.color);
            g.fillOval(x, y, diameter, diameter);
        }

    }

    void drawSelected(int x, int y, double w, double h) {
        if (Main.isBlinked) {
            final int border = (int) Math.round(w / 50.0 + 0.5);
            g.setColor(SELECTED_COLOR);
            g.fillOval(x - border, y - border, (int) w + 2 * border, (int) h + 2 * border);
        }
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

    Coordinate calcRelativePosition(final Subject subject) {

        Coordinate abs = calcAbsPosition(subject);

        if (abs.x == 0) {
            return abs;
        }
        final double scale = getRadiusScale(subject);

        return new Coordinate(abs.x * scale, abs.y * scale);
    }


    double getRadiusScale(final Subject subject) {


        double currentScale = radiusScale;
        if (subject.meta.type == SubjectType.MOON) {
            currentScale = radiusMoonScale;
        }

        if (subject.meta.type == SubjectType.SHIP) {
            currentScale = radiusMoonScale;
        }

        return currentScale;
    }


    static Coordinate calcAbsPosition(Subject subject) {

        double gx, gy;

        if (subject.meta.type == SubjectType.SHIP) {
            gx = (subject.parent.size + subject.p.radius) * cos(subject.p.angle);
            gy = (subject.parent.size + subject.p.radius) * sin(subject.p.angle);
        } else {
            gx = subject.p.radius * cos(subject.p.angle);
            gy = subject.p.radius * sin(subject.p.angle);
        }

        return new Coordinate(gx, gy);
    }
}
