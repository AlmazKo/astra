package ru.alexlen;

import java.awt.*;

//import static java.awt.Color.*;
import static java.lang.Math.*;

/**
 * Created by almazko on 20.04.14.
 */
public class Figure {
    private int x = 0;
    private int y = 0;
    private double angle = 0;

    private final int size;
    private final double speed;
    private final int radius;
    private final Color color;


    public Figure(int size, double speed, int radius) {
        this.size = size;
        this.speed = speed;
        this.radius = radius;
        this.color = Color.BLACK;
    }
    public Figure(int size, double speed, int radius, Color color) {
        this.size = size;
        this.speed = speed;
        this.radius = radius;
        this.color = color;
    }

    public void move(final int x, final int y) {
        this.x+=x;
        this.y+=y;
    }


    public void set(final int centerX, final int centerY) {


        double offset =  2 * PI * Main.RATE /(speed);
        angle +=offset;

        x = centerX + (int) round(radius * cos(angle));
        y = centerY + (int) round(radius * sin(angle));

    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - size/2, y - size/2, size, size);
    }



}
