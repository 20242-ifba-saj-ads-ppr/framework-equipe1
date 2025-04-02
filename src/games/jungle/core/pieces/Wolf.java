package games.jungle.core.pieces;

import framework.core.Player;
import games.jungle.core.PieceType;

public class Wolf extends Animal {
    public Wolf(Player owner) {
        super(owner, 3);
    }

    @Override
    public PieceType getType() {
        return PieceType.WOLF;
    }
}
