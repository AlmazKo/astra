package ru.alexlen;

import ru.alexlen.building.Antenna;
import ru.alexlen.building.RocketFactory;
import ru.alexlen.building.Spaceport;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static java.lang.Math.PI;

/**
 * @author Almazko
 */
public class Game {

    ArrayList<Owner> players = new ArrayList<>();
    Owner player;
    Subject system;

    static LinkedHashMap<String, Building> availableBuildings = new LinkedHashMap<>();

    Game(Subject system) {

        initBuildings();

        player = Owner.createPlayer("Almazko", new Color(0xFF3EED));
        Owner nasa = Owner.createPlayer("NASA", new Color(0x15F5FF));
        players.add(player);
        players.add(nasa);

        Subject earth = system.children.get(2);

        Ship ship1 = new Ship(5, 90 * 60, new PolarCoordinate(PI / 2, 300e+3), player);
        ship1.meta.name = "Space invader I";
        Ship ship2 = new Ship(3, 100 * 60, new PolarCoordinate(0, 310e+3), player);
        ship2.meta.name = "Viking 2012";
        Ship ship3 = new Ship(8, 110 * 60, new PolarCoordinate(PI / 3 * 4, 320e+3), player);
        ship3.meta.name = "Vostok-4";
        Ship ship4 = new Ship(9, 200 * 60, new PolarCoordinate(PI / 3, 36000e+3), player);
        ship4.meta.name = "Space invader II";

        earth.add(ship1);
        earth.add(ship2);
        earth.add(ship3);
        earth.add(ship4);


        Ship nasaMoon = new Ship(5, 50 * 60, new PolarCoordinate(0, 200e+3), nasa);
        ship1.meta.name = "Moon orbiter";


        Subject moon = earth.children.get(0);

        moon.add(nasaMoon);


        player.buildings.add(new Spaceport());
        player.buildings.add(new RocketFactory());
        player.buildings.add(new Antenna());


        player.buildings.get(0).construct();
        player.buildings.get(1).construct();
        player.buildings.get(2).construct();



        player.credit = 10_000;
    }


    ArrayList<Building> getConstructingBuildings() {

        ArrayList<Building> result = new ArrayList<>(0);
        player.buildings.stream().filter(Building::isConstructing).forEach(result::add);
        return result;
    }



    private void initBuildings() {
        availableBuildings.put("antenna", new Antenna());
        availableBuildings.put("rocket_factory", new RocketFactory());
        availableBuildings.put("spaceport", new Spaceport());
        availableBuildings.put("spaceport1", new Spaceport());
        availableBuildings.put("spaceport2", new Spaceport());
        availableBuildings.put("spaceport3", new Spaceport());
        availableBuildings.put("spaceport4", new Spaceport());
        availableBuildings.put("spaceport5", new Spaceport());
    }

}
