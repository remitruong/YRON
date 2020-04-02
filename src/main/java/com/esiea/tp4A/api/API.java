package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.Position;

public interface API {
    default Position getPositionOfRover(MarsRoverImpl rover) {
        return rover.getPosition();
    }

    default Integer getLaserRange(MarsRoverImpl rover) {
        return rover.getLaserRange();
    }

    default Position moveRover(MarsRoverImpl rover, Character[] commands) {
        return rover.move(commands);
    }

    default Position shotRoverLaser(MarsRoverImpl rover) {
        return rover.shootLaser();
    }

    default Boolean isRoverAlive(MarsRoverImpl rover) {
        return Boolean.TRUE;
    }
}
