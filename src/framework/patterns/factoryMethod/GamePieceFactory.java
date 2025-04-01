package framework.patterns.factoryMethod;

import framework.core.Player;
import framework.core.piece.GamePiece;
import framework.core.piece.PieceType;

public interface GamePieceFactory {
    GamePiece createPiece(PieceType type, Player owner);
}
