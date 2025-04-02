package games.jungle.patterns.composite;

import framework.core.Board;
import framework.core.Move;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import framework.core.rules.imp.GameRule;

public class RankBasedCaptureRule implements GameRule {
    @Override
    public boolean validate(Move move, Board board) {
        Position to = move.getTo();
        Cell targetCell = board.getCell(to);

        if (!targetCell.isOccupied()) {
            return true;
        }

        GamePiece attacker = move.getPiece();
        GamePiece defender = targetCell.getOccupant();

        if (attacker.getOwner().equals(defender.getOwner())) {
            return false;
        }

        return attacker.canCapture(defender);
    }
}
