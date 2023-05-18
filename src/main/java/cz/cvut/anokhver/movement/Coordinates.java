package cz.cvut.anokhver.movement;

import java.util.Random;

/**
 * Implements coordinates and calculating distance beetwen them
 *
 * @author Veronika
 */
public class Coordinates {
    private int x;
    private int y;

    /**
     * Setting coordinates
     *
     * @param x coor
     * @param y coor
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Add two coordinates
     *
     * @param original coor
     * @param other coor
     * @return new coor
     */
    public static Coordinates add(Coordinates original, Coordinates other) {
        int newX = original.getX() + other.getX();
        int newY = original.getY() + other.getY();
        return new Coordinates(newX, newY);
    }

    /**
     * Calculates the distance between two points
     *
     * @param a coordinates of first point
     * @param b coordinates of second point
     * @return the distance between those two points
     */
    public static double minus(Coordinates a, Coordinates b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Generates some random movement direction
     *
     * @return returns random generated direction
     */
    public static Direction generateDirection() {
        // Determine a random direction to move in
        Direction[] directions = {Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT};

        // Randomly choose a direction to move in
        Direction direction = directions[new Random().nextInt(directions.length)];

        // Adjust the direction with some probability
        if (Math.random() < 0.2) {
            direction = directions[new Random().nextInt(directions.length)];
        }
        return direction;
    }

    /*===========================
     *Getters & Setters
    ===========================*/

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

