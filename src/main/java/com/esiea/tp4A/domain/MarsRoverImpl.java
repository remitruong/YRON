package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {

    private Integer x;
    private Integer y;
    private Direction direction;

    public MarsRover initialize(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
        return this;
    }

    @Override
    public Position move(String command) {
        switch (command) {
            case "f":
                switch (this.direction) {
                    case NORTH:
                        this.y += 1;
                        break;
                    case SOUTH:
                        this.y -= 1;
                        break;
                    case EAST:
                        this.x += 1;
                        break;
                    case WEST:
                        this.x -= 1;
                        break;
                }
                break;
            case "b":
                switch (this.direction) {
                    case NORTH:
                        this.y -= 1;
                        break;
                    case SOUTH:
                        this.y += 1;
                        break;
                    case EAST:
                        this.x -= 1;
                        break;
                    case WEST:
                        this.x += 1;
                        break;
                }
                break;
            case "l":
                switch (this.direction) {
                    case NORTH:
                        this.direction = Direction.WEST;
                        break;
                    case SOUTH:
                        this.direction = Direction.EAST;
                        break;
                    case WEST:
                        this.direction = Direction.SOUTH;
                        break;
                    case EAST:
                        this.direction = Direction.NORTH;
                        break;
                }
                break;
            case "r":
                switch (this.direction) {
                    case NORTH:
                        this.direction = Direction.EAST;
                        break;
                    case SOUTH:
                        this.direction = Direction.WEST;
                        break;
                    case WEST:
                        this.direction = Direction.NORTH;
                        break;
                    case EAST:
                        this.direction = Direction.SOUTH;
                        break;
                }
                break;
        }
        return Position.of(this.x, this.y, this.direction);
    }

    public Position move(Character[] commands){
        for (Character command : commands) {
            this.move(Character.toString(command));
        }
        return Position.of(this.x, this.y, this.direction);
    }
}
