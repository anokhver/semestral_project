package cz.cvut.anokhver.enteties;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.menu.Inventory;
import cz.cvut.anokhver.movement.Direction;
import javafx.scene.image.Image;

import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

public class Player extends Movable{
    private float damage;
    private float health;
    private float speed_damage;
    private Integer coins = 0;
    private boolean live = true;
    private final Inventory inventory;


    public Player(float damage, float walkSpeed, float health, float speedDamage, Inventory inventory) {
        Configuration.init(create_proper_path("config.json"));

        this.damage = damage;
        this.health = health;
        speed_damage = speedDamage;
        this.inventory = inventory;

        setWalk_speed(walkSpeed);
        setTexture(new Image("file:" + File.separator + create_proper_path(Configuration.getPathPlayer()), Configuration.getPlayerWidth(), Configuration.getPlayerHeight(), false, false));

    }

    public Player() {
        Configuration.init(create_proper_path("config.json"));

        this.damage = 20;
        this.health = 100;
        speed_damage = 10;
        this.inventory = new Inventory();

        setWalk_speed(120);
        setTexture(new Image("file:" + File.separator + create_proper_path(Configuration.getPathPlayer()), Configuration.getPlayerWidth(), Configuration.getPlayerHeight(), false, false));

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

