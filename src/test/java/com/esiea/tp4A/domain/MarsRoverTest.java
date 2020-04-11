package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverTest {
    private final PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
    private final MarsRoverImpl roverNorth = new MarsRoverImpl(Position.of(0, 0, Direction.NORTH),"Jean", planetMap, 10);
    private final MarsRoverImpl roverSouth = new MarsRoverImpl(Position.of(0, 0, Direction.SOUTH),"Veronika", planetMap, 10);
    private final MarsRoverImpl roverEast = new MarsRoverImpl(Position.of(0, 0, Direction.EAST),"Alicia", planetMap, 10);
    private final MarsRoverImpl roverWest = new MarsRoverImpl(Position.of(0, 0, Direction.WEST),"Nicolas", planetMap, 10);

    private final MarsRoverImpl roverNorthLimit = new MarsRoverImpl(Position.of(0, 50, Direction.NORTH), "Irwin", planetMap, 10);
    private final MarsRoverImpl roverSouthLimit = new MarsRoverImpl(Position.of(0, -49, Direction.SOUTH), "Claire", planetMap, 10);
    private final MarsRoverImpl roverEastLimit = new MarsRoverImpl(Position.of(50, 0, Direction.EAST), "Ron", planetMap, 10);
    private final MarsRoverImpl roverWestLimit = new MarsRoverImpl(Position.of(-49, 0, Direction.WEST), "Bruce", planetMap, 10);

    private final MarsRoverImpl roverNorthLimit2 = new MarsRoverImpl(Position.of(0, -49, Direction.NORTH), "David", planetMap, 10);
    private final MarsRoverImpl roverSouthLimit2 = new MarsRoverImpl(Position.of(0, 50, Direction.SOUTH), "Chloe", planetMap, 10);
    private final MarsRoverImpl roverEastLimit2 = new MarsRoverImpl(Position.of(-49, 0, Direction.EAST), "Eve", planetMap, 10);
    private final MarsRoverImpl roverWestLimit2 = new MarsRoverImpl(Position.of(50, 0, Direction.WEST), "Maud", planetMap, 10);

    /*
     * Rover Info
     */
    @Test
    void rover_name_test(){
        assertThat(roverNorth.getName())
            .as("Test the name of the Rover")
            .isEqualTo("Jean");
        assertThat(roverSouth.getName())
            .as("Test the name of the Rover")
            .isEqualTo("Veronika");
        assertThat(roverEast.getName())
            .as("Test the name of the Rover")
            .isEqualTo("Alicia");
        assertThat(roverWest.getName())
            .as("Test the name of the Rover")
            .isEqualTo("Nicolas");
    }

    @Test
    void rover_alive_at_creation() {
        MarsRoverImpl rover = new MarsRoverImpl(Position.of(0, 0, Direction.NORTH),"Justin NOUVOROVEUR", planetMap, 10);

        assertThat(rover.isAlive())
            .as("Test if a new rover is alive at creation")
            .isTrue();
    }

    @Test
    void rover_kill() {
        MarsRoverImpl rover = new MarsRoverImpl(Position.of(0, 0, Direction.NORTH),"Justin NOUVOROVEUR", planetMap, 10);
        rover.kill();

        assertThat(rover.isAlive())
            .as("Test if rover is killed")
            .isFalse();
    }

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
        roverNorth.move("s");

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
        roverSouth.move("s");

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
        roverEast.move("s");

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
        roverWest.move("s");

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
        roverNorth.move("s");

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
        roverNorth.move("s");

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
        roverNorth.move("s");

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
        roverNorth.move("s");
        Character[] commands = {'f', 'f', 'f', 'f'};
        Position newPosition = roverNorth.move(commands);

        assertThat(newPosition)
                .as("Rover position after f command without obstacle and laser shot")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .isEqualTo(List.of(0, 4, Direction.NORTH));
    }

    @Test
    void tirLaser_withRover() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        roverNorth.updateMap(planetMap);
        roverNorth.configureLaserRange(10);
        Position roverPosition = Position.of(0, 1, Direction.NORTH);
        planetMap.generateRover("Matué", roverPosition, 10);

        assertThat(planetMap.isOtherRoverAtPosition(roverPosition))
            .as("Check if rover is on map")
            .isNotNull();

        roverNorth.move("s");

        assertThat(planetMap.getRoverByName("Matué").isAlive())
            .as("Check if rover is killed")
            .isFalse();
    }
}
