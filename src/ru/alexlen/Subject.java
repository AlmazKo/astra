package ru.alexlen;

import java.util.ArrayList;

import static java.lang.Math.PI;

/**
 * Created by almazko on 20.04.14.
 */
public class Subject {

    Subject parent;
    ArrayList<Subject> children = new ArrayList<>(0);
    Meta meta = new Meta();
    PolarCoordinate p;

    /*
     * angular velocity
     */
    private final double velocity;

   final double size;

    public Subject(double size, double rate, PolarCoordinate p) {
        this.velocity = 2 * PI / rate;
        this.size = size;
        this.p = p;
    }

    public Subject(double size, PolarCoordinate p) {
        this.velocity = 0;
        this.size = size;
        this.p = p;
    }
    public Subject(double size) {
        this.velocity = 0;
        this.size = size;
        this.p = new PolarCoordinate(0,0);
    }

//    public void draw(Graphics2D g) {
//
//        if (radius > 0 && size > 1.5) {
//            g.setColor(PATH_COLOR);
//            g.setStroke(dashed);
//            g.drawOval(center.x - (int) (radius * SCALE), center.y - (int) (radius * SCALE), (int) (radius * 2 * SCALE), (int) (radius * 2 * SCALE));
//        }
//
//
//        if (size * SCALE < 1) {
//            return;
//        }
//
//        int x, y, diameter;
//        x = c.x - (int) (size / 2 * SCALE);
//        y = c.y - (int) (size / 2 * SCALE);
//        diameter = (int) (size * SCALE);
//
//        if (isSelected && isBlinked) {
//            g.setColor(SELECTED_COLOR);
//            g.fillOval((int) (x - 1 * SCALE), (int) (y - 1 * SCALE), (int) (diameter + 2 * SCALE), (int) (diameter + 2 * SCALE));
//
//        }
//
//        g.setStroke(new BasicStroke());
//        g.setColor(color);
//        g.fillOval(x, y, diameter, diameter);
//    }


//    public Coordinate calcPositions(Coordinate co) {
//        center = co;
//        double offset = 2 * PI * Main.RATE * TIME_SPEED / (speed);
//
//        // TODO add counting
//        angle += offset;
//
//        c.x = co.x + (int) round(radius * SCALE * cos(angle));
//        c.y = co.y + (int) round(radius * SCALE * sin(angle));
//
//        return c;
//
//    }

    void move(double msTime) {
        if (p.radius > 0) {
            p.angle += velocity * msTime;
        }

        for (Subject child : children) {
            child.move(msTime);
        }
    }

    void add(Subject s) {
        children.add(s);
        s.parent = this;
    }

    int depth() {
        if (parent != null){
            return 1 + parent.depth();
        }

        return 1;
    }
}
