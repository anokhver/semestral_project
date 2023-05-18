package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.additional.EnemyConfigurations;
import cz.cvut.anokhver.items.Item;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import static cz.cvut.anokhver.additional.FileManagement.createProperPath;

import javafx.scene.image.Image;
import java.io.File;

public class Enemy extends Movable{

    private final String name;
    private float health;
    private final float damage;
    private float speedDamage;
    private final double damageRadius;
    private final float seeRadius;
    public float cooldown = 0;
    private Item dropChance;
    private Direction curDirection = Direction.STOP;

    public Enemy(String name, float damage, float walkSpeed, float seeRadius, double damageRadius, float speedDamage, float health, Coordinates coordinates) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speedDamage = speedDamage;
        this.damageRadius = damageRadius;
        this.seeRadius  = seeRadius;
        this.setWalk_speed(walkSpeed);

        EnemyConfigurations.init(createProperPath("con_" + name + ".json"));
        Image img = new Image("file:" + File.separator + createProperPath(EnemyConfigurations.getTexture()), EnemyConfigurations.getTextureWidth(), EnemyConfigurations.getTextureHeight(), false, true);
        this.setTexture(img);
        this.setPosition(coordinates);
    }


    public Enemy(String conName, Coordinates coordinates) {
        EnemyConfigurations.init(createProperPath("con_" + conName + ".json"));
        this.name = EnemyConfigurations.getName();
        this.health = EnemyConfigurations.getHealth();
        this.damage = EnemyConfigurations.getDamage();
        this.speedDamage = EnemyConfigurations.getSpeedDamage();
        this.damageRadius = EnemyConfigurations.getDamageRadius();
        this.seeRadius  = EnemyConfigurations.getSeeRadius();
        this.setWalk_speed(EnemyConfigurations.getWalkSpeed());

        Image img = new Image("file:" + File.separator + createProperPath(EnemyConfigurations.getTexture()), EnemyConfigurations.getTextureWidth(), EnemyConfigurations.getTextureHeight(), false, true);
        this.setTexture(img);

        if(coordinates != null) {
            this.setPosition(coordinates);
        }
    }

    /*===========================
    *Getters & Setters
    ===========================*/
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


