package cz.cvut.anokhver.level;


import cz.cvut.anokhver.GameLauncher;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static cz.cvut.anokhver.level.SingleTile.loadImageForTile;

/**
 * Represents a map by two dementional array of tiles
 *
 * @author Veronika
 */
public class Tilemap {
    private final SingleTile[][] tiles;
    private int width;
    private int height;

    /**
     * Allocates the array for map
     *
     * @param width  of array
     * @param height of array
     */
    public Tilemap(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        tiles = new SingleTile[width][height];
    }

    /*===========================
    *Set tile & get tile
    ===========================*/

    public void setTile(Integer x, Integer y, SingleTile tile) {
        tiles[x][y] = tile;
    }

    public SingleTile getTile(Integer x, Integer y) {
        return tiles[x][y];
    }

    /*===========================
    *Read from file
    ===========================*/

    /**
     * Reading map from the file
     *
     * @param path to file
     */

    public void readMap(String path) {
        GameLauncher.log.info("Reading map from:" + path);
        try {
            File mapFile = new File(path);
            Scanner fileSc = new Scanner(mapFile);
            int x = 0;
            int y = 0;
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                for (String s : line.split("")) {
                    // Create a new Tile object based on the character read from the file
                    // and add it to the Tilemap
                    Image image = loadImageForTile(s);
                    SingleTile tile = new SingleTile(image, x, y);
                    this.setTile(x, y, tile);
                    x++;
                }
                y++;
                x = 0;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /*===========================
    *Getters & setters
    ===========================*/
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // other methods as needed
}