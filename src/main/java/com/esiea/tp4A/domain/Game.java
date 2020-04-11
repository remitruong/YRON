package com.esiea.tp4A.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private Integer id;
    private PlanetMapImpl map;
    private ArrayList<MarsRover> roverList;
    private int mapSize;

    public Game(int range, int size, Integer id){
        this.id = id;
        this.mapSize = size;
        this.map = (PlanetMapImpl) new PlanetMapImpl().initialize(size);
        this.map.setLaserRange(range);
        this.map.generateObstacle(size);
        this.roverList = new ArrayList<>();
    }

    public void generateRover(String name){
        Position roverPosition = this.map.randomPositionRover(this.getMapSize());
        MarsRoverImpl rover = new MarsRoverImpl(roverPosition, name, map);
        this.roverList.add(rover);
    }

    public Integer getId() {
        return id;
    }

    public int getMapSize() {
        return mapSize;
    }

    public ArrayList<MarsRover> getRoverList() {
        return roverList;
    }

}
