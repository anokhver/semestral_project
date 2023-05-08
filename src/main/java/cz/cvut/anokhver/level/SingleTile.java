package cz.cvut.anokhver.level;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

public class SingleTile{
    private final Image image;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public SingleTile(Image image, int x, int y) {
        Configuration.init("config.json");

        this.image = image;
        this.x = x;
        this.y = y;
        this.width = Configuration.getTileSize();
        this.height = Configuration.getTileSize();;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, width*x, y*height, width, height);
    }
    public static Image loadImageForTile(String tileChar) {
        Configuration.init("config.json");

        String dir = create_proper_path( Configuration.getPathTile() + tileChar + ".png");
        //System.out.println(dir);

        File file = new File(dir);
        return new Image(file.toURI().toString());
    }

}


