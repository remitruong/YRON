package com.esiea.tp4A.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private String id;
    private PlanetMapImpl map;
    private int mapSize;
    private int laserRange;

    public Game(String id){
        this.id = id;

        Utils utils = new Utils();
        this.mapSize = utils.generateRandMapSize();
        this.map = (PlanetMapImpl) new PlanetMapImpl().initialize(this.mapSize);
        this.map.generateObstacle(this.mapSize);

        this.laserRange = utils.generateRandLaserRange(this.getMapSize());
    }

    public String getId() {
        return id;
    }

    public int getMapSize() {
        return mapSize;
    }

    public PlanetMapImpl getPlanetMap() {
        return this.map;
    }

    public int getLaserRange() {
        return this.laserRange;
    }

    public MarsRoverImpl generateRover(String name) {
        return this.map.generateRover(name, this.getLaserRange());
    }
}
