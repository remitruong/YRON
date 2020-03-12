package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {
    private Position position;
    private PlanetMapImpl map;

    public MarsRover initialize(Position position) {
        this.position = position;
        this.map = (PlanetMapImpl) new PlanetMapImpl().initialize();
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
                return Position.of(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection());
            case SOUTH:
                return Position.of(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection());
            case EAST:
                return Position.of(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection());
            case WEST:
                return Position.of(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection());
            default:
                return pos_next;
        }
    }

    private Position move_switch_back(Position pos_next) {
        switch (this.position.getDirection()) {
            case NORTH:
                return Position.of(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection());
            case SOUTH:
                return Position.of(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection());
            case EAST:
                return Position.of(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection());
            case WEST:
                return Position.of(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection());
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

    public MarsRoverImpl configureLaserRange(int portee) {
        boolean detruit = false;
        Position pos_next = this.position;

        for (int i = 0; i < portee; i++) {
            if (!detruit) {
                switch (this.position.getDirection()) {
                    case NORTH:
                        pos_next = new Position.FixedPosition(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection());
                        if (this.map.isPositionOnMap(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                    case SOUTH:
                        pos_next = new Position.FixedPosition(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection());
                        if (this.map.isPositionOnMap(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                    case EAST:
                        pos_next = new Position.FixedPosition(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection());
                        if (this.map.isPositionOnMap(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                    case WEST:
                        pos_next = new Position.FixedPosition(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection());
                        if (this.map.isPositionOnMap(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                }
            }
        }
        return this;
    }

    public Position move(Character[] commands) {
        for (Character command : commands) {
            this.move(Character.toString(command));
        }
        return this.position;
    }
}
