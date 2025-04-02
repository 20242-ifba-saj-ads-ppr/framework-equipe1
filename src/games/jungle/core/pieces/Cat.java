package games.jungle.core.pieces;

import framework.core.Player;
import games.jungle.core.PieceType;

public class Cat extends Animal {
    public Cat(Player owner) {
        super(owner, 2);
    }

    @Override
    public PieceType getType() {
        return PieceType.CAT;
    }
}