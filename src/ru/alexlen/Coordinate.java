package ru.alexlen;

/**
 * Created by almazko on 23.04.14.
 */
final class Coordinate {

    public double x;
    public double y;

    Coordinate() {
        x = y = 0;
    }

    Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Coordinate toScalar() {
        return new Coordinate((int) x, (int) y);
    }

    @Override
    public String toString() {
        return String.format("[x=%1.2f, y=%1.2f]", x, y);
    }
}
