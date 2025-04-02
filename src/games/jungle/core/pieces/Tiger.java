package games.jungle.core.pieces;

import framework.core.Player;
import games.jungle.core.PieceType;

public class Tiger extends JumpingAnimal {
    public Tiger(Player owner) {
        super(owner, 6);
    }

    @Override
    public PieceType getType() {
        return PieceType.TIGER;
    }
}
