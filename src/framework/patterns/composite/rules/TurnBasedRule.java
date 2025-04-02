package framework.patterns.composite.rules;

import framework.core.Board;
import framework.core.Game;
import framework.core.Move;
import framework.core.Player;
import framework.core.piece.GamePiece;
import framework.core.rules.imp.GameRule;
import framework.patterns.singleton.GameManager;

public class TurnBasedRule implements GameRule {
    @Override
    public boolean validate(Move move, Board board) {
        Game game = GameManager.getInstance().getCurrentGame();
        if (game == null) return false;

        Player currentPlayer = game.getCurrentPlayer();
        GamePiece piece = move.getPiece();

        return piece.getOwner().equals(currentPlayer);
    }
}