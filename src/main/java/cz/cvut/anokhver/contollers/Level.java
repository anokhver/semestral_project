package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.additional.Configuration;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.level.SingleTile;
import cz.cvut.anokhver.level.Tilemap;
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
