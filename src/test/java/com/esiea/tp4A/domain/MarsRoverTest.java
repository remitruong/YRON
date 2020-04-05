package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverTest {
    private final MarsRoverImpl roverNorth = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));
    private final MarsRoverImpl roverSouth = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, 0, Direction.SOUTH));
    private final MarsRoverImpl roverEast = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, 0, Direction.EAST));
    private final MarsRoverImpl roverWest = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, 0, Direction.WEST));

    private final MarsRoverImpl roverNorthLimit = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, 50, Direction.NORTH));
    private final MarsRoverImpl roverSouthLimit = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, -49, Direction.SOUTH));
    private final MarsRoverImpl roverEastLimit = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(50, 0, Direction.EAST));
    private final MarsRoverImpl roverWestLimit = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(-49, 0, Direction.WEST));

    private final MarsRoverImpl roverNorthLimit2 = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, -49, Direction.NORTH));
    private final MarsRoverImpl roverSouthLimit2 = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, 50, Direction.SOUTH));
    private final MarsRoverImpl roverEastLimit2 = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(-49, 0, Direction.EAST));
    private final MarsRoverImpl roverWestLimit2 = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(50, 0, Direction.WEST));


    //Forward limit tests
    @Test
    void move_forward_north_limit() {
        Position newPosition = roverNorthLimit.move("f");

        assertThat(newPosition)
                .as("North-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, -49, Direction.NORTH));
    }

    @Test
    void move_forward_south_limit() {
        Position newPosition = roverSouthLimit.move("f");

        assertThat(newPosition)
                .as("South-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 50, Direction.SOUTH));
    }

    @Test
    void move_forward_east_limit() {
        Position newPosition = roverEastLimit.move("f");

        assertThat(newPosition)
                .as("East-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(-49, 0, Direction.EAST));
    }

    @Test
    void move_forward_west_limit() {
        Position newPosition = roverWestLimit.move("f");

        assertThat(newPosition)
                .as("West-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(50, 0, Direction.WEST));
    }

    //Backward limit tests

    @Test
    void move_backward_north_limit() {
        Position newPosition = roverNorthLimit2.move("b");

        assertThat(newPosition)
                .as("North-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 50, Direction.NORTH));
    }

    @Test
    void move_backward_south_limit() {
        Position newPosition = roverSouthLimit2.move("b");

        assertThat(newPosition)
                .as("South-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, -49, Direction.SOUTH));
    }

    @Test
    void move_backward_east_limit() {
        Position newPosition = roverEastLimit2.move("b");

        assertThat(newPosition)
                .as("East-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(50, 0, Direction.EAST));
    }

    @Test
    void move_backward_west_limit() {
        Position newPosition = roverWestLimit2.move("b");

        assertThat(newPosition)
                .as("West-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(-49, 0, Direction.WEST));
    }

    /*
     * Move forward
     */
    @Test
    void move_forward_north() {
        Position newPosition = roverNorth.move("f");

        assertThat(newPosition)
                .as("North-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 1, Direction.NORTH));
    }

    @Test
    void move_forward_south() {
        Position newPosition = roverSouth.move("f");

        assertThat(newPosition)
                .as("South-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, -1, Direction.SOUTH));
    }

    @Test
    void move_forward_east() {
        Position newPosition = roverEast.move("f");

        assertThat(newPosition)
                .as("East-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 0, Direction.EAST));
    }

    @Test
    void move_forward_west() {
        Position newPosition = roverWest.move("f");

        assertThat(newPosition)
                .as("West-oriented Rover position after f command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(-1, 0, Direction.WEST));
    }

    /*
     * Move Backward
     */
    @Test
    void move_backward_north() {
        Position newPosition = roverNorth.move("b");

        assertThat(newPosition)
                .as("North-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, -1, Direction.NORTH));
    }

    @Test
    void move_backward_south() {
        Position newPosition = roverSouth.move("b");

        assertThat(newPosition)
                .as("South-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 1, Direction.SOUTH));
    }

    @Test
    void move_backward_east() {
        Position newPosition = roverEast.move("b");

        assertThat(newPosition)
                .as("East-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(-1, 0, Direction.EAST));
    }

    @Test
    void move_backward_west() {
        Position newPosition = roverWest.move("b");

        assertThat(newPosition)
                .as("West-oriented Rover position after b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 0, Direction.WEST));
    }

    /*
     * Rotate to the left
     */
    @Test
    void rotate_left_north() {
        Position newPosition = roverNorth.move("l");
        assertThat(newPosition)
                .as("North-oriented Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.WEST));
    }

    @Test
    void rotate_left_south() {
        Position newPosition = roverSouth.move("l");

        assertThat(newPosition)
                .as("South-oriented Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.EAST));
    }

    @Test
    void rotate_left_east() {
        Position newPosition = roverEast.move("l");

        assertThat(newPosition)
                .as("East-oriented Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    @Test
    void rotate_left_west() {
        Position newPosition = roverWest.move("l");

        assertThat(newPosition)
                .as("West-oriented Rover position after l command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.SOUTH));
    }

    /*
     * Rotate to the right
     */
    @Test
    void rotate_right_north() {
        Position newPosition = roverNorth.move("r");

        assertThat(newPosition)
                .as("North-oriented Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.EAST));
    }

    @Test
    void rotate_right_south() {
        Position newPosition = roverSouth.move("r");

        assertThat(newPosition)
                .as("South-oriented Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.WEST));
    }

    @Test
    void rotate_right_east() {
        Position newPosition = roverEast.move("r");

        assertThat(newPosition)
                .as("East-oriented Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.SOUTH));
    }

    @Test
    void rotate_right_west() {
        Position newPosition = roverWest.move("r");

        assertThat(newPosition)
                .as("West-oriented Rover position after r command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    /*
     * Uknown move
     */
    @Test
    void move_with_unknown_command() {
        Position newPosition = roverNorth.move("g");

        assertThat(newPosition)
                .as("Rover position after an unknown command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    /*
     * Multiple commands to move
     */
    @Test
    void move_with_multiple_command() {
        Character[] commands = {'f', 'r', 'f', 'f', 'l', 'b', 'l'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after f,r,f,f,l,b,l commands")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(2, 0, Direction.WEST));
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
        Character[] commands = {'l', 'b', 'r', 'f'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after l, b command")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(1, 1, Direction.NORTH));
    }

    /*
     * Obstacles management
     */
    @Test
    void move_forward_with_obstacle_north() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(Position.of(0, 1, Direction.NORTH));

        roverNorth.updateMap(planetMap);
        Position newPosition = roverNorth.move("f");

        assertThat(newPosition)
                .as("Rover position after f command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_forward_with_obstacle_south() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(Position.of(0, -1, Direction.NORTH));
      
        roverSouth.updateMap(planetMap);
        Position newPosition = roverSouth.move("f");

        assertThat(newPosition)
                .as("Rover position after f command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.SOUTH));
    }

    @Test
    void move_forward_with_obstacle_east() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(Position.of(1, 0, Direction.NORTH));

        roverEast.updateMap(planetMap);
        Position newPosition = roverEast.move("f");

        assertThat(newPosition)
                .as("Rover East position after f command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.EAST));
    }
    
    @Test
    void move_forward_with_obstacle_west() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(Position.of(-1, 0, Direction.NORTH));

        roverWest.updateMap(planetMap);
        Position newPosition = roverWest.move("f");

        assertThat(newPosition)
                .as("Rover West position after f command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.WEST));
    }

    @Test
    void move_backward_with_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        planetMap.addObstaclePosition(Position.of(0, -1, Direction.NORTH));

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
        planetMap.addObstaclePosition(Position.of(-1, 0, Direction.NORTH));

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
        planetMap.addObstaclePosition(Position.of(1, 0, Direction.NORTH));

        roverNorth.updateMap(planetMap);
        Character[] commands = {'r', 'f'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after r command with obstacle")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 0, Direction.EAST));
    }
    
    /*
     * Laser on obstacles
     */
    @Test
    void shoot_north_classic() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(0, 3, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);
        roverNorth.updateMap(planetMap);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);

        roverNorth.configureLaserRange(10);
        roverNorth.shootLaser();

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well destroyed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void shoot_south_classic() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(0, -3, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);
        roverSouth.updateMap(planetMap);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);

        roverSouth.configureLaserRange(10);
        roverSouth.shootLaser();

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well destroyed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void shoot_east_classic() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(3, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);
        roverEast.updateMap(planetMap);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);

        roverEast.configureLaserRange(10);
        roverEast.shootLaser();

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well destroyed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void shoot_west_classic() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(-3, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);
        roverWest.updateMap(planetMap);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);

        roverWest.configureLaserRange(10);
        roverWest.shootLaser();

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well destroyed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void shoot_obstacle_at_beginning_range() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(0, 1, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);
        roverNorth.updateMap(planetMap);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);

        roverNorth.configureLaserRange(10);
        roverNorth.shootLaser();

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well destroyed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void shoot_obstacle_at_end_range() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(0, 10, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition);
        roverNorth.updateMap(planetMap);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);

        roverNorth.configureLaserRange(10);
        roverNorth.shootLaser();

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well destroyed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void shoot_obstacle_while_double_obstacles() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition1 = Position.of(0, 6, Direction.NORTH);
        Position obstaclePosition2 = Position.of(0, 7, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition1);
        planetMap.addObstaclePosition(obstaclePosition2);
        roverNorth.updateMap(planetMap);

        assertThat(planetMap.obstaclePositions())
                .as("Check if first obstacle is on map")
                .contains(obstaclePosition1);

        assertThat(planetMap.obstaclePositions())
                .as("Check if second obstacle is on map")
                .contains(obstaclePosition2);

        roverNorth.configureLaserRange(10);
        roverNorth.shootLaser();

        assertThat(planetMap.obstaclePositions())
                .as("Check if the first obstacle is well destroyed")
                .doesNotContain(obstaclePosition1);

        assertThat(planetMap.obstaclePositions())
                .as("Check if second obstacle is still on map")
                .contains(obstaclePosition2);
    }
    
    @Test
    void tirLaser_without_Obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        roverNorth.updateMap(planetMap);

        roverNorth.configureLaserRange(10);
        roverNorth.shootLaser();
        Character[] commands = {'f', 'f', 'f', 'f'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after f command without obstacle and laser shot")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 4, Direction.NORTH));
    }
}
