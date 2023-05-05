package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.level.SingleTile;
import cz.cvut.anokhver.level.Tilemap;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import java.util.HashMap;

public class LevelView extends Scene {

    HashMap<String, Canvas> cur_canvases = new HashMap<>();

    public LevelView() {
        super(new Pane(), Configuration.getWindowWidth(), Configuration.getWindowHeight());

        cur_canvases.put("player", new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight()));
        cur_canvases.put("map", new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight()));

        Pane pane = (Pane) this.getRoot();
        pane.getChildren().addAll(cur_canvases.get("map"), cur_canvases.get("player"));

        pane.getChildren().get(0).setOpacity(1.0);
        pane.getChildren().get(1).setOpacity(1.0);

        //how to set this scene from pane
    }

    public void draw_all(Tilemap map, Player hero){
        drawTileMap(map);
        drawPlayer(hero);

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

    public void drawPlayer(Player hero)
    {
        Configuration.init("config.json");
        GraphicsContext gc = cur_canvases.get("player").getGraphicsContext2D();
        gc.drawImage(hero.getTexture(), hero.getPosition().getX(), hero.getPosition().getY(), Configuration.getTileSize(), Configuration.getTileSize());

    }

    public void clearPlayer(){
        GraphicsContext gc = cur_canvases.get("player").getGraphicsContext2D();
        gc.clearRect(0, 0, Configuration.getWindowWidth(), Configuration.getWindowHeight());

    }
}
