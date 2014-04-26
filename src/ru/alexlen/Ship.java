package ru.alexlen;

/**
 * Created by almazko on 26.04.14.
 */
public class Ship extends Subject {

    Owner owner;
    public Ship(double size, double rate, PolarCoordinate p, Owner owner) {
        super(size, rate, p);

        this.meta.type = SubjectType.SHIP;
        this.meta.color = owner.color;
    }
}
