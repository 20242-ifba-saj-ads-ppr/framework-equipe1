package framework.patterns.abstractFactory;

import framework.core.GameBoard;
import framework.core.PieceType;
import framework.patterns.flyweight.GamePiece;
import framework.patterns.flyweight.GamePieceProps;

import java.util.HashMap;
import java.util.List;

public interface GameAbstractFactory {
    List<GamePiece> createGamePiece(HashMap<PieceType, Integer> gamePiecesRequest);
    GameBoard createGameBoard();
}
