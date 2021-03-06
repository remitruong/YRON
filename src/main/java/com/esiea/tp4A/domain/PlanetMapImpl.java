package com.esiea.tp4A.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;

public class PlanetMapImpl implements PlanetMap, Serializable {
    private Set<Position> obstaclePositions;
    private HashMap<String, MarsRoverImpl> roverList;
    private int sizeOfTheMap;
    private final Utils utils = new Utils();

    public PlanetMap initialize() {
        this.sizeOfTheMap = 100;
        initialize(new HashSet<>());
        this.roverList = new HashMap<>();
        return this;
    }

    public PlanetMap initialize(int size) {
        this.sizeOfTheMap = size;
        initialize(new HashSet<>());
        this.roverList = new HashMap<>();
        return this;
    }

    public PlanetMap initialize(Set<Position> positions) {
        this.obstaclePositions = positions;
        return this;
    }

    public void generateObstacle (int size){
        int nbObstacle = (int) (size*0.15);
        int i = 0;
        while(i < nbObstacle){
            int randomX = utils.randomIntForPosition(size);
            int randomY = utils.randomIntForPosition(size);
            Position position = Position.of(randomX, randomY, Direction.NORTH);
            if(isObstaclePositionOnMap(position)){
                i = +0;
            }else{
                addObstaclePosition(position);
                i++;
            }
        }
    }

    public MarsRoverImpl generateRover(String name, int laserRange){
        Position roverPosition = utils.randomPosition(this.getSizeOfTheMap(), this);
        MarsRoverImpl rover = new MarsRoverImpl(roverPosition, name, this, laserRange);
        this.roverList.put(name, rover);

        return rover;
    }

    public MarsRoverImpl generateRover(String name, Position pos, int laserRange){
        MarsRoverImpl rover = new MarsRoverImpl(pos, name, this, laserRange);
        this.roverList.put(name, rover);

        return rover;
    }

    @JsonProperty("players")
    public HashMap getRoverList() {
        return this.roverList;
    }

    @JsonProperty("obstacles")
    public Set<Position> getObstaclePositions() {
        return obstaclePositions;
    }

    @Override
    public Set<Position> obstaclePositions() {
        return this.obstaclePositions;
    }

    // Add an obstacle, if the obstacle position is already used : return null
    public boolean addObstaclePosition(Position position) {
        if (!this.isObstaclePositionOnMap(position)) {
            this.obstaclePositions.add(position);
            return true;
        }

        return false;
    }

    // Remove an object of the map, and return true if the obstacle is found, false if not
    public boolean destroyObjectAtPosition(Position position) {
        MarsRoverImpl roverAtPosition = isOtherRoverAtPosition(position); // null if no rover

        if(this.isObstaclePositionOnMap(position)) {
            this.obstaclePositions.removeIf(pos -> pos.getX() == position.getX() && pos.getY() == position.getY());
            return true;
        }
        else if(roverAtPosition != null) {
            roverAtPosition.kill();
            return true;
        }

        return false;
    }

    public boolean isObstaclePositionOnMap(Position position) {
        if(this.obstaclePositions != null) {
            for (Position p : this.obstaclePositions) {
                if (p.getX() == position.getX() && p.getY() == position.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public MarsRoverImpl isOtherRoverAtPosition(Position position) {
        if(this.roverList != null) {
            for (MarsRoverImpl rover : this.roverList.values()) {
                if (rover.getPosition().getX() == position.getX() && rover.getPosition().getY() == position.getY()) {
                    return rover;
                }
            }
        }
        return null;
    }

    public MarsRoverImpl getRoverByName(String name) {
        return this.roverList.get(name);
    }

    @JsonIgnore
    public int getSizeOfTheMap() {
        return this.sizeOfTheMap;
    }

    boolean isLimitOfMap(Position position){
        return position.getX() == (getSizeOfTheMap() / 2) || position.getY() == (getSizeOfTheMap() / 2)
            || position.getX() == -((getSizeOfTheMap() / 2) - 1) || position.getY() == -((getSizeOfTheMap() / 2) - 1);
    }
}
