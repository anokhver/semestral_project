package cz.cvut.anokhver.playerStatsView;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.image.Image;

import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.createProperPath;

public class CoinView extends StatsView{

    public CoinView(){

        for(int i = 0; i <= 0; i++) {
            String dir = createProperPath(Configuration.getPathCoin()) + i + ".png";
            this.getTextures().put("anim" + i, new Image("file:" + File.separator + dir, Configuration.getTileSize(), Configuration.getTileSize(), false, false));
        }

        setCurTextureByInd(0);
    }

}
