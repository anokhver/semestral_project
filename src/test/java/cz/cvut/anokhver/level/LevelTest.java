package cz.cvut.anokhver.level;

import cz.cvut.anokhver.GameLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.enteties.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LevelTest {

    private Level level;

    @BeforeEach
    void setUp() {
        new GameLauncher();
        GameLauncher.setUplog();
        level = new Level(1); // Create a Level instance with ID 1 for testing
    }

    @Test
    void startTimer() {
        // Test startTimer method
        level.startTimer();
        assertNotNull(level.getTimer());
    }

    @Test
    void getId() {
        // Test getId method
        assertNotNull(level.getId());
    }

    @Test
    void getMap() {
        // Test getMap method
        assertNotNull(level.getMap());
    }

    @Test
    void setMap() {
        // Test setMap method
        Tilemap map = new Tilemap(10, 10); // Example map instance
        level.setMap(map);
        assertEquals(map, level.getMap());
    }

    @Test
    void getStars() {
        // Test getStars method
        assertNotNull(level.getStars());
    }

    @Test
    void setStars() {
        // Test setStars method
        List<Star> stars = new ArrayList<>(); // Example stars list
        level.setStars(stars);
        assertEquals(stars, level.getStars());
    }

    @Test
    void getEnemies() {
        // Test getEnemies method
        assertNotNull(level.getEnemies());
    }

    @Test
    void setEnemies() {
        // Test setEnemies method
        List<Enemy> enemies = new ArrayList<>(); // Example enemies list
        level.setEnemies(enemies);
        assertEquals(enemies, level.getEnemies());
    }

    @Test
    void getEnemyCount() {
        // Test getEnemyCount method
        assertNotNull(level.getEnemyCount());
    }

    @Test
    void setEnemyCount() {
        // Test setEnemyCount method
        level.setEnemyCount(5); // Example enemy count
        assertEquals(5, level.getEnemyCount());
    }


    @Test
    void setElapsedSeconds() {
        // Test setElapsedSeconds method
        level.setElapsedSeconds(10); // Example elapsed seconds
        assertEquals(10, level.getElapsedSeconds());
    }


    @Test
    void setTotalTime() {
        // Test setTotalTime method
        level.setTotalTime(60); // Example total time
        assertEquals(60, level.getTotalTime());
    }
}
