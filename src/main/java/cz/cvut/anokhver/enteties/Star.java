package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.movement.Coordinates;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

import static cz.cvut.anokhver.additional.FileManagement.createProperPath;

/**
 * Star object
 * is needed to be collected to win the game
 *
 * @author Veronika
 */
public class Star {

    private final HashMap<String, Image> textures = new HashMap<>();
    private Coordinates position;
    private int anim_ind = 1;

    /**
     * Create star on position
     *
     * @param position coor
     */
    public Star(Coordinates position) {

        for (int i = 0; i < 3; i++) {
            String dir = createProperPath(Configuration.getPathStar()) + i + ".png";
            textures.put("anim" + i, new Image("file:" + File.separator + dir, Configuration.getTileSize(), Configuration.getTileSize(), false, false));
        }

        this.position = position;
    }

    /**
     * Draws star texture on its coor on graphic context
     *
     * @param gc graphic context
     */
    public void render(GraphicsContext gc) {

        gc.drawImage(textures.get("anim" + anim_ind),
                position.getX(),
                position.getY(),
                textures.get("anim1").getWidth(), textures.get("anim1").getHeight());

        if (anim_ind == textures.size() - 1) anim_ind = 0;
        else anim_ind += 1;
    }

    /*===========================
     *Getters & Setters
     ===========================*/
    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public int getAnim_ind() {
        return anim_ind;
    }

    public void setAnim_ind(int anim_ind) {
        this.anim_ind = anim_ind;
    }
}


