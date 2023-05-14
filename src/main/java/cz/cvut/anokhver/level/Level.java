package cz.cvut.anokhver.level;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.additional.Configuration;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;
import static cz.cvut.anokhver.movement.Coordinates.minus;

import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.movement.Coordinates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Level {

    private final Integer id;
    private final List<Enemy> enemies;
    private List<Star> stars;

    private Tilemap map;
    private Integer enemyCount = 1;

    public Level(Integer id) {
        GameLauncher.log.info("Generating level...");
        this.id = id;
        String dir = create_proper_path(Configuration.getPathLevel() + id.toString());
        configureMap(dir);

        this.map = new Tilemap(Configuration.getMapWidth(), Configuration.getMapHeight(), id);
        map.readMap(dir);

        stars = generateStars();
        enemies = generateEnemies();
    }

    public List<Enemy> generateEnemies() {
        GameLauncher.log.info("Generating enemies on the level...");
        Coordinates border = new Coordinates((map.getWidth() - 2)* Configuration.getTileSize(), (map.getHeight() - 2)* Configuration.getTileSize());
        List<String> configFiles = Arrays.asList("frog", "spider", "moth");
        int minDistance = Configuration.getMinimalDistStars();

        List<Enemy> enemies = new ArrayList<>();
        Random random = new Random();

        // Generate enemies while the list is not full
        while (enemies.size() < enemyCount) {
            Coordinates new_coor = new Coordinates(random.nextInt(border.getX()), random.nextInt(border.getY()));
            boolean tooClose = false;

            // Check if the enemy is too close to any other enemy
            for (Enemy enemy : enemies) {
                double dist = minus(enemy.getPosition(), new_coor);
                if (dist < minDistance) {
                    tooClose = true;
                    break;
                }
            }
            // If not too close, add the enemy to the list
            if (!tooClose) {
                // Load enemy configurations from JSON files
                Enemy enemy = new Enemy(configFiles.get(random.nextInt(configFiles.size())), new_coor);
                enemies.add(enemy);
            }

        }

        return enemies;
    }

    public List<Star> generateStars() {
        GameLauncher.log.info("Generating stars on the level...");
        //default configurations
        Coordinates border = new Coordinates((map.getWidth() - 1)* Configuration.getTileSize(), (map.getHeight() - 1)* Configuration.getTileSize());
        int count = Configuration.getCountStars();
        int minDistance = Configuration.getMinimalDistStars();

        //--------
        List<Star> stars = new ArrayList<>();
        Random random = new Random();

        //generating stars while the list is not full
        while (stars.size() < count) {
            Coordinates new_coor = new Coordinates(random.nextInt(border.getX()), random.nextInt(border.getY()));
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

    public void configureMap(String dir) {
        try {
            GameLauncher.log.info("Configuring map...");
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
            }

            Configuration.setMapWidth(mapWidth);
            Configuration.setMapHeight(mapHeight);

        } catch (IOException e) {
            GameLauncher.log.info("Failed to configure map: " + e.getMessage());
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

}

