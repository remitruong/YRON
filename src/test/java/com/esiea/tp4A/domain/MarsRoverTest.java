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
}
