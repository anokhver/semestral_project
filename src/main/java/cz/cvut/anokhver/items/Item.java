package cz.cvut.anokhver.items;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;

import java.awt.*;

public class Item {

    private final String name;
    private final Integer id;
    private final Image texture;
    public boolean pickUp(Player player)
    {
        return player.getInventory().addItem(this);
    }

    public boolean drop(Enemy enemy)
    {
        return enemy.setDropChance(this);
    }

    public Item(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.texture = null;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
