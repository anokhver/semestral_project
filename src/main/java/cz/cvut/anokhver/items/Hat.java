package cz.cvut.anokhver.items;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.FileManagement;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

import java.io.File;

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
        Image img = new Image("file:" + File.separator + FileManagement.createProperPath(Configuration.getPathItem() + name + ".png"));
        this.setTexture(img);
    }

    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.getInventory().setYourHat(this);
        hero.setDamage_radius(hero.getDamage_radius() + 2);
    }
}
