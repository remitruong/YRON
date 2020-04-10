package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.MarsRover;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarsRoverController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to the Mars Rover API !";
    }

    @PostMapping("/api/player/{playerName}")
    public ResponseEntity createPlayer(@PathVariable String playerName) {
        MarsRover rover = MarsRoverModel.createRover(playerName);

        return ResponseEntity.ok(rover);
    }

    @RequestMapping("/api/player/{playerName}")
    public ResponseEntity getPlayer(@PathVariable String playerName) {
        MarsRover rover = MarsRoverModel.getRover(playerName);
        if (rover == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(rover);
    }

    @PatchMapping("/api/player/{playerName}/{command}")
    public ResponseEntity command(@PathVariable String playerName, @PathVariable String command) {
        if (command.equals("shot")) {

        } else if (command.equals("moveUp")) {

        } else if (command.equals("moveDown")) {

        } else if (command.equals("moveLeft")) {

        } else if (command.equals("moveRight")) {

        }
    }
}
