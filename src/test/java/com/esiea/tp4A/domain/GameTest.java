package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private final Game game1 = new Game(5, 100, 1);
    private final Game game2 = new Game(10, 300, 2);
    private final Game game3 = new Game(100, 600, 3);


    @Test
    void game_id_test(){
        assertThat(game1.getId())
            .as("Test the id of the map")
            .isEqualTo(1);
        assertThat(game2.getId())
            .as("Test the id of the map")
            .isEqualTo(2);
        assertThat(game3.getId())
            .as("Test the id of the map")
            .isEqualTo(3);
    }

    @Test
    void game_size_test(){
        assertThat(game1.getMapSize())
            .as("Test the size of the map")
            .isEqualTo(100);
        assertThat(game2.getMapSize())
            .as("Test the size of the map")
            .isEqualTo(300);
        assertThat(game3.getMapSize())
            .as("Test the size of the map")
            .isEqualTo(600);
    }

    @Test
    void generate_rover_test(){
        game1.generateRover("Rover1");
        assertThat(game1.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(1);
        game1.generateRover("Rover1_2");
        assertThat(game1.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(2);
        game1.generateRover("Rover1_3");
        assertThat(game1.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(3);

        game2.generateRover("Rover2");
        assertThat(game2.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(1);
        game2.generateRover("Rover2_2");
        assertThat(game2.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(2);
        game2.generateRover("Rover2_3");
        assertThat(game2.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(3);

        game3.generateRover("Rover3");
        assertThat(game3.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(1);
        game3.generateRover("Rover3_2");
        assertThat(game3.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(2);
        game3.generateRover("Rover3_3");
        assertThat(game3.getRoverList().size())
            .as("Test generate rover")
            .isEqualTo(3);
    }
}
