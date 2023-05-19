package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.contollers.InventoryController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PlayerTest {
    /**
     * Method under test: {@link Player#Player()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at cz.cvut.anokhver.enteties.Player.<init>(Player.java:56)
        //   See https://diff.blue/R013 to resolve this issue.

        new Player();
    }

    /**
     * Method under test: {@link Player#Player(float, float, float, float, double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at cz.cvut.anokhver.additional.PlayerConfigurations.getTexture(PlayerConfigurations.java:34)
        //       at cz.cvut.anokhver.enteties.Player.<init>(Player.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        new Player(10.0f, 10.0f, 10.0f, 10.0f, 10.0d);

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Player#setCoins(Integer)}
     *   <li>{@link Player#setDamage(float)}
     *   <li>{@link Player#setDamage_radius(double)}
     *   <li>{@link Player#setHealth(float)}
     *   <li>{@link Player#setInventory(InventoryController)}
     *   <li>{@link Player#setSpeed_damage(float)}
     *   <li>{@link Player#setStar_counter(int)}
     *   <li>{@link Player#getCoins()}
     *   <li>{@link Player#getDamage()}
     *   <li>{@link Player#getDamage_radius()}
     *   <li>{@link Player#getHealth()}
     *   <li>{@link Player#getInventory()}
     *   <li>{@link Player#getSpeed_damage()}
     *   <li>{@link Player#getStar_counter()}
     * </ul>
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetCoins() {
        // TODO: Complete this test.
        //   Reason: R081 Exception in arrange section.
        //   Diffblue Cover was unable to construct an instance of the class under test using
        //   cz.cvut.anokhver.enteties.Player.<init>.
        //   A step in the arrange section threw
        //   java.lang.NullPointerException
        //       at cz.cvut.anokhver.enteties.Player.<init>(Player.java:56)
        //   See https://diff.blue/R081 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        Player player = null;
        Integer coins = null;

        // Act
        player.setCoins(coins);
        float damage = 0.0f;
        player.setDamage(damage);
        double damage_radius = 0.0d;
        player.setDamage_radius(damage_radius);
        float health = 0.0f;
        player.setHealth(health);
        InventoryController inventory = null;
        player.setInventory(inventory);
        float speed_damage = 0.0f;
        player.setSpeed_damage(speed_damage);
        int star_counter = 0;
        player.setStar_counter(star_counter);
        Integer actualCoins = player.getCoins();
        float actualDamage = player.getDamage();
        double actualDamage_radius = player.getDamage_radius();
        float actualHealth = player.getHealth();
        InventoryController actualInventory = player.getInventory();
        float actualSpeed_damage = player.getSpeed_damage();
        int actualStar_counter = player.getStar_counter();

        // Assert
        // TODO: Add assertions on result
    }
}

