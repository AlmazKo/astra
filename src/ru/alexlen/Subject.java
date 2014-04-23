package ru.alexlen;

import java.awt.*;
import java.util.HashSet;
import java.util.Map;

//import static java.awt.Color.*;
import static java.lang.Math.*;
import static ru.alexlen.Main.*;

/**
 * Created by almazko on 20.04.14.
 */
public class Subject {


    final static float dash1[] = {1.0f};
    final static BasicStroke dashed =
            new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    1.0f, dash1, 0.0f);


    Meta meta = new Meta();

    boolean isSelected = false;

    Coordinate c = new Coordinate(0, 0);
    Coordinate center;

    double angle = 0;

    private final double size;
    private final double speed;
    private final int radius;
    private final Color color;



    public Subject(double size, double speed, int radius) {
        this.size = size;
        this.speed = speed;
        this.radius = radius;
        this.color = Color.BLACK;
    }

    public Subject(double size, double speed, int radius, Color color) {
        this.size = size;
        this.speed = speed;
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics2D g) {

        if (radius > 0 && size > 1.5) {
            g.setColor(PATH_COLOR);
            g.setStroke(dashed);
            g.drawOval(center.x - (int) (radius* SCALE), center.y - (int)( radius* SCALE), (int) (radius * 2* SCALE), (int)(radius * 2* SCALE));
        }


        if (size * SCALE < 1) {
            return;
        }

        int x,y,diameter;
        x = c.x - (int) (size / 2 * SCALE);
        y = c.y - (int) (size / 2 * SCALE);
        diameter = (int) (size * SCALE);

        if (isSelected && isBlinked) {
            g.setColor(SELECTED_COLOR);
            g.fillOval((int) (x - 1 * SCALE), (int) (y - 1 * SCALE), (int) (diameter + 2 * SCALE), (int) (diameter + 2 * SCALE));

        }

        g.setStroke(new BasicStroke());
        g.setColor(color);
        g.fillOval(x, y, diameter,diameter);
    }


    public Coordinate set(Coordinate co) {
        center = co;
        double offset = 2 * PI * Main.RATE / (speed);

        // TODO add counting
        angle += offset;

        c.x = co.x + (int) round(radius * SCALE * cos(angle));
        c.y = co.y + (int) round(radius * SCALE * sin(angle));

        return c;

    }


}
