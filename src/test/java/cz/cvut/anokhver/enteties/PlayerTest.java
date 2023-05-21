package cz.cvut.anokhver.enteties;

import static org.junit.jupiter.api.Assertions.*;

import cz.cvut.anokhver.contollers.InventoryController;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void getStar_counter() {
        int starCounter = 5;
        Player player = new Player();
        player.setStar_counter(starCounter);

        assertEquals(starCounter, player.getStar_counter());
    }

    @Test
    void setStar_counter() {
        int starCounter = 5;
        Player player = new Player();
        player.setStar_counter(starCounter);

        assertEquals(starCounter, player.getStar_counter());
    }

    @Test
    void getDamage() {
        float damage = 10.0f;
        Player player = new Player(damage, 5.0f, 100.0f, 8.0f, 15.0);

        assertEquals(damage, player.getDamage());
    }

    @Test
    void setDamage() {
        float damage = 10.0f;
        Player player = new Player(damage, 5.0f, 100.0f, 8.0f, 15.0);

        float newDamage = 12.0f;
        player.setDamage(newDamage);

        assertEquals(newDamage, player.getDamage());
    }

    @Test
    void getHealth() {
        float health = 100.0f;
        Player player = new Player(10.0f, 5.0f, health, 8.0f, 15.0);

        assertEquals(health, player.getHealth());
    }

    @Test
    void setHealth() {
        float health = 100.0f;
        Player player = new Player(10.0f, 5.0f, health, 8.0f, 15.0);

        float newHealth = 80.0f;
        player.setHealth(newHealth);

        assertEquals(newHealth, player.getHealth());
    }

    @Test
    void getSpeed_damage() {
        float speedDamage = 8.0f;
        Player player = new Player(10.0f, 5.0f, 100.0f, speedDamage, 15.0);

        assertEquals(speedDamage, player.getSpeed_damage());
    }

    @Test
    void setSpeed_damage() {
        float speedDamage = 8.0f;
        Player player = new Player(10.0f, 5.0f, 100.0f, speedDamage, 15.0);

        float newSpeedDamage = 6.0f;
        player.setSpeed_damage(newSpeedDamage);

        assertEquals(newSpeedDamage, player.getSpeed_damage());
    }

    @Test
    void getCoins() {
        Integer coins = 100;
        Player player = new Player();
        player.setCoins(coins);

        assertEquals(coins, player.getCoins());
    }

    @Test
    void setCoins() {
        Integer coins = 100;
        Player player = new Player();
        player.setCoins(coins);

        assertEquals(coins, player.getCoins());
    }

    @Test
    void setInventory() {
        InventoryController inventory = new InventoryController(false, false, false, 0, 10, false, false, false);
        Player player = new Player();
        player.setInventory(inventory);

        assertEquals(inventory, player.getInventory());
    }

    @Test
    void getDamage_radius() {
        double damageRadius = 15.0;
        Player player = new Player(10.0f, 5.0f, 100.0f, 8.0f, damageRadius);

        assertEquals(damageRadius, player.getDamage_radius());
    }

    @Test
    void setDamage_radius() {
        double damageRadius = 15.0;
        Player player = new Player(10.0f, 5.0f, 100.0f, 8.0f, damageRadius);

        double newDamageRadius = 20.0;
        player.setDamage_radius(newDamageRadius);

        assertEquals(newDamageRadius, player.getDamage_radius());
    }
}
