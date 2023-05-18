package cz.cvut.anokhver.items;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.FileManagement;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

import java.io.File;

public class Collar extends Item{
    @Override
    public void useItem(Player hero) {
        hero.getInventory().removeItem(this);
        hero.getInventory().setYourCollar(this);
        hero.setDamage(hero.getDamage() + 2);
    }

    public Collar(String name) {
        super(name);
        Image img = new Image("file:" + File.separator + FileManagement.createProperPath(Configuration.getPathItem()+ name + ".png"));
        this.setTexture(img);
    }
}
