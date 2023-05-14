package cz.cvut.anokhver.playerStatsView;

import javafx.scene.image.Image;

import java.util.HashMap;

public abstract class  StatsView {
    private final HashMap<String,Image> textures = new HashMap<>();
    private Image curTexture;

    public Image getCurTexture() {
        return curTexture;
    }

    public void setCurTexture(Image cur_texture) {
        this.curTexture = cur_texture;
    }

    public HashMap<String, Image> getTextures() {
        return textures;
    }

    public void setCurTextureByInd(Integer cur_state) {
        this.curTexture = textures.get("anim" + String.valueOf(cur_state));
    }

}
