package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.*;

import java.io.*;
import java.util.HashMap;

public interface MarsRoverModel {
    String FILE_NAME = "data.tmp";

    static Game createGame(String gameName) throws IOException, ClassNotFoundException, APIAlreadyExistsException {
        try {
            getGame(gameName);
            throw new APIAlreadyExistsException("Game already exists.");
        } catch (APINotFoundException e) {
            Game game = new Game(gameName);
            writeInFile(game);
            return game;
        }
    }

    static Game getGame(String gameName) throws IOException, ClassNotFoundException, APINotFoundException {
        HashMap<String, Game> games = readinFile();
        if (games.get(gameName) == null)
            throw new APINotFoundException("Game not found.");
        else
            return games.get(gameName);
    }

    static MarsRoverImpl createRover(String gameName, String playerName) throws IOException, ClassNotFoundException, APINotFoundException, APIAlreadyExistsException {
        Game game = getGame(gameName);
        try {
            getRover(game, playerName);
            throw new APIAlreadyExistsException("Player already exists.");
        } catch (APINotFoundException e)  {
            MarsRoverImpl rover = game.generateRover(playerName);
            writeInFile(game);
            return rover;
        }
    }

    static MarsRoverImpl getRover(Game game, String roverName) throws APINotFoundException {
        MarsRoverImpl rover = game.getPlanetMap().getRoverByName(roverName);
        if (rover == null)
            throw new APINotFoundException("Player not found.");
        else
            return rover;
    }

    static MarsRoverImpl moveRover(String gameName, String roverName, String command) throws IOException, ClassNotFoundException, APINotFoundException {
        Game game = getGame(gameName);
        MarsRoverImpl rover = getRover(game, roverName);
        rover.move(command);
        writeInFile(game);
        return rover;
    }

    static HashMap<String, Game> readinFile() throws IOException, ClassNotFoundException {
        HashMap<String, Game> games;
        FileInputStream fis = new FileInputStream(FILE_NAME);

        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            if (obj instanceof HashMap<?, ?>) {
                games = (HashMap<String, Game>) obj;
            } else {
                games = new HashMap<>();
            }
            ois.close();
        } catch (EOFException e) {
            games = new HashMap<>();
        }

        return games;
    }

    static void writeInFile(Game game) throws IOException, ClassNotFoundException {
        HashMap<String, Game> games = readinFile();
        games.put(game.getId(), game);
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(games);
        oos.close();
    }
}
