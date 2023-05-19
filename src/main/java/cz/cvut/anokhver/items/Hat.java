package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Player;

/**
 * Hat item increases player damage radius by 2 tiles
 *
 * @author Veronika
 */
public class Hat extends Item {

    /**
     * Creates hat
     *
     * @param name "hat"
     */
    public Hat(String name) {
        super(name);
    }

    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.getInventory().setYourHat(this);
        hero.setDamage_radius(hero.getDamage_radius() + 2);
    }

    @Override
    public void UnUseItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.setDamage_radius(hero.getDamage_radius() - 2);
    }


}
