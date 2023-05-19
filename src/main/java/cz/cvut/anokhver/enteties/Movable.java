package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import javafx.scene.image.Image;

import java.util.HashMap;

import static cz.cvut.anokhver.additional.FileManagement.getFileFromResourceAsStream;
import static cz.cvut.anokhver.movement.Coordinates.minus;

/**
 * Implement the movable entity with texture
 *
 * @author Veronika
 */
public abstract class Movable {
    private final HashMap<String, Image> textures = new HashMap<>();
    private Coordinates position;
    private Image curTexture;
    private float walk_speed;

    /**
     * Calculate distance between two creatures
     *
     * @param creature1
     * @param creature2
     * @return distance
     */
    public static double rangeCalculateCreatures(Movable creature1, Movable creature2) {
        int creature1X = (int) (creature1.getPosition().getX() + creature1.getTexture().getWidth() / 2.0);
        int creature1Y = (int) (creature1.getPosition().getY() + creature1.getTexture().getHeight() / 2.0);
        int creature2X = (int) (creature2.getPosition().getX() + creature2.getTexture().getWidth() / 2.0);
        int creature2Y = (int) (creature2.getPosition().getY() + creature2.getTexture().getHeight() / 2.0);

        return minus(new Coordinates(creature1X, creature1Y), new Coordinates(creature2X, creature2Y));
    }

    /**
     * Move entities
     *
     * @param direction where it will move
     * @param delta
     */
    public void move(Direction direction, double delta) {

        float speed = (float) (this.getWalk_speed() * delta);
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        // Update the creature position based on the given direction
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
            case STOP:
                break;
            default:
                break;
        }

        // Update the creature position if it's within the game world boundaries
        if (checkOnMap(x, y)) {
            this.setPosition(new Coordinates(x, y));
        }
    }

    private boolean checkOnMap(int x, int y) {
        return ((x >= 0 && x <= Configuration.getMapWidth() * Configuration.getTileSize() - this.getTexture().getWidth())
                && (y >= 0 && y <= Configuration.getMapHeight() * Configuration.getTileSize() - this.getTexture().getHeight()));

    }

    /**
     * load all textures for moving animation
     *
     * @param width
     * @param heights
     */
    protected void loadAllTextures(String fileName, int width, int heights) {

        for (Direction direction : Direction.values()) {
            this.getTextures().put("anim" + direction, new Image(getFileFromResourceAsStream(fileName + direction.name() + ".png"), width, heights, false, false));
        }
    }

    public Coordinates getPosition() {
        return position;
    }

    /*===========================
    *Getters & Setters
    ===========================*/
    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public float getWalk_speed() {
        return walk_speed;
    }

    public void setWalk_speed(float walk_speed) {
        this.walk_speed = walk_speed;
    }

    public Image getTexture() {
        return curTexture;
    }

    public void setTexture(Image img) {
        this.curTexture = img;
    }

    public HashMap<String, Image> getTextures() {
        return textures;
    }

    public void setCurTextureDirection(Direction direction) {
        this.curTexture = textures.get("anim" + direction);
    }


}
