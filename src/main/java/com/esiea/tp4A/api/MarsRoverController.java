package com.esiea.tp4A.api;


import com.esiea.tp4A.domain.Game;
import com.esiea.tp4A.domain.MarsRoverImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MarsRoverController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to the Mars Rover API !";
    }

    @PostMapping("/api/game/{gameName}")
    public ResponseEntity createGame(@PathVariable String gameName) throws IOException, ClassNotFoundException {
        try {
            Game game = MarsRoverModel.createGame(gameName);
            return ResponseEntity.ok(game);
        } catch (APIAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    @RequestMapping("/api/game/{gameName}")
    public ResponseEntity getGame(@PathVariable String gameName) {
        try {
            Game game = MarsRoverModel.getGame(gameName);
            return ResponseEntity.ok(game);
        } catch (APINotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    @PostMapping("/api/game/{gameName}/player/{playerName}")
    public ResponseEntity createPlayer(@PathVariable String gameName, @PathVariable String playerName) {
        try {
            MarsRoverImpl rover = MarsRoverModel.createRover(gameName, playerName);
            return ResponseEntity.ok(rover);
        } catch (APIAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (APINotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    @RequestMapping("/api/game/{gameName}/player/{playerName}")
    public ResponseEntity getPlayer(@PathVariable String gameName, @PathVariable String playerName) {
        try {
            Game game = MarsRoverModel.getGame(gameName);
            MarsRoverImpl rover = MarsRoverModel.getRover(game, playerName);
            return ResponseEntity.ok(rover);
        } catch (APINotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    @PatchMapping("/api/game/{gameName}/player/{playerName}/{command}")
    public ResponseEntity command(@PathVariable String gameName, @PathVariable String playerName,
                                  @PathVariable String command) {
        if (!command.matches("[fblrs]")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong command given.");
        }
        try {
            MarsRoverImpl rover = MarsRoverModel.moveRover(gameName, playerName, command);
            return ResponseEntity.ok(rover);
        } catch (APINotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }
}
