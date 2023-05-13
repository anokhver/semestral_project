package cz.cvut.anokhver.level;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Movable;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

public class LevelView extends Scene {

    public HashMap<String, Canvas> cur_canvases = new HashMap<>();

    public LevelView() {
        super(new Pane(), Configuration.getWindowWidth(), Configuration.getWindowHeight());
        Pane pane = (Pane) this.getRoot();

        cur_canvases.put("background", new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight()));
        cur_canvases.put("player", new Canvas(Configuration.getMapWidth()*Configuration.getTileSize(),
                Configuration.getMapHeight()*Configuration.getTileSize()));
        cur_canvases.put("map", new Canvas(Configuration.getMapWidth()*Configuration.getTileSize(),
               Configuration.getMapHeight()*Configuration.getTileSize()));
        cur_canvases.put("star", new Canvas(Configuration.getMapWidth()*Configuration.getTileSize(),
                Configuration.getMapHeight()*Configuration.getTileSize()));
        cur_canvases.put("enemies", new Canvas(Configuration.getMapWidth()*Configuration.getTileSize(),
                Configuration.getMapHeight()*Configuration.getTileSize()));

        fillWithBlack(cur_canvases.get("background"));
        pane.getChildren().addAll(cur_canvases.get("background"),cur_canvases.get("map"), cur_canvases.get("player"), cur_canvases.get("star"),cur_canvases.get("enemies"));

        pane.getChildren().get(0).setOpacity(1.0);
        pane.getChildren().get(1).setOpacity(1.0);
        pane.getChildren().get(2).setOpacity(1.0);
        pane.getChildren().get(3).setOpacity(1.0);
        pane.getChildren().get(4).setOpacity(1.0);



    }

    public void draw_all(Tilemap map, Player hero, List<Star> stars){
        drawTileMap(map);
        drawCreature(hero, cur_canvases.get("player").getGraphicsContext2D());
        drawStar(stars);
    }

    public void fillWithBlack(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawStar(List<Star> stars) {
        GraphicsContext gc = cur_canvases.get("star").getGraphicsContext2D();

        for(Star st : stars)
        {
            st.render(gc);
        }
    }

    public void drawEnemies(List<Enemy> enemies) {
        GraphicsContext gc = cur_canvases.get("enemies").getGraphicsContext2D();

        for(Enemy enemy : enemies)
        {
            drawCreature(enemy, cur_canvases.get("enemies").getGraphicsContext2D());
        }
    }


    public void drawTileMap(Tilemap map) {
        System.out.println("Started rendered" + String.valueOf(map.getWidth()) + " " + String.valueOf(map.getHeight()));

        GraphicsContext gc = cur_canvases.get("map").getGraphicsContext2D();
        for (int i = 0; i < map.getWidth(); i++) {

            for (int j = 0; j < map.getHeight(); j++) {
                SingleTile tile = map.getTile(i, j);
                tile.render(gc);

            }
        }
    }
    public void updateCamera(double playerX, double playerY) {
        double offsetX = (Configuration.getWindowWidth() >> 1) - playerX;
        double offsetY = (Configuration.getWindowHeight() >> 1) - playerY;
        this.getRoot().setTranslateX(offsetX);
        this.getRoot().setTranslateY(offsetY);

        cur_canvases.get("background").setLayoutX(-offsetX);
        cur_canvases.get("background").setLayoutY(-offsetY);

    }
    public void drawCreature(Movable enetety,GraphicsContext gc )
    {
        gc.drawImage(enetety.getTexture(), enetety.getPosition().getX(), enetety.getPosition().getY());
    }

    public void clearCanvas(GraphicsContext gc){
        gc.clearRect(0, 0, Configuration.getMapWidth()*Configuration.getTileSize(), Configuration.getMapHeight()*Configuration.getTileSize());
    }

}
