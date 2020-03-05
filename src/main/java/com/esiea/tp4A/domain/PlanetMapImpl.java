package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {
    private Set<Position> obstaclePositions;

    public PlanetMap initialize() {
        initialize(new HashSet<Position>());
        return this;
    }

    public PlanetMap initialize(Set<Position> positions) {
        this.obstaclePositions = positions;
        return this;
    }

    @Override
    public Set<Position> obstaclePositions() {
        // TODO : Renvoyer un ensemble de position correspondant à des obstacles non franchissables
        return null;
    }

    public boolean contains(Position p) {
        return this.obstaclePositions.contains(p);
    }

    // Add an obstacle, if the obstacle position is already used : return null
    public Position addObstaclePosition(Position position) {
        Position result = position;

        if(!this.obstaclePositions.contains(position))
            this.obstaclePositions.add(position);
        else
            result = null;

        return result;
    }

    // Remove an obstacle of the map, and return true if the obstacle is found, false if not
    public boolean removeObstaclePosition(Position position) {
        boolean result = false;

        if(this.obstaclePositions.contains(position)) {
            this.obstaclePositions.remove(position);
            result = true;
        }

        return result;
    }
}
