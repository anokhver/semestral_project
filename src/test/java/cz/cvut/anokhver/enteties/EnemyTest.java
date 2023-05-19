package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EnemyTest {
    /**
     * Method under test: {@link Enemy#Enemy(String, float, float, float, double, float, float, Coordinates)}
     */
    @Test
    void testConstructor() {
        new Enemy("Name", 10.0f, 10.0f, 10.0f, 10.0d, 10.0f, 10.0f, new Coordinates(2, 3));
    }

    /**
     * Method under test: {@link Enemy#Enemy(String, Coordinates)}
     */
    @Test
    void testConstructor2() {
        new Enemy("Con Name", new Coordinates(2, 3));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Enemy#setCurDir(Direction)}
     *   <li>{@link Enemy#setHealth(float)}
     *   <li>{@link Enemy#setSpeedDamage(float)}
     *   <li>{@link Enemy#getCurDirection()}
     *   <li>{@link Enemy#getDamage()}
     *   <li>{@link Enemy#getDamageRadius()}
     *   <li>{@link Enemy#getHealth()}
     *   <li>{@link Enemy#getName()}
     *   <li>{@link Enemy#getSeeRadius()}
     *   <li>{@link Enemy#getSpeedDamage()}
     * </ul>
     */
    @Test
    void testSetCurDir() {
        Enemy enemy = null;
        Direction cur_dir = Direction.TOP;

        // Act
        enemy.setCurDir(cur_dir);
        float health = 0.0f;
        enemy.setHealth(health);
        float speedDamage = 0.0f;
        enemy.setSpeedDamage(speedDamage);
        Direction actualCurDirection = enemy.getCurDirection();
        float actualDamage = enemy.getDamage();
        double actualDamageRadius = enemy.getDamageRadius();
        float actualHealth = enemy.getHealth();
        String actualName = enemy.getName();
        float actualSeeRadius = enemy.getSeeRadius();
        float actualSpeedDamage = enemy.getSpeedDamage();


    }
}

