package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface MarsRoverModel {
    String FILE_NAME = "data.tmp";

    static MarsRover createRover(String roverName) {
        MarsRoverImpl rover = new MarsRoverImpl();
        rover.initialize(Position.of(0, 0, Direction.NORTH));
        try {
            writeinFile(rover);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rover;
    }

    static MarsRover getRover(String roverName) {
        try {
            List<MarsRover> rovers = readinFile();
            for (MarsRover rover : rovers) {
                if (rover.getName().equals(roverName)) {
                    return rover;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    static List<MarsRover> readinFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<MarsRover> rovers = (List<MarsRover>) ois.readObject();
        ois.close();
        return rovers;
    }

    static void writeinFile(MarsRover rover) throws IOException, ClassNotFoundException {
        // List<MarsRover> rovers = readinFile();
        List<MarsRover> rovers = new ArrayList<>();
        rovers.add(rover);
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(rovers);
        oos.close();
    }
}
