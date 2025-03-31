package framework.core.piece;

import framework.core.Board;
import framework.core.Player;
import framework.core.Position;

public interface GamePiece{
    PieceType getType();
    Player getOwner();
    boolean canMoveTo(Board board, Position from, Position to);
    boolean canCapture(GamePiece target);
    Position getInitialPosition();
}
