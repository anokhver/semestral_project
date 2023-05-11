package cz.cvut.anokhver.playerStatsView;

import javafx.scene.image.Image;

import java.util.HashMap;

public abstract class  StatsView {
    private final HashMap<String,Image> textures = new HashMap<>();
    private Integer cur_state;
    private Image cur_texture;

    public HashMap<String, Image> getTextures() {
        return textures;
    }

    public Integer getCur_state() {
        return cur_state;
    }


    public void setCur_state(Integer cur_state) {
        this.cur_state = cur_state;
    }

}
