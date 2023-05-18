package cz.cvut.anokhver.items;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.FileManagement;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

import java.io.File;

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
        Image img = new Image("file:" + File.separator + FileManagement.createProperPath(Configuration.getPathItem() + name + ".png"));
        this.setTexture(img);
    }

    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.setHealth(hero.getHealth() + 10);
    }

}
