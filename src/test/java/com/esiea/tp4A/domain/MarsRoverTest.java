package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverTest {

    private final MarsRoverImpl rover = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));
    private final MarsRoverImpl rover2 = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(1, 1, Direction.SOUTH));
    private final MarsRoverImpl rover3 = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(1, 1, Direction.EAST));
    private final MarsRoverImpl rover4 = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(1, 1, Direction.WEST));

    @Test
    void move_forward2() {
        Position newPosition2 = rover2.move("f");

        assertThat(newPosition2)
            .as("Rover position after f command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(1, 0, Direction.SOUTH));
    }
    
    @Test
    void move_forward() {
        Position newPosition = rover.move("f");
        Position newPosition3 = rover3.move("f");
        Position newPosition4 = rover4.move("f");

        assertThat(newPosition)
            .as("Rover position after f command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, 1, Direction.NORTH));

        assertThat(newPosition3)
            .as("Rover position after f command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(2, 1, Direction.EAST));

        assertThat(newPosition4)
            .as("Rover position after f command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, 1, Direction.WEST));
    }

    @Test
    void move_backward() {
        Position newPosition = rover.move("b");
        Position newPosition2 = rover2.move("b");
        Position newPosition3 = rover3.move("b");
        Position newPosition4 = rover4.move("b");

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
    void move_left(){
        Position newPosition = rover.move("l");
        Position newPosition2 = rover2.move("l");
        Position newPosition3 = rover3.move("l");
        Position newPosition4 = rover4.move("l");

        assertThat(newPosition)
            .as("Rover position after l command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0,0,Direction.WEST));
        
        assertThat(newPosition2)
        .as("Rover position after l command")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .isEqualTo(List.of(1,1,Direction.EAST));
        
        assertThat(newPosition3)
        .as("Rover position after l command")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .isEqualTo(List.of(1,1,Direction.NORTH));
        
        assertThat(newPosition4)
        .as("Rover position after l command")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .isEqualTo(List.of(1,1,Direction.SOUTH));
    }

    @Test
    void move_right(){
        Position newPosition = rover.move("r");
        Position newPosition2 = rover2.move("r");
        Position newPosition3 = rover3.move("r");
        Position newPosition4 = rover4.move("r");

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
    void move_with_unknown_command(){
        Position newPosition = rover.move("g");

        assertThat(newPosition)
            .as("Rover position after g command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_with_multiple_command(){
        Character[] commands = {'f', 'f', 'l', 'b'};
        Position newPosition = rover.move(commands);

        assertThat(newPosition)
            .as("Rover position after f,f,l,b command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(1, 2, Direction.WEST));
    }

    @Test
    // FIXME : moving on the obstacle
    void add_obstacle() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(new HashSet<Position>());
        planetMap.addObstaclePosition(Position.of(0,1,Direction.NORTH));

        rover.updateMap(planetMap);
        Position newPosition = rover.move("f");

        assertThat(newPosition)
            .as("Rover position after f command with obstacle")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, 0, Direction.NORTH));
    }
}
