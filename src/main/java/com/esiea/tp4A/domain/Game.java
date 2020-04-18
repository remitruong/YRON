package com.esiea.tp4A.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

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

    @JsonIgnore
    public int getMapSize() {
        return mapSize;
    }

    @JsonProperty("map")
    public PlanetMapImpl getPlanetMap() {
        return this.map;
    }

    @JsonIgnore
    public int getLaserRange() {
        return this.laserRange;
    }

    public MarsRoverImpl generateRover(String name) {
        return this.map.generateRover(name, this.getLaserRange());
    }
}
