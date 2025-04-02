package games.jungle.patterns.composite;

import framework.core.Board;
import framework.core.Game;
import framework.core.Move;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import framework.core.rules.imp.GameRule;
import games.jungle.core.CellType;

public class DenRule implements GameRule {
    @Override
    public boolean validate(Move move, Board board) {
        Position to = move.getTo();
        Cell targetCell = board.getCell(to);
        GamePiece piece = move.getPiece();

        if (targetCell.getType() == CellType.DEN) {
            Game game = framework.patterns.singleton.GameManager.getInstance().getCurrentGame();
            if (game == null) return false;

            int playerIndex = -1;
            for (int i = 0; i < game.getPlayers().size(); i++) {
                if (piece.getOwner().equals(game.getPlayers().get(i))) {
                    playerIndex = i;
                    break;
                }
            }

            if ((playerIndex == 0 && to.y() == 0) || (playerIndex == 1 && to.y() == board.getHeight() - 1)) {
                return false;
            }
        }

        return true;
    }
}