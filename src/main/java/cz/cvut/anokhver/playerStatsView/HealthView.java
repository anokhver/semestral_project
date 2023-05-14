package cz.cvut.anokhver.playerStatsView;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.image.Image;

import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

public class HealthView extends StatsView{

    public HealthView()
    {

        for(int i = 0; i <= 3; i++) {
            String dir = create_proper_path(Configuration.getPathHeart()) + String.valueOf(i) + ".png";
            this.getTextures().put("anim" + String.valueOf(i), new Image("file:" + File.separator + dir, Configuration.getTileSize(), Configuration.getTileSize(), false, false));
        }

        setCurTextureByInd(0);
    }


    public void checkForChange(Float count)
    {
        if(count <= 60) setCurTextureByInd(1);
        if(count <= 40) setCurTextureByInd(2);
        if(count <= 20) setCurTextureByInd(3);
     }

}
