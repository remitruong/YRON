package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UtilsTest {
    private final Utils utils = new Utils();

    @Test
    void random_int() {
        int rand = utils.randomInt(5);
        assertThat(rand)
            .as("random int between 0 and 4")
            .isBetween(0, 5);
    }

    @Test
    void randomIntForPosition() {
        int rand = utils.randomIntForPosition(50);
        assertThat(rand)
            .as("random int between -24 and 25")
            .isBetween(-24, 25);
    }

    @Test
    void randomDirection() {
        Direction rand = utils.randomDirection();
        assertThat(rand)
            .as("random directions")
            .isIn(Direction.values());
    }

    @Test
    void randomPosition() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(50);
        Position rand = utils.randomPosition(planetMap.getSizeOfTheMap(), planetMap);

        assertThat(rand.getX())
            .as("Random X on map")
            .isBetween(-24, 25);

        assertThat(rand.getY())
                .as("Random Y on map")
                .isBetween(-24, 25);
    }

    @Test
    void randomPosition_withOnlyOnePositionFree() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(2);
        Position obstaclePosition1 = Position.of(0, 0, Direction.NORTH);
        Position obstaclePosition2 = Position.of(0, -1, Direction.NORTH);
        Position obstaclePosition3 = Position.of(-1, 0, Direction.NORTH);
        planetMap.addObstaclePosition(obstaclePosition1);
        planetMap.addObstaclePosition(obstaclePosition2);
        planetMap.addObstaclePosition(obstaclePosition3);

        Position rand = utils.randomPosition(planetMap.getSizeOfTheMap(), planetMap);

        assertThat(rand)
                .as("Random X and Y on map with only one position free")
                .extracting(Position::getX, Position::getY)
                .isEqualTo(List.of(-1, -1));

    }

    @Test
    void generateRandMapSize() {
        int rand = utils.generateRandMapSize();

        assertThat(rand)
            .as("Random map size")
            .isIn(100,300,600);
    }

    @Test
    void generateRandLaserRange() {
        int rand = utils.generateRandLaserRange(100);

        assertThat(rand)
            .as("Random map size")
            .isIn(5,30,101);
    }
}