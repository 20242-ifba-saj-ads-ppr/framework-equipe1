package games.jungle.patterns.composite;

import framework.core.Board;
import framework.core.Move;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import framework.core.rules.imp.GameRule;
import games.jungle.core.CellType;

public class WaterMovementRule implements GameRule {
    @Override
    public boolean validate(Move move, Board board) {
        Position to = move.getTo();
        Cell targetCell = board.getCell(to);
        GamePiece piece = move.getPiece();

        if (targetCell.getType() == CellType.WATER && !(piece instanceof Rat)) {
            return false;
        }

        return true;
    }
}
