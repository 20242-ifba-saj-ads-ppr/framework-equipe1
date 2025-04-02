package games.jungle.patterns.composite;

import framework.core.Board;
import framework.core.Move;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.rules.imp.GameRule;
import games.jungle.core.CellType;

public class TrapRule implements GameRule {
    @Override
    public boolean validate(Move move, Board board) {
        Position to = move.getTo();
        Cell targetCell = board.getCell(to);

        if (targetCell.getType() == CellType.TRAP) {
            return true;
        }

        return true;
    }
}
