package cz.cvut.anokhver.playerStatsView;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.image.Image;

import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

public class CoinView extends StatsView{

    public CoinView(){

        for(int i = 0; i <= 0; i++) {
            String dir = create_proper_path(Configuration.getPathCoin()) + String.valueOf(i) + ".png";
            this.getTextures().put("anim" + String.valueOf(i), new Image("file:" + File.separator + dir, Configuration.getTileSize(), Configuration.getTileSize(), false, false));
        }

        setCurTextureByInd(0);
    }

}
