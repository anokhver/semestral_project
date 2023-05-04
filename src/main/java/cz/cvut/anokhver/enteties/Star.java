package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.movement.Coordinates;

public class Star {

    private final Coordinates position;
    private boolean pickUp;
    public Star(Coordinates position) {
        this.position = position;
        this.pickUp = false;
    }

    public Coordinates getPosition() {
        return position;
    }


    public boolean isPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }
}
