package cz.cvut.anokhver.movement;

import java.util.Random;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates add(Coordinates original, Coordinates other) {
        int newX = original.getX() + other.getX();
        int newY = original.getY() + other.getY();
        return new Coordinates(newX, newY);
    }

    public static double minus(Coordinates a, Coordinates b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static Direction generateDirection() {
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

