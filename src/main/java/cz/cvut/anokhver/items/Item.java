package cz.cvut.anokhver.items;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.enteties.Player;
import javafx.scene.image.Image;

import java.io.InputStream;

import static cz.cvut.anokhver.additional.FileManagement.getFileFromResourceAsStream;

/**
 * Implements the items that player can use on itself
 *
 * @author Veronika
 */
public abstract class Item {

    private final String name;

    private Image texture;

    private Configuration positionDroped;
    /**
     * standard item constructor
     *
     * @param name "item name"
     */
    public Item(String name) {
        this.name = name;
        System.out.println(Configuration.getPathItem() + name + ".png");
        InputStream stream = getFileFromResourceAsStream(Configuration.getPathItem() + name + ".png");
        texture = new Image(stream);
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
    public abstract void UnUseItem(Player hero);

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

    public Configuration getPositionDroped() {
        return positionDroped;
    }

    public void setPositionDroped(Configuration positionDroped) {
        this.positionDroped = positionDroped;
    }
}
