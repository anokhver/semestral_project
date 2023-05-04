package cz.cvut.anokhver.additional;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {

    private static JSONObject json_config;

    public static void init(String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            json_config = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getWindowWidth() {
        return json_config.getInt("windowWidth");
    }

    public static int getWindowHeight() {
        return json_config.getInt("windowHeight");
    }

    public static String getWindowName() {
        return json_config.getString("windowName");
    }


    public static int getTileSize() {
        return json_config.getInt("tileSize");
    }

    public static String getPathIcon() {
        return json_config.getString("pathIcon");
    }
    public static String getPathTile() {return json_config.getString("pathTile");}

    public static String getPathPlayer(){return json_config.getString("pathPlayerTexture");}

    public static String getPathLevel() {
        return json_config.getString("pathLevel");
    }

    public static int getMapWidth(){
        return json_config.getInt("mapWidth");
    }

    public static int getMapHeight(){
        return json_config.getInt("mapHeight");
    }
}
