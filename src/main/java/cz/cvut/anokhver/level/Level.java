package cz.cvut.anokhver.level;

import cz.cvut.anokhver.additional.Configuration;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;
import static cz.cvut.anokhver.level.SingleTile.loadImageForTile;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public class Level {
    private final Integer id;
    private List<Enemy> enemies;
    private List<Star> stars;

    private Tilemap map;

    public Level(Integer id) {
        Configuration.init("config.json");

        this.id = id;
        this.map = new Tilemap(Configuration.getMapWidth(), Configuration.getMapHeight(), id);
        String dir = create_proper_path(Configuration.getPathLevel() + id.toString());
        map.readMap(dir);
    }

    private void startLevel(){

    }

    public void drawTileMap(Canvas canvas) {
        System.out.println("Started rendered" + String.valueOf(map.getWidth()) + " " + String.valueOf(map.getHeight()));

        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < map.getWidth(); i++) {
            //System.out.println("i rendered " + String.valueOf(i));

            for (int j = 0; j < map.getHeight(); j++) {
                SingleTile tile = map.getTile(i, j);
                tile.render(gc);

                //System.out.println("i rendered JJJJJJ" + String.valueOf(j));
            }
        }
    }

    public void drawPlayer(Canvas canvas, Player hero)
    {
        Configuration.init("config.json");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        hero.setPosition(new Coordinates(100, 100)); // Set the initial position of the player
        gc.drawImage(hero.getTexture(), hero.getPosition().getX(), hero.getPosition().getY(), Configuration.getTileSize(), Configuration.getTileSize());

    }

    public Integer getId() {
        return id;
    }

    public Tilemap getMap() {
        return map;
    }

    public void setMap(Tilemap map) {
        this.map = map;
    }
}
