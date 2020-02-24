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
                        return Position.of(this.x, this.y + 1, this.direction);
                    case SOUTH:
                        return Position.of(this.x, this.y - 1 , this.direction);
                    case WEST:
                        return Position.of(this.x + 1, this.y, this.direction);
                    case EAST:
                        return Position.of(this.x - 1, this.y, this.direction);
                }
            case "b":
                switch (this.direction) {
                    case NORTH:
                        return Position.of(this.x, this.y - 1, this.direction);
                    case SOUTH:
                        return Position.of(this.x, this.y + 1, this.direction);
                    case WEST:
                        return Position.of(this.x - 1, this.y, this.direction);
                    case EAST:
                        return Position.of(this.x + 1, this.y, this.direction);
                }
            case "l":
                switch (this.direction) {
                    case NORTH:
                        return Position.of(this.x, this.y, Direction.WEST);
                    case SOUTH:
                        return Position.of(this.x, this.y, Direction.EAST);
                    case WEST:
                        return Position.of(this.x, this.y, Direction.SOUTH);
                    case EAST:
                        return Position.of(this.x, this.y, Direction.NORTH);
                }
            case "r":
                switch (this.direction) {
                    case NORTH:
                        return Position.of(this.x, this.y, Direction.EAST);
                    case SOUTH:
                        return Position.of(this.x, this.y, Direction.WEST);
                    case WEST:
                        return Position.of(this.x, this.y, Direction.NORTH);
                    case EAST:
                        return Position.of(this.x, this.y, Direction.SOUTH);
                }
            default:
                return Position.of(this.x, this.y, this.direction);
        }
    }

    public Position move(Position initPosition, char[] commands){
        for (int i = 0; i < commands.length; i++) {
            initPosition = this.move(Character.toString(i));
        }
        return Position.of(this.x, this.y, this.direction);
    }
}
