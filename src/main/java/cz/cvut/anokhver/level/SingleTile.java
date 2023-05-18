package cz.cvut.anokhver.level;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import static cz.cvut.anokhver.additional.FileManagement.createProperPath;

public class SingleTile{
    private final Image image;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public SingleTile(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = Configuration.getTileSize();
        this.height = Configuration.getTileSize();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, width*x, y*height, width, height);
    }
    public static Image loadImageForTile(String tileChar) {
        String dir = createProperPath( Configuration.getPathTile() + tileChar + ".png");
        File file = new File(dir);
        return new Image(file.toURI().toString());
    }

}


