package cz.cvut.anokhver.additional;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EnemyConfigurations {

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

    public static String getName() {
        return json_config.getString("name");
    }

    public static String getTexture() {
        return json_config.getString("texturePath");
    }

    public static float getHealth(){return json_config.getFloat("health");}
    public static float getDamage(){return json_config.getFloat("damage");}
    public static float getSpeedDamage(){return json_config.getFloat("speedDamage");}
    public static float getDamageRadius(){return json_config.getFloat("damageRadius");}
    public static float getSeeRadius(){return json_config.getFloat("seeRadius");}
    public static float getWalkSpeed(){return json_config.getFloat("walkSpeed");}

    public static int getTextureWidth(){return json_config.getInt("textureWidth");}
    public static int getTextureHeight(){return json_config.getInt("textureHeight");}


}
