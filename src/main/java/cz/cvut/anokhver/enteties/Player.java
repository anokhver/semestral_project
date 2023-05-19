package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.additional.PlayerConfigurations;
import cz.cvut.anokhver.contollers.InventoryController;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;

import static cz.cvut.anokhver.additional.FileManagement.createProperPath;

/**
 * The player instance
 * user controls it, fights monsters etc...
 *
 * @author Veronika
 */
public class Player extends Movable {

    private float damage;
    private float health;
    private float speed_damage;
    private double damage_radius;
    private Integer coins = 0;

    private InventoryController inventory;
    private int star_counter = 0;

    /**
     * Create player from given parameters
     * usually used for loading from saves
     *
     * @param damage
     * @param walkSpeed
     * @param health
     * @param speedDamage
     * @param damageRadius
     */
    public Player(float damage, float walkSpeed, float health, float speedDamage, double damageRadius) {
        PlayerConfigurations.init(createProperPath("con_player.json"));

        this.damage = damage;
        this.health = health;
        this.speed_damage = speedDamage;
        this.damage_radius = damageRadius;
        this.setWalk_speed(walkSpeed);

        loadAllTextures(PlayerConfigurations.getTexture(),PlayerConfigurations.getTextureWidth(), PlayerConfigurations.getTextureHeight());
        setCurTextureDirection(Direction.STOP);
        this.setInventory(new InventoryController());
    }

    /**
     * Create player from regular player configuration
     */
    public Player() {
        GameLauncher.log.info("Creating default player...");
        PlayerConfigurations.init("con_player.json");
        this.damage = PlayerConfigurations.getDamage();
        this.health = PlayerConfigurations.getHealth();
        this.speed_damage = PlayerConfigurations.getSpeedDamage();
        this.damage_radius = PlayerConfigurations.getDamageRadius();

        setWalk_speed(PlayerConfigurations.getWalkSpeed());
        loadAllTextures(PlayerConfigurations.getTexture(),PlayerConfigurations.getTextureWidth(), PlayerConfigurations.getTextureHeight());
        setCurTextureDirection(Direction.STOP);
        this.coins = PlayerConfigurations.getCoins();
        this.setPosition(new Coordinates(100, 100));
        this.setInventory(new InventoryController());

    }

    /*===========================
   *Getters & Setters
   ===========================*/
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

    public InventoryController getInventory() {
        return inventory;
    }

    public void setInventory(InventoryController inventory) {
        this.inventory = inventory;
    }

    public double getDamage_radius() {
        return damage_radius;
    }

    public void setDamage_radius(double damage_radius) {
        this.damage_radius = damage_radius;
    }

}

