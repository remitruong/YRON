package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UtilsTest {

    @Test
    void random_int() {
        int rand = Utils.randomInt(5);
        assertThat(rand)
            .as("random int between 0 and 4")
            .isBetween(0, 5);
    }

    @Test
    void randomIntForPosition() {
        int rand = Utils.randomIntForPosition(50);
        assertThat(rand)
            .as("random int between -24 and 25")
            .isBetween(-24, 25);
    }

    @Test
    void randomDirection() {
        Direction rand = Utils.randomDirection();
        assertThat(rand)
            .as("random directions")
            .isIn(Direction.values());
    }

    @Test
    void randomPosition() {
        PlanetMapImpl planetMap = (PlanetMapImpl) new PlanetMapImpl().initialize(50);
        Position rand = Utils.randomPosition(planetMap.getSizeOfTheMap(), planetMap);

        assertThat(rand.getX())
            .as("Random X on map")
            .isBetween(-24, 25);

        assertThat(rand.getY())
                .as("Random Y on map")
                .isBetween(-24, 25);
    }

    @Test
    void generateRandMapSize() {
        int rand = Utils.generateRandMapSize();

        assertThat(rand)
            .as("Random map size")
            .isIn(100,300,600);
    }

    @Test
    void generateRandLaserRange() {
        int rand = Utils.generateRandLaserRange(100);

        assertThat(rand)
            .as("Random map size")
            .isIn(5,30,101);
    }
}