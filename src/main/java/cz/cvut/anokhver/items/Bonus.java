package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Player;

/**
 * Bonus item increases players walk speed by 42
 *
 * @author Veronika
 */
public class Bonus extends Item {

    /**
     * Creating bonus
     *
     * @param name "Bonus"
     */
    public Bonus(String name) {
        super(name);
    }

    @Override
    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.getInventory().setYourBonus(this);
        hero.setWalk_speed(hero.getWalk_speed() + 42);
    }

    @Override
    public void UnUseItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.setWalk_speed(hero.getWalk_speed() - 42);
    }
}
