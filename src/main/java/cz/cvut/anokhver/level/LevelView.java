package cz.cvut.anokhver.level;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Movable;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.playerStatsView.CoinView;
import cz.cvut.anokhver.playerStatsView.HealthView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.List;

/**
 * Implements a view for any level
 * draws all entities/stats/background/etc...
 *
 * @author Veronika
 */
public class LevelView extends Scene {

    private final HealthView healthView = new HealthView();
    private final CoinView coinView = new CoinView();
    public HashMap<String, Canvas> cur_canvases = new HashMap<>();

    /**
     * Creating needed canvases and sorting them in right order
     */
    public LevelView() {
        super(new Pane(), Configuration.getWindowWidth(), Configuration.getWindowHeight());
        Pane pane = (Pane) this.getRoot();

        cur_canvases.put("background", new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight()));
        cur_canvases.put("heroStats", new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight()));

        fillWithBlack(cur_canvases.get("background"));

        String[] canvasKeys = {"map", "player", "star", "enemies"};

        for (String key : canvasKeys) {
            cur_canvases.put(key, new Canvas(Configuration.getMapWidth() * Configuration.getTileSize(), Configuration.getMapHeight() * Configuration.getTileSize()));
        }

        pane.getChildren().setAll(cur_canvases.get("background"), cur_canvases.get("map"), cur_canvases.get("player"), cur_canvases.get("star"), cur_canvases.get("enemies"), cur_canvases.get("heroStats"));

        for (Node node : pane.getChildren()) {
            node.setOpacity(1.0);
        }
    }

    /*===========================
    *Drawing & Cleaning
    ===========================*/

    /**
     * Draw the level
     *
     * @param map
     * @param stars all stars left
     * @param time  left till the end
     */

    public void draw_all(Tilemap map, List<Star> stars, Integer time) {
        Player hero = GameLogic.getPlayer();
        drawTileMap(map);
        drawCreature(hero, cur_canvases.get("player").getGraphicsContext2D());
        drawStar(stars);
        drawStats(time);
    }

    private void fillWithBlack(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Draws all stars
     *
     * @param stars
     */
    public void drawStar(List<Star> stars) {
        GraphicsContext gc = cur_canvases.get("star").getGraphicsContext2D();

        for (Star st : stars) {
            st.render(gc);
        }
    }

    /**
     * Draws all enemies
     *
     * @param enemies
     */
    public void drawEnemies(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            drawCreature(enemy, cur_canvases.get("enemies").getGraphicsContext2D());
        }
    }

    /**
     * Draws stats of player
     *
     * @param time left till the
     */
    public void drawStats(Integer time) {
        Player hero = GameLogic.getPlayer();
        GraphicsContext gc = cur_canvases.get("heroStats").getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 14));

        //draw heart
        gc.drawImage(healthView.getCurTexture(), 0, 0);
        gc.fillText(String.valueOf(hero.getHealth()), Configuration.getTileSize() + 5, 15);

        //draw coins
        gc.drawImage(coinView.getCurTexture(), 0, Configuration.getTileSize());
        gc.fillText(String.valueOf(hero.getCoins()), Configuration.getTileSize() + 5, Configuration.getTileSize() + 15);

        //draw time
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 30));
        gc.fillText("Time left : " + time, Configuration.getWindowWidth() >> 1, 30);

        //draw stars in player
        Coordinates drawing_coor = new Coordinates(0, 0);
        Star just_for_texture = new Star(drawing_coor);
        for (int i = 0; i < hero.getStar_counter(); i++) {
            drawing_coor = new Coordinates(Configuration.getWindowWidth() - Configuration.getTileSize() * (3 - i), 0);
            just_for_texture.setPosition(drawing_coor);
            just_for_texture.setAnim_ind(0);
            just_for_texture.render(gc);
        }
    }

    private void drawTileMap(Tilemap map) {
        System.out.println("Started rendered" + map.getWidth() + " " + map.getHeight());

        GraphicsContext gc = cur_canvases.get("map").getGraphicsContext2D();
        for (int i = 0; i < map.getWidth(); i++) {

            for (int j = 0; j < map.getHeight(); j++) {
                SingleTile tile = map.getTile(i, j);
                tile.render(gc);

            }
        }
    }

    /**
     * Centers the camera to the player
     *
     * @param playerX coor
     * @param playerY coor
     */
    public void updateCamera(double playerX, double playerY) {
        double offsetX = (Configuration.getWindowWidth() >> 1) - playerX;
        double offsetY = (Configuration.getWindowHeight() >> 1) - playerY;
        this.getRoot().setTranslateX(offsetX);
        this.getRoot().setTranslateY(offsetY);

        cur_canvases.get("background").setLayoutX(-offsetX);
        cur_canvases.get("background").setLayoutY(-offsetY);

        cur_canvases.get("heroStats").setLayoutX(-offsetX);
        cur_canvases.get("heroStats").setLayoutY(-offsetY);

    }

    /**
     * Draw the some entity
     *
     * @param entity
     * @param gc     where it will be drawn
     */
    public void drawCreature(Movable entity, GraphicsContext gc) {
        gc.drawImage(entity.getTexture(), entity.getPosition().getX(), entity.getPosition().getY());
    }

    /**
     * Clear the graphic context
     *
     * @param gc
     */
    public void clearCanvas(GraphicsContext gc) {
        gc.clearRect(0, 0, Configuration.getMapWidth() * Configuration.getTileSize(), Configuration.getMapHeight() * Configuration.getTileSize());
    }


    /*===========================
     *Getters & Setters
     ===========================*/
    public HashMap<String, Canvas> getCur_canvases() {
        return cur_canvases;
    }

    public void setCur_canvases(HashMap<String, Canvas> cur_canvases) {
        this.cur_canvases = cur_canvases;
    }

    public HealthView getHealthView() {
        return healthView;
    }

    public CoinView getCoinView() {
        return coinView;
    }
}
