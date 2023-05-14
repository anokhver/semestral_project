package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.additional.Configuration;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

import cz.cvut.anokhver.movement.Coordinates;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class Star {

    private Coordinates position;

    private int anim_ind = 1;
    private boolean pickUp;
    private final HashMap<String,Image> textures = new HashMap<>();

    public Star(Coordinates position) {

        for(int i = 0; i < 3; i++) {
            String dir = create_proper_path(Configuration.getPathStar()) + i + ".png";
            textures.put("anim" + i, new Image("file:" + File.separator + dir, Configuration.getTileSize(), Configuration.getTileSize(), false, false));
        }

        this.position = position;
        this.pickUp = false;

    }

    public void render(GraphicsContext gc) {

        gc.drawImage(textures.get("anim" + anim_ind),
                position.getX(),
                position.getY(),
                textures.get("anim1").getWidth(), textures.get("anim1").getHeight());

        if(anim_ind == textures.size() - 1) anim_ind = 0;
        else anim_ind += 1;
    }


    public Coordinates getPosition() {
        return position;
    }


    public boolean isPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
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


