package framework.patterns.abstractFactory;

import framework.core.Board;
import framework.core.Player;
import framework.core.piece.GamePiece;

import java.util.List;

public interface GameFactory {
    Board createBoard();
    List<Player> createPlayers();
    List<GamePiece> createPieces();

}
