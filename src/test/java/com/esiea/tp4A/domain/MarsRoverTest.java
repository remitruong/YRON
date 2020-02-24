package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverTest {

    private final MarsRover rover = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));

    @Test
    void move_forward() {
        Position newPosition = rover.move("f");

        assertThat(newPosition)
            .as("Rover position after f command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, 1, Direction.NORTH));
    }

    @Test
    void move_backward() {
        Position newPosition = rover.move("b");

        assertThat(newPosition)
            .as("Rover position after f command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, -1, Direction.NORTH));
    }

    @Test
    void move_left(){
        Position newPosition = rover.move("l");

        assertThat(newPosition)
            .as("Rover position after l command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0,0,Direction.WEST));
    }

    @Test
    void move_right(){
        Position newPosition = rover.move("r");

        assertThat(newPosition)
            .as("Rover position after r command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, 0, Direction.EAST));
    }

    @Test
    void move_with_multiple_command(){
        Position newPosition = rover.move("f");
        newPosition = rover.move("f");
        newPosition = rover.move("l");
        newPosition = rover.move("b");

        assertThat(newPosition)
            .as("Rover position after f,f,l,b command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(1, 2, Direction.WEST));
    }

    @Test
    void move_with_unknown_command(){
        Position newPosition = rover.move("g");

        assertThat(newPosition)
            .as("Rover position after g command")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .isEqualTo(List.of(0, 0, Direction.NORTH));
    }
}
