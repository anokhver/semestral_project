package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Player;

/**
 * Collar item increases player damage by 2
 *
 * @author Veronika
 */
public class Collar extends Item {
    public Collar(String name) {
        super(name);
    }

    @Override
    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.getInventory().setYourCollar(this);
        hero.setDamage(hero.getDamage() + 2);
    }

    @Override
    public void UnUseItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.setDamage(hero.getDamage() - 2);
    }
}
