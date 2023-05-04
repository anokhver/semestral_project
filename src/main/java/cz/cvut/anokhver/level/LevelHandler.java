package cz.cvut.anokhver.level;

import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.movement.Coordinates;

public class LevelHandler {
    protected final Player character;

    public LevelHandler(){
        character = new Player();
    }

    public void moveCharacter(Coordinates offset){
        Coordinates curPosition = character.getPosition();
        character.setPosition(Coordinates.add(curPosition, offset));
    }

    public Coordinates getCharacterPosition() {
        return character.getPosition();
    }
}
