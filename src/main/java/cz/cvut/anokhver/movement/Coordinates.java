package cz.cvut.anokhver.movement;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates add(Coordinates original, Coordinates other) {
        int newX = original.getX() + other.getX();
        int newY = original.getY() + other.getY();
        return new Coordinates(newX, newY);
    }
}

