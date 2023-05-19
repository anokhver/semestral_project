package cz.cvut.anokhver.level;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Star;

import java.util.List;

import javafx.animation.Timeline;
import org.junit.jupiter.api.Test;

class LevelTest {
    /**
     * Method under test: {@link Level#Level(int)}
     */
    @Test
    void testConstructor() {
        new Level(1);
    }

    /**
     * Method under test: {@link Level#configureMap(String)}
     */
    @Test
    void testConfigureMap() {
        (new Level(1)).configureMap("Dir");
    }

    /**
     * Method under test: {@link Level#startTimer()}
     */
    @Test
    void testStartTimer() {
        (new Level(1)).startTimer();
    }

    /**
     * Method under test: {@link Level#getRemainingTime()}
     */
    @Test
    void testGetRemainingTime() {
        (new Level(1)).getRemainingTime();
    }

    /**
     * Method under test: {@link Level#stopTimer()}
     */
    @Test
    void testStopTimer() {
        (new Level(1)).stopTimer();
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Level#setElapsedSeconds(int)}
     *   <li>{@link Level#setEnemies(List)}
     *   <li>{@link Level#setEnemyCount(Integer)}
     *   <li>{@link Level#setMap(Tilemap)}
     *   <li>{@link Level#setStars(List)}
     *   <li>{@link Level#setTimer(Timeline)}
     *   <li>{@link Level#setTotalTime(int)}
     *   <li>{@link Level#getElapsedSeconds()}
     *   <li>{@link Level#getEnemies()}
     *   <li>{@link Level#getEnemyCount()}
     *   <li>{@link Level#getId()}
     *   <li>{@link Level#getMap()}
     *   <li>{@link Level#getStars()}
     *   <li>{@link Level#getTimer()}
     *   <li>{@link Level#getTotalTime()}
     * </ul>
     */
    @Test
    void testSetElapsedSeconds() {

        Level level = null;
        int elapsedSeconds = 0;

        // Act
        level.setElapsedSeconds(elapsedSeconds);
        List<Enemy> enemies = null;
        level.setEnemies(enemies);
        Integer enemyCount = null;
        level.setEnemyCount(enemyCount);
        Tilemap map = null;
        level.setMap(map);
        List<Star> stars = null;
        level.setStars(stars);
        Timeline timer = null;
        level.setTimer(timer);
        int totalTime = 0;
        level.setTotalTime(totalTime);
        int actualElapsedSeconds = level.getElapsedSeconds();
        List<Enemy> actualEnemies = level.getEnemies();
        Integer actualEnemyCount = level.getEnemyCount();
        Integer actualId = level.getId();
        Tilemap actualMap = level.getMap();
        List<Star> actualStars = level.getStars();
        Timeline actualTimer = level.getTimer();
        int actualTotalTime = level.getTotalTime();

        // Assert
        // TODO: Add assertions on result
    }
}

