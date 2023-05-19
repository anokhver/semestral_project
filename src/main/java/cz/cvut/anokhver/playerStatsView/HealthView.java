package cz.cvut.anokhver.playerStatsView;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.image.Image;

import static cz.cvut.anokhver.additional.FileManagement.getFileFromResourceAsStream;

/**
 * Representation of health view of player
 *
 * @author Veronika
 */
public class HealthView extends StatsView {

    /**
     * Creating health view
     */
    public HealthView() {
        //loading all textures needed for animation
        for (int i = 0; i <= 3; i++) {
            this.getTextures().put("anim" + i, new Image(getFileFromResourceAsStream(Configuration.getPathHeart() + i + ".png"), Configuration.getTileSize(), Configuration.getTileSize(), false, false));
        }
        setCurTextureByInd(0);
    }

    /**
     * Checking if i need to change the texture of heart
     *
     * @param count count of health
     */
    public void checkForChange(Float count) {
        if (count <= 60) setCurTextureByInd(1);
        if (count <= 40) setCurTextureByInd(2);
        if (count <= 20) setCurTextureByInd(3);
    }

}
