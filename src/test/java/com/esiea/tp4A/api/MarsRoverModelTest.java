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
    void testCreateGame() throws IOException, ClassNotFoundException, APIAlreadyExistsException {
        Game game = MarsRoverModel.createGame("testGameA");
        assertThat(game.getId()).isEqualTo("testGameA");

        HashMap<String, Game> games = MarsRoverModel.readinFile();
        assertThat(games).containsKey(game.getId());
    }

    @Test
    void getGame() throws IOException, ClassNotFoundException, APINotFoundException, APIAlreadyExistsException {
        Game game = MarsRoverModel.createGame("testGameB");

        Game game2 = MarsRoverModel.getGame("testGameB");

        assertThat(game.getId()).isEqualTo(game2.getId());
        assertThat(game.getMapSize()).isEqualTo(game2.getMapSize());
        assertThat(game.getLaserRange()).isEqualTo(game2.getLaserRange());
    }

    @Test
    void getUnknownGame() {
        APINotFoundException thrown = assertThrows(
            APINotFoundException.class,
            () -> MarsRoverModel.getGame("testGameJKWWLKNLKN"),
            "Expected getGame() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Game not found."));
    }

    @Test
    void createRover() throws IOException, ClassNotFoundException, APINotFoundException, APIAlreadyExistsException {
        MarsRoverModel.createGame("testGameC");
        MarsRoverModel.createRover("testGameC", "roverC");

        HashMap<String, Game> games = MarsRoverModel.readinFile();
        assertThat(games.get("testGameC").getPlanetMap().getRoverList().containsKey("roverC"));
    }

    @Test
    void getRover() throws IOException, ClassNotFoundException, APINotFoundException, APIAlreadyExistsException {
        Game game = MarsRoverModel.createGame("testGameF");
        MarsRoverImpl rover = game.generateRover("roverC");

        MarsRoverImpl rover2 = MarsRoverModel.getRover(game, "roverC");

        assertThat(rover.getName()).isEqualTo(rover2.getName());
        assertThat(rover.getLaserRange()).isEqualTo(rover2.getLaserRange());
    }

    @Test
    void getUnknownRover() throws IOException, ClassNotFoundException, APINotFoundException, APIAlreadyExistsException {
        Game game = MarsRoverModel.createGame("testGameD");

        APINotFoundException thrown = assertThrows(
            APINotFoundException.class,
            () -> MarsRoverModel.getRover(game, "roverUIUHCDINCSC"),
            "Expected getRover() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Player not found."));
    }

    @Test
    void moveRover() throws IOException, ClassNotFoundException, APINotFoundException, APIAlreadyExistsException {
        Game game = MarsRoverModel.createGame("testGameE");
        MarsRoverImpl rover = MarsRoverModel.createRover("testGameD", "roverD");

        MarsRoverImpl rover2 = MarsRoverModel.moveRover("testGameD", "roverD", "f");

        assertThat(rover.getName()).isEqualTo(rover2.getName());
        assertThat(rover.getLaserRange()).isEqualTo(rover2.getLaserRange());
    }
}
