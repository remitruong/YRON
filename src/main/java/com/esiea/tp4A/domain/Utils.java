package com.esiea.tp4A.domain;

import java.io.Serializable;
import java.util.Random;

public class Utils implements Serializable {
    public int randomInt(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public int randomIntForPosition(int size){
        int x = (int)(Math.random() * size);
        if(x > size/2) {
            x = x/2;
        } else {
            x = -x;
        }
        return x;
    }

    public Direction randomDirection(){
        Direction dir = null;
        Random r = new Random();
        int rand = randomInt(4);
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
    
    public Position randomPosition(int size, PlanetMapImpl map){
        int randomX = randomIntForPosition(size);
        int randomY = randomIntForPosition(size);
        Position position = Position.of(randomX, randomY, randomDirection());
        if(map.isObstaclePositionOnMap(position)){
            position = randomPosition(map.getSizeOfTheMap(), map);
        }
        return position;
    }

    public int generateRandMapSize() {
        int result = 0;
        int rand = randomInt(3);
        switch(rand) {
            case 0:
                result = 100;
                break;
            case 1 :
                result = 300;
                break;
            case 2:
                result = 600;
                break;
        }
        return result;
    }

    public int generateRandLaserRange(int mapSize) {
        int result = 0;
        int rand = randomInt(3);
        switch(rand) {
            case 0:
                result = 5;
                break;
            case 1 :
                result = 30;
                break;
            case 2:
                result = mapSize+1;
                break;
        }
        return result;
    }
}
