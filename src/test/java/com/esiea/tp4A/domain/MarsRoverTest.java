package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverTest {

    private final MarsRoverImpl roverNorth = (MarsRoverImpl) new MarsRoverImpl().initialize(new Position.FixedPosition(0, 0, Direction.NORTH));
    private final MarsRoverImpl roverSouth = (MarsRoverImpl) new MarsRoverImpl().initialize(new Position.FixedPosition(1, 1, Direction.SOUTH));
    private final MarsRoverImpl roverEast = (MarsRoverImpl) new MarsRoverImpl().initialize(new Position.FixedPosition(1, 1, Direction.EAST));
    private final MarsRoverImpl roverWest = (MarsRoverImpl) new MarsRoverImpl().initialize(new Position.FixedPosition(1, 1, Direction.WEST));
    private final MarsRoverImpl roverLaser = (MarsRoverImpl) new MarsRoverImpl().initialize(new Position.FixedPosition(1, 0, Direction.NORTH));


    @Test
    void move_forward() {
        Position newPosition = roverNorth.move("f");
        Position newPosition2 = roverSouth.move("f");
        Position newPosition3 = roverEast.move("f");
        Position newPosition4 = roverWest.move("f");

        assertThat(newPosition)
                .as("Test position")
                .extracting(Position::getDirection)
                .isEqualTo(Direction.NORTH);

        assertThat(newPosition)
                .as("Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 1, Direction.NORTH));

        assertThat(newPosition2)
                .as("Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 0, Direction.SOUTH));

        assertThat(newPosition2)
                .as("Test position")
                .extracting(Position::getDirection)
                .isEqualTo(Direction.SOUTH);

        assertThat(newPosition3)
                .as("Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(2, 1, Direction.EAST));

        assertThat(newPosition3)
                .as("Test position")
                .extracting(Position::getDirection)
                .isEqualTo(Direction.EAST);

        assertThat(newPosition4)
                .as("Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 1, Direction.WEST));

        assertThat(newPosition4)
                .as("Test position")
                .extracting(Position::getDirection)
                .isEqualTo(Direction.WEST);
    }

    @Test
    void move_backward() {
        Position newPosition = roverNorth.move("b");
        Position newPosition2 = roverSouth.move("b");
        Position newPosition3 = roverEast.move("b");
        Position newPosition4 = roverWest.move("b");

        assertThat(newPosition)
                .as("Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, -1, Direction.NORTH));
        assertThat(newPosition2)
                .as("Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 2, Direction.SOUTH));
        assertThat(newPosition3)
                .as("Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 1, Direction.EAST));
        assertThat(newPosition4)
                .as("Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(2, 1, Direction.WEST));

    }

    @Test
    void move_left() {
        Position newPosition = roverNorth.move("l");
        Position newPosition2 = roverSouth.move("l");
        Position newPosition3 = roverEast.move("l");
        Position newPosition4 = roverWest.move("l");

        assertThat(newPosition)
                .as("Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.WEST));

        assertThat(newPosition2)
                .as("Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 1, Direction.EAST));

        assertThat(newPosition3)
                .as("Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 1, Direction.NORTH));

        assertThat(newPosition4)
                .as("Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 1, Direction.SOUTH));
    }

    @Test
    void move_right() {
        Position newPosition = roverNorth.move("r");
        Position newPosition2 = roverSouth.move("r");
        Position newPosition3 = roverEast.move("r");
        Position newPosition4 = roverWest.move("r");

        assertThat(newPosition)
                .as("Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.EAST));
        assertThat(newPosition2)
                .as("Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 1, Direction.WEST));
        assertThat(newPosition3)
                .as("Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 1, Direction.SOUTH));
        assertThat(newPosition4)
                .as("Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 1, Direction.NORTH));
    }

    @Test
    void move_with_unknown_command() {
        Position newPosition = roverNorth.move("g");

        assertThat(newPosition)
                .as("Rover position after g command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_with_multiple_command() {
        Character[] commands = {'f', 'f', 'l', 'b'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after f,f,l,b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 2, Direction.WEST));
    }

    @Test
    void move_right_forward() {
        Character[] commands = {'r', 'f'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after r,f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 0, Direction.EAST));
    }

    @Test
    void move_right_backward() {
        Character[] commands = {'r', 'b'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after r,b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(-1, 0, Direction.EAST));
    }

    @Test
    void move_left_backward() {
        Character[] commands = {'l', 'b'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after l, b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 0, Direction.WEST));
    }

    @Test
    void add_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(1, 0, Direction.NORTH);
        boolean status = planetMap.addObstaclePosition(obstaclePosition);

        assertThat(status)
                .as("addObstaclePosition status")
                .isEqualTo(true);

        assertThat(planetMap.obstaclePositions())
                .as("Add obstacle in map")
                .contains(obstaclePosition);
    }

    @Test
    void add_same_obstacle_twice() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(1, 0, Direction.NORTH);
        boolean status1 = planetMap.addObstaclePosition(obstaclePosition);
        boolean status2 = planetMap.addObstaclePosition(obstaclePosition);

        assertThat(status1)
                .as("First addObstaclePosition status")
                .isEqualTo(true);

        assertThat(status2)
                .as("Second addObstaclePosition status")
                .isEqualTo(false);

        assertThat(planetMap.obstaclePositions())
                .as("Add obstacle in map")
                .containsOnlyOnce(obstaclePosition);
    }

    @Test
    void remove_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);

        boolean status = planetMap.removeObstaclePosition(obstaclePosition);

        assertThat(status)
                .as("removeObstaclePosition status")
                .isEqualTo(true);

        assertThat(planetMap.obstaclePositions())
                .as("Remove obstacle in map")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void remove_same_obstacle_twice() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);

        boolean status1 = planetMap.removeObstaclePosition(obstaclePosition);
        boolean status2 = planetMap.removeObstaclePosition(obstaclePosition);

        assertThat(status1)
                .as("First removeObstaclePosition status")
                .isEqualTo(true);

        assertThat(status2)
                .as("Second removeObstaclePosition status")
                .isEqualTo(false);

        assertThat(planetMap.obstaclePositions())
                .as("Add obstacle in map")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void move_forward_with_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(new Position.FixedPosition(0, 1, Direction.NORTH));

        roverNorth.updateMap(planetMap);
        Position newPosition = roverNorth.move("f");

        assertThat(newPosition)
                .as("Rover position after f command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_backward_with_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(new Position.FixedPosition(0, -1, Direction.NORTH));

        roverNorth.updateMap(planetMap);
        Position newPosition = roverNorth.move("b");

        assertThat(newPosition)
                .as("Rover position after b command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_left_with_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(new Position.FixedPosition(-1, 0, Direction.NORTH));

        roverNorth.updateMap(planetMap);
        Character[] commands = {'l', 'f'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after l and f command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.WEST));
    }

    @Test
    void move_right_with_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(new Position.FixedPosition(1, 0, Direction.NORTH));

        roverNorth.updateMap(planetMap);
        Character[] commands = {'r', 'f'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after r command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.EAST));
    }

    @Test
    void tirLaser_without_Obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        roverLaser.updateMap(planetMap);

        roverLaser.configureLaserRange(10);
        roverLaser.shootLaser();
        Character[] commands = {'f', 'f', 'f', 'f'};
        Position newPosition = roverLaser.move(commands);

        assertThat(newPosition)
                .as("Rover position after f command without obstacle and laser shot")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 4, Direction.NORTH));
    }

    @Test
    void tirLaserN() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(1, 3, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);


        roverLaser.updateMap(planetMap);

        roverLaser.configureLaserRange(10);
        roverLaser.shootLaser();

        Character[] commands = {'f', 'f', 'f', 'f'};
        Position newPosition = roverLaser.move(commands);

        assertThat(newPosition)
                .as("Rover position after f command with obstacle and laser shot")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 4, Direction.NORTH));

        assertThat(planetMap.obstaclePositions())
                .as("Remove obstacle in map with laser")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void tirLaserS() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(1, 0, Direction.SOUTH);
        planetMap.addObstaclePosition(obstaclePosition);


        roverSouth.updateMap(planetMap);

        roverSouth.configureLaserRange(10);
        roverSouth.shootLaser();

        Position newPosition = roverSouth.move("f");

        assertThat(newPosition)
                .as("Rover position after f command with obstacle and laser shot")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 0, Direction.SOUTH));

        assertThat(planetMap.obstaclePositions())
                .as("Remove obstacle in map with laser")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void tirLaserE() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(2, 1, Direction.EAST);
        planetMap.addObstaclePosition(obstaclePosition);


        roverEast.updateMap(planetMap);

        roverEast.configureLaserRange(10);
        roverEast.shootLaser();

        Position newPosition = roverEast.move("f");

        assertThat(newPosition)
                .as("Rover position after f command with obstacle and laser shot")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(2, 1, Direction.EAST));

        assertThat(planetMap.obstaclePositions())
                .as("Remove obstacle in map with laser")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void tirLaserW() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();

        Position obstaclePosition = new Position.FixedPosition(0, 1, Direction.WEST);
        planetMap.addObstaclePosition(obstaclePosition);


        roverWest.updateMap(planetMap);

        roverWest.configureLaserRange(10);
        roverWest.shootLaser();

        Position newPosition = roverWest.move("f");

        assertThat(newPosition)
                .as("Rover position after f command with obstacle and laser shot")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 1, Direction.WEST));

        assertThat(planetMap.obstaclePositions())
                .as("Remove obstacle in map with laser")
                .doesNotContain(obstaclePosition);
    }


}
