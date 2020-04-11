package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {
    private Set<Position> obstaclePositions;
    private int sizeOfTheMap;
    private int laserRange;

    public PlanetMap initialize() {
        this.sizeOfTheMap = 100;
        initialize(new HashSet<>());
        return this;
    }

    public PlanetMap initialize(int size) {
        this.sizeOfTheMap = size;
        initialize(new HashSet<>());
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
            int randomX = randomInt(size);
            int randomY = randomInt(size);
            Position position = Position.of(randomX, randomY, Direction.NORTH);
            if(isPositionOnMap(position)){
                i = +0;
            }else{
                addObstaclePosition(position);
                i++;
            }
        }
    }

    public Position randomPositionRover(int size){
        int randomX = randomInt(size);
        int randomY = randomInt(size);
        Position position = Position.of(randomX, randomY, randomDirection());
        System.out.println("DIRECTION DE RANDOM : "+ position.getDirection());
        if(isPositionOnMap(position)){
            System.out.println("CETTE POSITION EST DEJA PRISE");
            position = randomPositionRover(size);
        }
        return position;
    }

    public int randomInt(int size){
        int x = (int)(Math.random() * size);
        if(x > size/2){
            x = x/2;
        }else{
            x = -x;
        }
        return x;
    }

    public Direction randomDirection(){
        Direction dir = Direction.NORTH;
        Random r = new Random();
        int rand = r.nextInt(4);
        System.out.println("RANDOM NUMBER : " + rand);
        switch (rand){
            case 0:
                dir = Direction.NORTH;
                break;
            case 1:
                dir = Direction.SOUTH;
                break;
            case 2:
                dir = Direction.EAST;
                break;
            case 3:
                dir = Direction.WEST;
                break;
        }
        return dir;
    }

    public int getLaserRange() {
        return laserRange;
    }

    public void setLaserRange(int laserRange) {
        this.laserRange = laserRange;
    }

    public Set<Position> getObstaclePositions() {
        return obstaclePositions;
    }

    @Override
    public Set<Position> obstaclePositions() {
        return this.obstaclePositions;
    }

    // Add an obstacle, if the obstacle position is already used : return null
    public boolean addObstaclePosition(Position position) {
        if (!this.isPositionOnMap(position)) {
            this.obstaclePositions.add(position);
            return true;
        }

        return false;
    }

    // Remove an obstacle of the map, and return true if the obstacle is found, false if not
    public boolean removeObstaclePosition(Position position) {
        if (this.isPositionOnMap(position)) {
            this.obstaclePositions.removeIf(pos -> pos.getX() == position.getX() &&
                    pos.getY() == position.getY());
            return true;
        }

        return false;
    }

    boolean isPositionOnMap(Position position) {
        for (Position p : this.obstaclePositions) {
            if (p.getX() == position.getX() && p.getY() == position.getY()) {
                return true;
            }
        }
        return false;
    }

    public int getSizeOfTheMap() {
        return sizeOfTheMap;
    }

    boolean isLimitOfMap(Position position){
        return position.getX() == (getSizeOfTheMap() / 2) || position.getY() == (getSizeOfTheMap() / 2)
            || position.getX() == -((getSizeOfTheMap() / 2) - 1) || position.getY() == -((getSizeOfTheMap() / 2) - 1);
    }

}
