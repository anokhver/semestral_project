package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.items.Item;
import cz.cvut.anokhver.movement.Coordinates;

public class Enemy {

    private final String name;
    private final float damage;
    private final float walkSpeed;
    private Item dropChance;
    private final float seeRadius;
    private float health;
    private float speed_damage;
    private boolean live = true;
    private Coordinates position;

    public Enemy(String name, float damage, float walkSpeed, Item dropChance, float seeRadius) {
        this.name = name;
        this.damage = damage;
        this.walkSpeed = walkSpeed;
        this.dropChance = dropChance;
        this.seeRadius = seeRadius;
    }

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

    public float getSpeed_damage() {
        return speed_damage;
    }

    public void setSpeed_damage(float speed_damage) {
        this.speed_damage = speed_damage;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public Item getDropChance() {
        return dropChance;
    }

    public float getSeeRadius() {
        return seeRadius;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }
}
