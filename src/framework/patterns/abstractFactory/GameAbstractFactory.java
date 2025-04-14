package framework.patterns.abstractFactory;

import framework.core.GameBoard;
import framework.core.GameId;
import framework.core.PieceType;
import framework.core.Player;
import framework.patterns.builder.BoardBuilder;
import framework.patterns.flyweight.GamePiece;

import java.util.List;
import java.util.Map;

public interface GameAbstractFactory {
    List<GamePiece> createGamePieces();
    GameBoard createGameBoard();
    List<Player> createPlayers();
}

