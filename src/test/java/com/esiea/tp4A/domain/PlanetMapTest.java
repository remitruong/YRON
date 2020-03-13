package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlanetMapTest {
    /*
     * Adding obstacle
     */
    @Test
    void add_obstacle_status() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        boolean status = planetMap.addObstaclePosition(obstaclePosition);

        assertThat(status)
                .as("addObstaclePosition status after attempt")
                .isEqualTo(true);

    }

    @Test
    void add_obstacle_position() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        boolean status = planetMap.addObstaclePosition(obstaclePosition);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);
    }

    @Test
    void add_same_obstacle_twice() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        boolean status1 = planetMap.addObstaclePosition(obstaclePosition);
        boolean status2 = planetMap.addObstaclePosition(obstaclePosition);

        assertThat(status1)
                .as("First addObstaclePosition status attempt")
                .isEqualTo(true);

        assertThat(status2)
                .as("Second addObstaclePosition status attempt")
                .isEqualTo(false);

        assertThat(planetMap.obstaclePositions())
                .as("Check if map contains only one occurence of the obstacle")
                .containsOnlyOnce(obstaclePosition);
    }

    /*
     * Remove obstacle
     */
    @Test
    void remove_obstacle_status() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition); // add obstacle
        boolean status = planetMap.removeObstaclePosition(obstaclePosition);

        assertThat(status)
                .as("removeObstaclePosition status after attempt")
                .isEqualTo(true);
    }

    @Test
    void remove_obstacle_position() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition); // add obstacle
        boolean status = planetMap.removeObstaclePosition(obstaclePosition);

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well removed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void remove_same_obstacle_twice() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize();
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition); // add obstacle

        boolean status1 = planetMap.removeObstaclePosition(obstaclePosition);
        boolean status2 = planetMap.removeObstaclePosition(obstaclePosition);

        assertThat(status1)
                .as("First removeObstaclePosition status attempt")
                .isEqualTo(true);

        assertThat(status2)
                .as("Second removeObstaclePosition status attempt")
                .isEqualTo(false);

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well removed")
                .doesNotContain(obstaclePosition);
    }
}
