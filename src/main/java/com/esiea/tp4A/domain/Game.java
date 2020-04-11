package com.esiea.tp4A.domain;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {
    private Integer id;
    private PlanetMapImpl map;
    private List<MarsRover> roverList;

    public Game(int range, int size, Integer id){
        this.id = id;
        this.map = (PlanetMapImpl) new PlanetMapImpl().initialize(size);
        this.map.setLaserRange(range);

        //generate obstacle
    }

    /*public void generateRover(String name){
        Position roverPosition = generatePosition();
        MarsRoverImpl rover = new MarsRoverImpl(roverPosition, name);
        this.roverList.add(rover);
    }*/

    public Integer getId() {
        return id;
    }

}
