package cz.cvut.anokhver.additional;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.contollers.InventoryController;
import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.items.Item;
import cz.cvut.anokhver.level.Level;
import cz.cvut.anokhver.movement.Coordinates;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SavingLoading {

    /**
     * Load level from Json file
     *
     * @param filename name of json file
     * @return level loaded
     */
    public static Level loadFromJsonLevel(String filename) {
        try {
            JSONObject levelJson = CreateJsonForRead(filename);
            if (levelJson == null) return null;
            // Extract level properties
            int id = levelJson.getInt("id");
            int enemyCount = levelJson.getInt("enemyCount");
            int elapsedSeconds = levelJson.getInt("elapsedSeconds");
            int totalTime = levelJson.getInt("totalTime");

            // Extract enemies
            JSONArray enemiesJson = levelJson.getJSONArray("enemies");
            List<Enemy> enemies = new ArrayList<>();
            for (int i = 0; i < enemiesJson.length(); i++) {
                JSONObject enemyJson = enemiesJson.getJSONObject(i);
                // Create Enemy instance and add it to the list

                String positionString = enemyJson.getString("position");
                String[] positionArray = positionString.split(" ");
                int x = Integer.parseInt(positionArray[0]);
                int y = Integer.parseInt(positionArray[1]);

                Enemy enemy = new Enemy(enemyJson.getString("name"),
                        enemyJson.getFloat("damage"),
                        enemyJson.getFloat("walkSpeed"),
                        enemyJson.getFloat("seeRadius"),
                        enemyJson.getDouble("damageRadius"),
                        enemyJson.getFloat("speedDamage"),
                        enemyJson.getFloat("health"),
                        new Coordinates(x, y));
                enemies.add(enemy);
            }

            // Extract stars
            JSONArray starsJson = levelJson.getJSONArray("stars");
            List<Star> stars = new ArrayList<>();
            for (int i = 0; i < starsJson.length(); i++) {
                JSONObject starJson = starsJson.getJSONObject(i);

                String positionString = starJson.getString("position");
                String[] positionArray = positionString.split(" ");
                int x = Integer.parseInt(positionArray[0]);
                int y = Integer.parseInt(positionArray[1]);

                // Create Star instance and add it to the list
                Star star = new Star(new Coordinates(x, y));
                // Set star properties
                stars.add(star);
            }

            // Create Level instance and set its properties
            Level level = new Level(id);
            level.setEnemyCount(enemyCount);
            level.setElapsedSeconds(elapsedSeconds);
            level.setTotalTime(totalTime);
            level.setEnemies(enemies);
            level.setStars(stars);

            return level;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null; // Return null if loading failed
    }

    /**
     * Save level to the json file
     *
     * @param filename where to save
     * @param level    what to save
     */
    public static void saveToJsonLevel(String filename, Level level) {
        JSONObject levelJson = new JSONObject();
        levelJson.put("id", level.getId());
        levelJson.put("enemyCount", level.getEnemyCount());
        levelJson.put("elapsedSeconds", level.getElapsedSeconds());
        levelJson.put("totalTime", level.getTotalTime());

        JSONArray enemiesJson = new JSONArray();
        for (Enemy enemy : level.getEnemies()) {
            JSONObject enemyJson = new JSONObject();
            enemyJson.put("name", enemy.getName());
            enemyJson.put("damageRadius", enemy.getDamageRadius());
            enemyJson.put("health", enemy.getHealth());
            enemyJson.put("seeRadius", enemy.getSeeRadius());
            enemyJson.put("speedDamage", enemy.getSpeedDamage());
            enemyJson.put("walkSpeed", enemy.getWalk_speed());
            enemyJson.put("damage", enemy.getDamage());

            enemyJson.put("position", enemy.getPosition().getX() + " " + enemy.getPosition().getY());

            enemiesJson.put(enemyJson);
        }
        levelJson.put("enemies", enemiesJson);

        JSONArray starsJson = new JSONArray();
        for (Star star : level.getStars()) {
            JSONObject starJson = new JSONObject();
            starJson.put("position", star.getPosition().getX() + " " + star.getPosition().getY());
            starsJson.put(starJson);
        }
        levelJson.put("stars", starsJson);

        FileWriter writer = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            writer = new FileWriter(file);
            writer.write(levelJson.toString(4)); // Use 4 spaces for indentation
            writer.flush();

            GameLauncher.log.log(java.util.logging.Level.INFO, "Level saved to {0}", filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Save player to Json file
     *
     * @param filename where to save
     * @param hero     what save
     */
    public static void saveToJsonPlayer(String filename, Player hero) {
        JSONObject playerJson = new JSONObject();
        playerJson.put("damage", hero.getDamage());
        playerJson.put("health", hero.getHealth());
        playerJson.put("speed_damage", hero.getSpeed_damage());
        playerJson.put("damage_radius", hero.getDamage_radius());
        playerJson.put("walk_speed", hero.getWalk_speed());
        playerJson.put("coins", hero.getCoins());
        playerJson.put("star_counter", hero.getStar_counter());
        playerJson.put("inventorySpace", hero.getInventory().getBackPackSpace());

        playerJson.put("Hat", (hero.getInventory().getYourHat() != null));
        playerJson.put("Collar", (hero.getInventory().getYourCollar() != null));
        playerJson.put("Bonus", (hero.getInventory().getYourBonus() != null));

        playerJson.put("position", hero.getPosition().getX() + " " + hero.getPosition().getY());
        playerJson.put("CollarInv", false);
        playerJson.put("HatInv", false);
        playerJson.put("BonusInv", false);

        Integer milkCount = 0;
        for (Item item : hero.getInventory().getBackPack()) {
            if (item != null) {
                if (Objects.equals(item.getName(), "Milk")) milkCount += 1;
                if (Objects.equals(item.getName(), "Collar")) playerJson.put("CollarInv", true);
                if (Objects.equals(item.getName(), "Hat")) playerJson.put("HatInv", true);
                if (Objects.equals(item.getName(), "Bonus")) playerJson.put("BonusInv", true);
            }
        }

        playerJson.put("Milk", milkCount);

        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(playerJson.toString(4)); // Use 4 spaces for indentation
                writer.flush();
            } // Use 4 spaces for indentation

            GameLauncher.log.log(java.util.logging.Level.INFO, "Player saved to {0}", filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load player from json file
     *
     * @param filename name of json file
     * @return player loaded
     */
    public static Player loadFromJsonPlayer(String filename) {
        try {
            JSONObject playerJson = CreateJsonForRead(filename);

            if (playerJson == null) return null;

            float damage = playerJson.getFloat("damage");
            float health = playerJson.getFloat("health");
            float speedDamage = playerJson.getFloat("speed_damage");
            float walkSpeed = playerJson.getFloat("walk_speed");
            double damageRadius = playerJson.getDouble("damage_radius");
            int coins = playerJson.getInt("coins");
            int starCounter = playerJson.getInt("star_counter");
            int inventorySpace = playerJson.getInt("inventorySpace");

            String positionString = playerJson.getString("position");
            String[] positionArray = positionString.split(" ");
            int x = Integer.parseInt(positionArray[0]);
            int y = Integer.parseInt(positionArray[1]);

            Player hero = new Player(damage, walkSpeed, health, speedDamage, damageRadius);
            hero.setCoins(coins);
            hero.setStar_counter(starCounter);

            boolean hasHat = playerJson.getBoolean("Hat");
            boolean hasCollar = playerJson.getBoolean("Collar");
            boolean hasBonus = playerJson.getBoolean("Bonus");

            boolean hasInvHat = playerJson.getBoolean("HatInv");
            boolean hasInvCollar = playerJson.getBoolean("CollarInv");
            boolean hasInvBonus = playerJson.getBoolean("BonusInv");

            int milkCount = playerJson.getInt("Milk");

            hero.setInventory(new InventoryController(hasHat, hasCollar, hasBonus, milkCount, inventorySpace,
                    hasInvHat, hasInvCollar, hasInvBonus));
            hero.setPosition(new Coordinates(x, y));

            return hero;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static JSONObject CreateJsonForRead(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }

        FileReader reader = new FileReader(file);
        StringBuilder jsonString = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            jsonString.append((char) character);
        }
        reader.close();

        return new JSONObject(jsonString.toString());
    }
}
