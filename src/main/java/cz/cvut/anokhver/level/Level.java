package cz.cvut.anokhver.level;

import cz.cvut.anokhver.Configuration;
import static cz.cvut.anokhver.FileManagement.create_proper_path;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Star;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
        System.out.println("Staeted rendered" + String.valueOf(map.getWidth()) + " " + String.valueOf(map.getHeight()));

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
