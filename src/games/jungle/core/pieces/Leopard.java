package games.jungle.core.pieces;

import framework.core.Player;
import games.jungle.core.PieceType;

public class Leopard extends Animal {
    public Leopard(Player owner) {
        super(owner, 5);
    }

    @Override
    public PieceType getType() {
        return PieceType.LEOPARD;
    }
}
