package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlanetMapTest {
    /*
     * Adding obstacle
     */
    @Test
    void add_obstacle_status() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        boolean status = planetMap.addObstaclePosition(obstaclePosition);

        assertThat(status)
                .as("addObstaclePosition status after attempt")
                .isEqualTo(true);

    }

    @Test
    void add_obstacle_position() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        boolean status = planetMap.addObstaclePosition(obstaclePosition);

        assertThat(planetMap.obstaclePositions())
                .as("Check if obstacle is on map")
                .contains(obstaclePosition);
    }

    @Test
    void add_same_obstacle_twice() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
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
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition); // add obstacle
        boolean status = planetMap.removeObstaclePosition(obstaclePosition);

        assertThat(status)
                .as("removeObstaclePosition status after attempt")
                .isEqualTo(true);
    }

    @Test
    void remove_obstacle_position() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        Position obstaclePosition = Position.of(1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition); // add obstacle
        boolean status = planetMap.removeObstaclePosition(obstaclePosition);

        assertThat(planetMap.obstaclePositions())
                .as("Check if the obstacle is well removed")
                .doesNotContain(obstaclePosition);
    }

    @Test
    void remove_same_obstacle_twice() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
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

    @Test
    void planet_size(){
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        int limit = planetMap.getSizeOfTheMap();

        assertThat(limit)
            .as("Test the size of the map")
            .isEqualTo(100);
    }

    @Test
    void spherical_planet_100(){
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        Position position1 = Position.of(50, 0, Direction.NORTH);
        boolean isLimit1 = planetMap.isLimitOfMap(position1);

        Position position2 = Position.of(0, 50, Direction.SOUTH);
        boolean isLimit2 = planetMap.isLimitOfMap(position2);

        Position position3 = Position.of(50, 50, Direction.WEST);
        boolean isLimit3 = planetMap.isLimitOfMap(position3);

        Position position4 = Position.of(-49, 0, Direction.EAST);
        boolean isLimit4 = planetMap.isLimitOfMap(position4);

        Position position5 = Position.of(0, -49, Direction.NORTH);
        boolean isLimit5 = planetMap.isLimitOfMap(position5);

        Position position6 = Position.of(-49, -49, Direction.NORTH);
        boolean isLimit6 = planetMap.isLimitOfMap(position6);

        assertThat(isLimit1)
            .as("Check if the position is a limit of the map 50 0 ")
            .isEqualTo(true);

        assertThat(isLimit2)
            .as("Check if the position is a limit of the map 0 50")
            .isEqualTo(true);

        assertThat(isLimit3)
            .as("Check if the position is a limit of the map 50 50")
            .isEqualTo(true);

        assertThat(isLimit4)
            .as("Check if the position is a limit of the map -49 0")
            .isEqualTo(true);

        assertThat(isLimit5)
            .as("Check if the position is a limit of the map 0 -49")
            .isEqualTo(true);

        assertThat(isLimit6)
            .as("Check if the position is a limit of the map -49 -49")
            .isEqualTo(true);
    }

    @Test
    void spherical_planet_200(){
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(200);
        Position position1 = Position.of(100, 0, Direction.NORTH);
        boolean isLimit1 = planetMap.isLimitOfMap(position1);

        Position position2 = Position.of(0, 100, Direction.SOUTH);
        boolean isLimit2 = planetMap.isLimitOfMap(position2);

        Position position3 = Position.of(100, 100, Direction.WEST);
        boolean isLimit3 = planetMap.isLimitOfMap(position3);

        Position position4 = Position.of(-99, 0, Direction.EAST);
        boolean isLimit4 = planetMap.isLimitOfMap(position4);

        Position position5 = Position.of(0, -99, Direction.NORTH);
        boolean isLimit5 = planetMap.isLimitOfMap(position5);

        Position position6 = Position.of(-99, -99, Direction.NORTH);
        boolean isLimit6 = planetMap.isLimitOfMap(position6);

        assertThat(isLimit1)
            .as("Check if the position is a limit of the map 100 0 ")
            .isEqualTo(true);

        assertThat(isLimit2)
            .as("Check if the position is a limit of the map 0 100")
            .isEqualTo(true);

        assertThat(isLimit3)
            .as("Check if the position is a limit of the map 100 100")
            .isEqualTo(true);

        assertThat(isLimit4)
            .as("Check if the position is a limit of the map -99 0")
            .isEqualTo(true);

        assertThat(isLimit5)
            .as("Check if the position is a limit of the map 0 -99")
            .isEqualTo(true);

        assertThat(isLimit6)
            .as("Check if the position is a limit of the map -99 -99")
            .isEqualTo(true);
    }

    @Test
    void generate_Obstacle_100(){
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(100);
        planetMap.generateObstacle(100);

        assertThat(planetMap.getObstaclePositions().size())
            .as("Check numbers of obstacle on map of 100")
            .isEqualTo(15);
    }

    @Test
    void generate_Obstacle_300(){
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(300);
        planetMap.generateObstacle(300);

        assertThat(planetMap.getObstaclePositions().size())
            .as("Check numbers of obstacle on map of 300")
            .isEqualTo(45);
    }

    @Test
    void generate_Obstacle_600(){
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(600);
        planetMap.generateObstacle(600);

        assertThat(planetMap.getObstaclePositions().size())
            .as("Check numbers of obstacle on map")
            .isEqualTo(90);
    }

}
