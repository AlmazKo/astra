package ru.alexlen;

import java.util.ArrayList;
import java.util.List;

import static ru.alexlen.Main.TIME_SPEED;

/**
 * Created by almazko on 23.04.14.
 */
 class Planet {
    Subject planet;
    List<Subject> moons;

    Planet(Subject planet, List<Subject> moons) {
        this.planet = planet;
        this.moons = moons;
    }
    
    Planet(Subject planet, Subject moon) {
        this.planet = planet;
        moon.parent = planet;
        this.moons = new ArrayList<>(1);
        this.moons.add(moon);
    }

    Planet(Subject planet) {
        this.planet = planet;
        this.moons = new ArrayList<>(0);
    }

    public void move(double time) {
        planet.move(time);
        for (Subject moon : moons) {
            moon.move(time);
        }
    }
}
