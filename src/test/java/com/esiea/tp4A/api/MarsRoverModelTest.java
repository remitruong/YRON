package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.Game;
import com.esiea.tp4A.domain.MarsRoverImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarsRoverModelTest {

    @Test
    void testCreateGame() throws IOException, ClassNotFoundException {
        Game game = MarsRoverModel.createGame("testGameA");
        assertThat(game.getId()).isEqualTo("testGameA");

        HashMap<String, Game> games = MarsRoverModel.readinFile();
        assertThat(games).containsKey(game.getId());
    }

    @Test
    void getGame() throws IOException, ClassNotFoundException, APIException {
        Game game = MarsRoverModel.createGame("testGameB");

        Game game2 = MarsRoverModel.getGame("testGameB");

        assertThat(game.getId()).isEqualTo(game2.getId());
        assertThat(game.getMapSize()).isEqualTo(game2.getMapSize());
        assertThat(game.getLaserRange()).isEqualTo(game2.getLaserRange());
    }

    @Test
    void getUnknownGame() {
        APIException thrown = assertThrows(
            APIException.class,
            () -> MarsRoverModel.getGame("testGameJKWWLKNLKN"),
            "Expected getGame() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Game not found."));
    }

    @Test
    void createRover() throws IOException, ClassNotFoundException, APIException {
        MarsRoverModel.createGame("testGameC");
        MarsRoverModel.createRover("testGameC", "roverC");

        HashMap<String, Game> games = MarsRoverModel.readinFile();
        assertThat(games.get("testGameC").getPlanetMap().getRoverList().containsKey("roverC"));
    }

    @Test
    void getRover() throws IOException, ClassNotFoundException, APIException {
        Game game = MarsRoverModel.createGame("testGameC");
        MarsRoverImpl rover = MarsRoverModel.createRover("testGameC", "roverC");

        MarsRoverImpl rover2 = MarsRoverModel.getRover(game, "roverC");

        assertThat(rover.getName()).isEqualTo(rover2.getName());
        assertThat(rover.getLaserRange()).isEqualTo(rover2.getLaserRange());
    }

    @Test
    void getUnknownRover() throws IOException, ClassNotFoundException, APIException {
        Game game = MarsRoverModel.createGame("testGameC");

        APIException thrown = assertThrows(
            APIException.class,
            () -> MarsRoverModel.getRover(game, "roverUIUHCDINCSC"),
            "Expected getRover() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Player not found."));
    }

    @Test
    void moveRover() throws IOException, ClassNotFoundException, APIException {
        Game game = MarsRoverModel.createGame("testGameC");
        MarsRoverImpl rover = MarsRoverModel.createRover("testGameD", "roverD");

        MarsRoverImpl rover2 = MarsRoverModel.moveRover("testGameD", "roverD", "f");

        assertThat(rover.getName()).isEqualTo(rover2.getName());
        assertThat(rover.getLaserRange()).isEqualTo(rover2.getLaserRange());
    }
}
