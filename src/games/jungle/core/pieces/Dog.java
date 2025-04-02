package games.jungle.core.pieces;

import framework.core.Player;
import games.jungle.core.PieceType;

public class Dog extends Animal {
    public Dog(Player owner) {
        super(owner, 4);
    }

    @Override
    public PieceType getType() {
        return PieceType.DOG;
    }
}
