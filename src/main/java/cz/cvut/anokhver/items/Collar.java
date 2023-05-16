package cz.cvut.anokhver.items;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.FileManagement;
import javafx.scene.image.Image;

import java.io.File;

public class Collar extends Item{
    public Collar(String name, Integer id) {
        super(name, id);
        Image img = new Image("file:" + File.separator + FileManagement.create_proper_path(Configuration.getPathItem()+ name + ".png"));
        this.setTexture(img);
    }
}
