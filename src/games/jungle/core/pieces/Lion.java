package games.jungle.core.pieces;

import framework.core.Player;
import framework.core.piece.PieceType;

public class Lion extends JumpingAnimal {
    public Lion(Player owner) {
        super(owner, 7);
    }

    @Override
    public PieceType getType() {
        return games.jungle.core.PieceType.LION;
    }
}
