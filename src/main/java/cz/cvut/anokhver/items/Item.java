package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

public abstract class Item {

    private final String name;

    private Image texture;

    public Item(String name) {
        this.name = name;
        texture = null;
    }

    /*===========================
    *Basic interactions
    ===========================*/
    public void pickUp(Player player)
    {
        player.getInventory().addItem(this);
    }
    public abstract void useItem(Player hero);
    public boolean drop(Enemy enemy)
    {
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

    protected void setTexture(Image texture)
    {
        this.texture = texture;
    }

}
