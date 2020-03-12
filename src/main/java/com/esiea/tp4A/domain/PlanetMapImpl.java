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
        return this.obstaclePositions;
    }

    // Add an obstacle, if the obstacle position is already used : return null
    public boolean addObstaclePosition(Position position) {
        if (!this.isPositionOnMap(position)) {
            this.obstaclePositions.add(position);
            return true;
        }

        return false;
    }

    // Remove an obstacle of the map, and return true if the obstacle is found, false if not
    public boolean removeObstaclePosition(Position position) {
        if (this.isPositionOnMap(position)) {
            this.obstaclePositions.removeIf(pos -> pos.getX() == position.getX() &&
                    pos.getY() == position.getY());
            return true;
        }

        return false;
    }

    boolean isPositionOnMap(Position position) {
        for (Position p : this.obstaclePositions) {
            if (p.getX() == position.getX() && p.getY() == position.getY()) {
                return true;
            }
        }

        return false;
    }
}
