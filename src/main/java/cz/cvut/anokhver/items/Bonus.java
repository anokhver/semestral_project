package cz.cvut.anokhver.items;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.FileManagement;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

import java.io.File;

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
        Image img = new Image("file:" + File.separator + FileManagement.createProperPath(Configuration.getPathItem() + name + ".png"));
        this.setTexture(img);
    }

    @Override
    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.getInventory().setYourBonus(this);
        hero.setWalk_speed(hero.getWalk_speed() + 42);
    }
}
