package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

public abstract class Item {

    private final String name;
    private final Integer id;

    private Image texture;

    public boolean pickUp(Player player)
    {
        return player.getInventory().addItem(this);
    }
    public abstract void useItem(Player hero);
    public boolean drop(Enemy enemy)
    {
        return enemy.setDropChance(this);
    }

    public Item(String name, Integer id) {
        this.name = name;
        this.id = id;
        texture = null;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Image getTexture() {
        return texture;
    }

    protected void setTexture(Image texture)
    {
        this.texture = texture;
    }

}
