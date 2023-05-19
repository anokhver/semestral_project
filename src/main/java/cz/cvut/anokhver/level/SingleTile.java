package cz.cvut.anokhver.level;

import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.anokhver.additional.FileManagement.getFileFromResourceAsStream;

/**
 * Implements single tile for the map
 *
 * @author Veronika
 */
public class SingleTile {
    private final Image image;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    /**
     * Creates one tile
     *
     * @param image texture of tile
     * @param x     coor
     * @param y     coor
     */
    public SingleTile(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = Configuration.getTileSize();
        this.height = Configuration.getTileSize();
    }

    public static Image loadImageForTile(String tileChar) {
        return new Image(getFileFromResourceAsStream(Configuration.getPathTile() + tileChar + ".png"));
    }

    /**
     * Drawing tile
     *
     * @param gc graphicContext where it will be drown
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(image, width * x, y * height, width, height);
    }

}


