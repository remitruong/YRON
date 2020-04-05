package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {
    private Position position;
    private PlanetMapImpl map;
    private int laserRange;

    public MarsRover initialize(Position position) {
        this.position = position;
        this.map = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        return this;
    }

    @Override
    public MarsRover updateMap(PlanetMap map) {
        this.map = (PlanetMapImpl) map;
        return this;
    }

    @Override
    public Position move(String command) {
        Position pos_next = this.position;

        pos_next = move_switch_main(command, pos_next);

        // check if map not null and if next position is not the position of an obstacle, if it's not : move to next
        if (this.map != null && !this.map.isPositionOnMap(pos_next))
            this.position = pos_next;

        return this.position;
    }

    public Position move(Character[] commands) {
        for (Character command : commands) {
            this.move(Character.toString(command));
        }
        return this.position;
    }

    private Position move_switch_main(String command, Position pos_next) {
        switch (command) {
            case "f":
                return move_switch_front(pos_next);
            case "b":
                return move_switch_back(pos_next);
            case "l":
                return move_switch_left(pos_next);
            case "r":
                return move_switch_right(pos_next);
            default:
                return pos_next;
        }
    }

    private Position move_switch_front(Position pos_next) {
        switch (this.position.getDirection()) {
            case NORTH:
                return limitPositionForward(Position.of(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection()));
            case SOUTH:
                return limitPositionForward(Position.of(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection()));
            case EAST:
                return limitPositionForward(Position.of(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection()));
            case WEST:
                return limitPositionForward(Position.of(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection()));
            default:
                return pos_next;
        }
    }

    private Position move_switch_back(Position pos_next) {
        switch (this.position.getDirection()) {
            case NORTH:
                return limitPositionBackward(Position.of(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection()));
            case SOUTH:
                return limitPositionBackward(Position.of(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection()));
            case EAST:
                return limitPositionBackward(Position.of(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection()));
            case WEST:
                return limitPositionBackward(Position.of(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection()));
            default:
                return pos_next;
        }
    }

    private Position move_switch_left(Position pos_next) {
        switch (this.position.getDirection()) {
            case NORTH:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.WEST);
            case SOUTH:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.EAST);
            case WEST:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.SOUTH);
            case EAST:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.NORTH);
            default:
                return pos_next;
        }
    }

    private Position move_switch_right(Position pos_next) {
        switch (this.position.getDirection()) {
            case NORTH:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.EAST);
            case SOUTH:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.WEST);
            case WEST:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.NORTH);
            case EAST:
                return Position.of(pos_next.getX(), pos_next.getY(), Direction.SOUTH);
            default:
                return pos_next;
        }
    }

    public MarsRoverImpl configureLaserRange(int range) {
        this.laserRange = range;
        return this;
    }

    public Position shootLaser() {
        Position result = null;
        Position pos_next = this.position;
        int i = 0;
        boolean destroyed = false;

        while (i <= this.laserRange && !destroyed) { // As long as the laser has range and we haven't destroyed an obstacle
            pos_next = move_switch_front(pos_next); // Next cell on the laser range

            if (this.map.isPositionOnMap(pos_next)) // Check if next cell on the laser range is a laser and if yes : destroy it
                destroyed = map.removeObstaclePosition(pos_next);
            i++;
        }

        return result;
    }

    public Position limitPositionForward(Position nextPosition){
        int size = map.getSizeOfTheMap();
        switch (nextPosition.getDirection()) {
            case NORTH:
                if(nextPosition.getY() > size/2){
                    //Le cas y+1
                    nextPosition = Position.of(nextPosition.getX(), -size/2 +1, Direction.NORTH);
                }
            case SOUTH:
                if(nextPosition.getY() == -size/2){
                    //Le cas y-1
                    nextPosition = Position.of(nextPosition.getX(), size/2, Direction.SOUTH);
                }
            case EAST:
                if(nextPosition.getX() > size/2){
                    //Le cas x+1
                    nextPosition = Position.of(-size/2 +1, nextPosition.getY(), Direction.EAST);
                }
            case WEST:
                if(nextPosition.getX() == -size/2){
                    //Le cas x-1
                    nextPosition = Position.of(size/2, nextPosition.getY(), Direction.WEST);
                }
            default:
                return nextPosition;
        }
    }

    public Position limitPositionBackward(Position nextPosition){
        int size = map.getSizeOfTheMap();
        switch (nextPosition.getDirection()) {
            case NORTH:
                if(nextPosition.getY() == -size/2){
                    //Le cas y-1
                    nextPosition = Position.of(nextPosition.getX(), size/2, Direction.NORTH);
                }
            case SOUTH:
                if(nextPosition.getY() > size/2){
                    //Le cas y+1
                    nextPosition = Position.of(nextPosition.getX(), -size/2 +1, Direction.SOUTH);
                }
            case EAST:
                if(nextPosition.getX() == -size/2){
                    //Le cas x-1
                    nextPosition = Position.of(size/2, nextPosition.getY(), Direction.EAST);
                }
            case WEST:
                if(nextPosition.getX() > size/2){
                    //Le cas x+1
                    nextPosition = Position.of(-size/2 +1, nextPosition.getY(), Direction.WEST);
                }
            default:
                return nextPosition;
        }
    }
}
