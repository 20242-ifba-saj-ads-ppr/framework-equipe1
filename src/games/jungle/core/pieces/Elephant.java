package games.jungle.core.pieces;

import framework.core.Player;
import framework.core.piece.GamePiece;
import framework.core.piece.PieceType;


public class Elephant extends Animal {
    public Elephant(Player owner) {
        super(owner, 8);  // Elefante tem rank 8 (mais alto)
    }

    @Override
    public PieceType getType() {
        return games.jungle.core.PieceType.ELEPHANT;
    }

    @Override
    public boolean canCapture(GamePiece target) {
        if (target instanceof Rat) {
            return false;
        }
        return super.canCapture(target);
    }
}
