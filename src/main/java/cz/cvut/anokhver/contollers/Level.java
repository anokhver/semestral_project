package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.additional.Configuration;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;
import static cz.cvut.anokhver.movement.Coordinates.minus;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.level.Tilemap;
import cz.cvut.anokhver.movement.Coordinates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Level {

    private static Logger log = Logger.getLogger(Level.class.getName());

    private final Integer id;
    private List<Enemy> enemies;
    private List<Star> stars;

    private Tilemap map;

    public Level(Integer id) {
        log.info("Generating level...");
        this.id = id;
        String dir = create_proper_path(Configuration.getPathLevel() + id.toString());
        configuration_map(dir);

        this.map = new Tilemap(Configuration.getMapWidth(), Configuration.getMapHeight(), id);
        map.readMap(dir);

        stars = generateStars();
    }

    public List<Star> generateStars() {
        log.info("Generating stars on the level...");
        //default configurations
        Coordinates border = new Coordinates(map.getWidth(), map.getHeight());
        int count = Configuration.getCountStars();
        int minDistance = Configuration.getMinimalDistStars();

        //--------
        List<Star> stars = new ArrayList<>();
        Random random = new Random();

        //generating stars while the list is not full
        while (stars.size() < count) {
            Coordinates new_coor = new Coordinates(random.nextInt(border.getX())*Configuration.getTileSize(), random.nextInt(border.getY())*Configuration.getTileSize());
            boolean tooClose = false;

            //cheking if they are too close
            for (Star star : stars) {
                double dist = minus(star.getPosition(), new_coor);
                if (dist < minDistance) {
                    tooClose = true;
                    break;
                }
            }
            //if not add
            if (!tooClose) {
                stars.add(new Star(new_coor));
            }
        }

        return stars;
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

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public void configuration_map(String dir){
        log.info("Configuring map...");

        int mapWidth = 0;
        int mapHeight = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(dir))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Count the number of symbols in the line
                int lineLength = line.length();
                if (lineLength > mapWidth) {
                    mapWidth = lineLength;
                }
                mapHeight++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuration.setMapWidth(mapWidth);
        Configuration.setMapHeight(mapHeight);
    }
}
