package cz.cvut.anokhver.enteties;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.menu.Inventory;
import cz.cvut.anokhver.movement.Direction;
import javafx.scene.image.Image;

import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

public class Player{
    private float damage;
    private float walk_speed;
    private float health;
    private float speed_damage;
    private Integer coins = 0;
    private boolean live = true;
    private final Inventory inventory;
    private Coordinates position;

    private final Image texture;


    public Player(float damage, float walkSpeed, float health, float speedDamage, Inventory inventory) {
        Configuration.init(create_proper_path("config.json"));


        this.damage = damage;
        walk_speed = walkSpeed;
        this.health = health;
        speed_damage = speedDamage;
        this.inventory = inventory;

        this.texture = new Image("file:" + File.separator + create_proper_path(Configuration.getPathPlayer()));

    }

    public Player() {
        Configuration.init(create_proper_path("config.json"));

        this.damage = 20;
        walk_speed = 5;
        this.health = 100;
        speed_damage = 10;
        this.inventory = new Inventory();

        this.texture = new Image("file:" + File.separator + create_proper_path(Configuration.getPathPlayer()));

    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getWalk_speed() {
        return walk_speed;
    }

    public void setWalk_speed(float walk_speed) {
        this.walk_speed = walk_speed;
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

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Image getTexture() {
        return texture;
    }
    public void move(Direction direction) {
        Configuration.init(create_proper_path("config.json"));

        int x = position.getX();
        int y = position.getY();
        float speed = walk_speed;

        // Update the player's position based on the given direction
        switch (direction) {
            case TOP:
                y -= speed;
                break;
            case BOTTOM:
                y += speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            default:
                break;
        }

        // Update the player's position if it's within the game world boundaries
        if (x >= 0 && x <= Configuration.getMapWidth() - texture.getWidth() && y >= 0 && y <= Configuration.getMapHeight() - texture.getHeight()) {
            position = new Coordinates(x, y);
        }
        System.out.println(position);
        setPosition(position);
    }


}

