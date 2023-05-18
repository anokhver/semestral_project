package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

/**
 * Implements the items that player can use on itself
 *
 * @author Veronika
 */
public abstract class Item {

    private final String name;

    private Image texture;

    /**
     * standard item constructor
     *
     * @param name "item name"
     */
    public Item(String name) {
        this.name = name;
        texture = null;
    }

    /*===========================
    *Basic interactions
    ===========================*/
    public void pickUp(Player player) {
        player.getInventory().addItem(this);
    }

    /**
     * Uses item on player
     *
     * @param hero player
     */
    public abstract void useItem(Player hero);

    public boolean drop(Enemy enemy) {
        return enemy.setDropChance(this);
    }

    /*===========================
    *Getters & Setters
    ===========================*/

    public String getName() {
        return name;
    }

    public Image getTexture() {
        return texture;
    }

    protected void setTexture(Image texture) {
        this.texture = texture;
    }

}
