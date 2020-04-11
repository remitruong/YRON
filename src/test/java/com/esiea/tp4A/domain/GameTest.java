package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private final Game game1 = new Game("test");

    @Test
    void game_getId(){
        assertThat(game1.getId())
            .as("Test the id of the map")
            .isEqualTo("test");
    }

    @Test
    void game_getMapSize(){
        assertThat(game1.getMapSize())
            .as("Test the size of the map")
            .isIn(100, 300, 600);
    }

    @Test
    void game_getMap() {
        assertThat(game1.getPlanetMap())
                .as("Check presence of map")
                .isNotNull();
    }

    @Test
    void game_getLaserRange() {
        assertThat(game1.getLaserRange())
            .as("Test the size of the map")
            .isIn(5, 30, game1.getMapSize()+1);
    }

    @Test
    void generate_rover_test(){
        MarsRoverImpl rover = game1.generateRover("Rover1");

        assertThat(rover.isAlive())
            .as("Check if rover is well created")
            .isTrue();

        assertThat(rover.getName())
                .as("Check if rover is well named")
                .isEqualTo("Rover1");

        assertThat(game1.getPlanetMap().getRoverList().size())
            .as("Check if rover is added to roverList")
            .isEqualTo(1);
    }
}
