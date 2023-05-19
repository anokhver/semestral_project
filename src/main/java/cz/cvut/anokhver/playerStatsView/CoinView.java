package cz.cvut.anokhver.playerStatsView;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.image.Image;

import java.io.InputStream;

import static cz.cvut.anokhver.additional.FileManagement.getFileFromResourceAsStream;

public class CoinView extends StatsView {

    /**
     * Creating Coin view
     */
    public CoinView() {

        //setting all textures for animation
        for (int i = 0; i <= 0; i++) {
            InputStream stream = getFileFromResourceAsStream(Configuration.getPathCoin() + i + ".png");
            this.getTextures().put("anim" + i, new Image(stream, Configuration.getTileSize(), Configuration.getTileSize(), false, false));
        }

        setCurTextureByInd(0);
    }

}
