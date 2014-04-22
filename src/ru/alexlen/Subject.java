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

    int x = 0;
    int y = 0;

    Coordinate c = new Coordinate(0, 0);
    Coordinate center;

    double angle = 0;

    private final int size;
    private final double speed;
    private final int radius;
    private final Color color;


    public Subject(int size, double speed, int radius) {
        this.size = size;
        this.speed = speed;
        this.radius = radius;
        this.color = Color.BLACK;
    }

    public Subject(int size, double speed, int radius, Color color) {
        this.size = size;
        this.speed = speed;
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics2D g) {

        if (radius > 0) {
            g.setColor(PATH_COLOR);
            g.setStroke(dashed);
            g.drawOval(center.x - (int) (radius* SCALE), center.y - (int)( radius* SCALE), (int) (radius * 2* SCALE), (int)(radius * 2* SCALE));
        }


        g.setStroke(new BasicStroke());
        g.setColor(color);



        g.fillOval(c.x - (int) (size / 2 * SCALE), c.y - (int) (size / 2 * SCALE), (int) (size * SCALE), (int) (size * SCALE));



    }

    public Coordinate set(Coordinate co) {
        center = co;
        double offset = 2 * PI * Main.RATE / (speed);
        angle += offset;

        c.x = co.x + (int) round(radius * SCALE * cos(angle));
        c.y = co.y + (int) round(radius * SCALE * sin(angle));

        return c;

    }


}
