package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void getName() {
        String name = "spider";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        assertEquals(name, enemy.getName());
    }

    @Test
    void getDamage() {
        String name = "spider";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        assertEquals(damage, enemy.getDamage());
    }

    @Test
    void getHealth() {
        String name = "spider";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        assertEquals(health, enemy.getHealth());
    }

    @Test
    void setHealth() {
        String name = "spider";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        float newHealth = 80.0f;
        enemy.setHealth(newHealth);

        assertEquals(newHealth, enemy.getHealth());
    }

    @Test
    void getSpeedDamage() {
        String name = "spider";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        assertEquals(speedDamage, enemy.getSpeedDamage());
    }

    @Test
    void setSpeedDamage() {
        String name = "spider";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        float newSpeedDamage = 6.0f;
        enemy.setSpeedDamage(newSpeedDamage);

        assertEquals(newSpeedDamage, enemy.getSpeedDamage());
    }

    @Test
    void getSeeRadius() {
        String name = "moth";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        assertEquals(seeRadius, enemy.getSeeRadius());
    }

    @Test
    void getDamageRadius() {
        String name = "frog";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        assertEquals(damageRadius, enemy.getDamageRadius());
    }

    @Test
    void getCurDirection() {
        String name = "frog";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        assertEquals(Direction.STOP, enemy.getCurDirection());
    }

    @Test
    void setCurDir() {
        String name = "frog";
        float damage = 10.0f;
        float walkSpeed = 5.0f;
        float seeRadius = 20.0f;
        double damageRadius = 15.0;
        float speedDamage = 8.0f;
        float health = 100.0f;
        Coordinates coordinates = new Coordinates(0, 0);

        Enemy enemy = new Enemy(name, damage, walkSpeed, seeRadius, damageRadius, speedDamage, health, coordinates);

        Direction newDirection = Direction.LEFT;
        enemy.setCurDir(newDirection);

        assertEquals(newDirection, enemy.getCurDirection());
    }
}
