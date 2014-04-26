package ru.alexlen;

import static java.lang.Math.*;

/**
 * Created by almazko on 23.04.14.
 */
final class PolarCoordinate {
    double angle;
    double radius;

    PolarCoordinate(double angle, double radius) {
        this.angle = angle;
        this.radius = radius;
    }

    Coordinate toEuсlid() {
        return new Coordinate(radius * cos(angle), radius * sin(angle));
    }
}
