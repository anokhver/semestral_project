package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.additional.EnemyConfigurations;
import cz.cvut.anokhver.items.Item;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

import javafx.scene.image.Image;
import java.io.File;
import java.util.Random;


public class Enemy extends Movable{

    private final String name;
    private float health;
    private float damage;
    private float speedDamage;
    private double damageRadius;
    private float seeRadius;
    public float cooldown = 0;
    private Item dropChance;
    private Direction curDirection = Direction.STOP;
    ///===================================//
    ///========= Constructors ============//
    ///===================================//
    public Enemy(String name, float damage, float walkSpeed, Item dropChance, float seeRadius, double damageRadius, Image img) {
        this.name = name;
        this.damage = damage;
        this.setWalk_speed(walkSpeed);
        this.dropChance = dropChance;
        this.seeRadius = seeRadius;
        this.damageRadius = damageRadius;

        this.setTexture(img);
    }


    public Enemy(String conName, Coordinates coordinates) {
        EnemyConfigurations.init(create_proper_path("con_" + conName + ".json"));
        this.name = EnemyConfigurations.getName();
        this.health = EnemyConfigurations.getHealth();
        this.damage = EnemyConfigurations.getDamage();
        this.speedDamage = EnemyConfigurations.getSpeedDamage();
        this.damageRadius = EnemyConfigurations.getDamageRadius();
        this.seeRadius  = EnemyConfigurations.getSeeRadius();
        this.setWalk_speed(EnemyConfigurations.getWalkSpeed());

        Image img = new Image("file:" + File.separator + create_proper_path(EnemyConfigurations.getTexture()), EnemyConfigurations.getTextureWidth(), EnemyConfigurations.getTextureHeight(), false, true);
        this.setTexture(img);

        if(coordinates != null) {
            this.setPosition(coordinates);
        }
    }

    public Direction generateDirection() {
        // Determine a random direction to move in
        Direction[] directions = { Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT };

        // Randomly choose a direction to move in
        Direction direction = directions[new Random().nextInt(directions.length)];

        // Adjust the direction with some probability
        if (Math.random() < 0.2) {
            direction = directions[new Random().nextInt(directions.length)];
        }
        return direction;
    }

    ///===================================//
    ///=========Getters Setters============//
    ///===================================//
    public boolean setDropChance(Item item)
    {
        this.dropChance = item;
        return true;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getSpeedDamage() {
        return speedDamage;
    }

    public void setSpeedDamage(float speedDamage) {
        this.speedDamage = speedDamage;
    }

    public Item getDropChance() {
        return dropChance;
    }

    public float getSeeRadius() {
        return seeRadius;
    }
    public double getDamageRadius() {
        return damageRadius;
    }

    public Direction getCurDirection() {
        return curDirection;
    }

    public void setCurDir(Direction cur_dir) {
        this.curDirection = cur_dir;
    }


}


