package cz.cvut.anokhver.playerStatsView;

import javafx.scene.image.Image;

import java.util.HashMap;

/**
 * Abstract class for the representing view of some player stats
 *
 * @author Veronika
 */
public abstract class StatsView {

    private final HashMap<String, Image> textures = new HashMap<>();
    private Image curTexture;

    /*===================
    /Getters & Setters
    =====================*/
    public Image getCurTexture() {
        return curTexture;
    }

    public void setCurTexture(Image cur_texture) {
        this.curTexture = cur_texture;
    }

    public HashMap<String, Image> getTextures() {
        return textures;
    }

    /**
     * Setting current texture by index of animation
     * with which it is stored in textures HashMap
     *
     * @param cur_state the index of animation texture
     */
    public void setCurTextureByInd(Integer cur_state) {
        this.curTexture = textures.get("anim" + cur_state);
    }

}
