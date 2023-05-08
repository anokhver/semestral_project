package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.menu.Inventory;
import cz.cvut.anokhver.movement.Coordinates;
import javafx.scene.image.Image;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;
import static cz.cvut.anokhver.movement.Coordinates.minus;

public class Player extends Movable{
    private static Logger log = Logger.getLogger(Player.class.getName());

    private float damage;
    private float health;
    private float speed_damage;
    private Integer coins = 0;
    private boolean live = true;
    private final Inventory inventory;
    private int star_counter = 0;

    public Player(float damage, float walkSpeed, float health, float speedDamage, Inventory inventory) {
        this.damage = damage;
        this.health = health;
        speed_damage = speedDamage;
        this.inventory = inventory;

        setWalk_speed(walkSpeed);
        setTexture(new Image("file:" + File.separator + create_proper_path(Configuration.getPathPlayer()), Configuration.getPlayerWidth(), Configuration.getPlayerHeight(), false, true));

    }

    public Player() {
        log.info("Creating default player...");
        this.damage = 20;
        this.health = 100;
        speed_damage = 10;
        this.inventory = new Inventory();

        setWalk_speed(120);
        setTexture(new Image("file:" + File.separator + create_proper_path(Configuration.getPathPlayer()), Configuration.getPlayerWidth(), Configuration.getPlayerHeight(), false, false));

    }

    public int checkForStars(List<Star> stars){
        int playerCenterX = (int) (getPosition().getX() + Configuration.getPlayerWidth() / 2.0);
        int playerCenterY = (int) (getPosition().getY() + Configuration.getPlayerHeight() / 2.0);

        for (int i = 0; i < stars.size(); i++) {
            Star star = stars.get(i);
            int starCenterX = (int) (star.getPosition().getX() + Configuration.getTileSize() / 2.0);
            int starCenterY = (int) (star.getPosition().getY() + Configuration.getTileSize() / 2.0);

            double distance = minus(new Coordinates(playerCenterX, playerCenterY), new Coordinates(starCenterX, starCenterY));
            if (distance <= Configuration.getPickUp()) {
                return  i;
            }
        }
        return -1;
    }

    public int getStar_counter() {
        return star_counter;
    }

    public void setStar_counter(int star_counter) {
        this.star_counter = star_counter;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getSpeed_damage() {
        return speed_damage;
    }

    public void setSpeed_damage(float speed_damage) {
        this.speed_damage = speed_damage;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }




}

