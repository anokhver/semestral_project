package cz.cvut.anokhver.items;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.FileManagement;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

import java.io.File;

public class Hat extends Item{
    public Hat(String name, Integer id) {
        super(name, id);
        Image img = new Image("file:" + File.separator + FileManagement.create_proper_path(Configuration.getPathItem()+ name + ".png"));
        this.setTexture(img);
    }

    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.getInventory().setYourHat(this);
        hero.setDamage_radius(hero.getDamage_radius() + 2);
    }
}
