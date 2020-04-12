package com.esiea.tp4A.api;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MarsRoverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Welcome to the Mars Rover API !")));
    }

    @Test
    public void testCreateGame() throws Exception {
        this.mockMvc.perform(post("/api/game/testGame"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame")));
    }

    @Test
    public void testCreateGameTwice() throws Exception {
        this.mockMvc.perform(post("/api/game/testGame2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame2")));

        // Check raises Bad Request
        this.mockMvc.perform(post("/api/game/testGame2"))
            .andExpect(status().isConflict())
            .andExpect(content().string(containsString("Game already exists.")));
    }

    @Test
    public void testGetGame() throws Exception {
        // Create
        this.mockMvc.perform(post("/api/game/testGame3"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame3")));

        // Then Get
        this.mockMvc.perform(get("/api/game/testGame3"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame3")));
    }

    @Test
    public void testGetUnkownGame() throws Exception {
        this.mockMvc.perform(get("/api/game/unknownTestGame"))
            .andExpect(status().isNotFound())
            .andExpect(content().string(containsString("Game not found.")));
    }

    @Test
    public void testCreatePlayer() throws Exception {
        // Create game
        this.mockMvc.perform(post("/api/game/testGame5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame5")));

        // Then create player
        this.mockMvc.perform(post("/api/game/testGame5/player/testPlayer1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer1")));
    }

    @Test
    public void testCreatePlayerTwice() throws Exception {
        // Create game
        this.mockMvc.perform(post("/api/game/testGame55"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame55")));

        // Then create player
        this.mockMvc.perform(post("/api/game/testGame55/player/testPlayer2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer2")));

        // Create twice
        this.mockMvc.perform(post("/api/game/testGame55/player/testPlayer2"))
            .andExpect(status().isConflict())
            .andExpect(content().string(containsString("Player already exists.")));
    }

    @Test
    public void testGetPlayer() throws Exception {
        // Create game
        this.mockMvc.perform(post("/api/game/testGame6"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame6")));

        // Then create player
        this.mockMvc.perform(post("/api/game/testGame6/player/testPlayer3"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer3")));

        // Then Get Player
        this.mockMvc.perform(get("/api/game/testGame6/player/testPlayer3"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer3")));
    }

    @Test
    public void testGetUnknownPlayer() throws Exception {
        // Create game
        this.mockMvc.perform(post("/api/game/testGame7"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame7")));

        this.mockMvc.perform(get("/api/game/testGame7/player/testUnknownPlayer"))
            .andExpect(status().isNotFound())
            .andExpect(content().string(containsString("Player not found.")));
    }

    @Test
    public void testPatchPlayer() throws Exception {
        // Create game
        this.mockMvc.perform(post("/api/game/testGame8"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame8")));

        // Then create player
        this.mockMvc.perform(post("/api/game/testGame8/player/testPlayer4"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer4")));

        // Then Get Player
        this.mockMvc.perform(get("/api/game/testGame8/player/testPlayer4"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer4")));

        // Then Patch Player
        this.mockMvc.perform(patch("/api/game/testGame8/player/testPlayer4/s"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer4")));
    }

    @Test
    public void testPatchUnknownPlayer() throws Exception {
        // Create game
        this.mockMvc.perform(post("/api/game/testGame9"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame9")));

        // Then Patch Player
        this.mockMvc.perform(patch("/api/game/testGame9/player/testUnknownPlayer/s"))
            .andExpect(status().isNotFound())
            .andExpect(content().string(containsString("Player not found.")));
    }

    @Test
    public void testPatchUnknownCommand() throws Exception {
        // Create game
        this.mockMvc.perform(post("/api/game/testGame10"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("id", Matchers.is("testGame10")));

        // Then create player
        this.mockMvc.perform(post("/api/game/testGame10/player/testPlayer5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("testPlayer5")));

        // Then Patch Player
        this.mockMvc.perform(patch("/api/game/testGame10/player/testPlayer5/wrong"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("Wrong command given.")));
    }
}
