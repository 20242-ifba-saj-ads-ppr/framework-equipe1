package framework.patterns.abstractFactory;

import framework.core.Board;
import framework.core.Player;
import framework.core.piece.GamePiece;
import framework.core.rules.imp.GameRule;


import java.util.List;

public interface GameFactory {
    Board createBoard();
    List<Player> createPlayers();
    List<GamePiece> createPieces(Player player);
    GameRule createRuleEngine();
}
