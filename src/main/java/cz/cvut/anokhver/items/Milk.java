package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Player;

/**
 * Milk item heath the player by 10
 *
 * @author Veronika
 */
public class Milk extends Item {

    /**
     * Creates milk
     *
     * @param name "Milk"
     */
    public Milk(String name) {
        super(name);
    }

    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.setHealth(hero.getHealth() + 10);
    }

    @Override
    public void UnUseItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.setHealth(hero.getHealth() - 10);
    }

}
