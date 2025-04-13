package games.jungle.patterns.chainOfRespo;

import framework.core.GameBoard;
import framework.core.Position;
import framework.patterns.chainOfRespo.MoveHandler;
import games.jungle.core.JungleCellType;

public class WaterBlock extends MoveHandler {
    @Override
    public boolean move(Position from, Position to, GameBoard board) {
        if (!board.getCell(to).getType().equals(JungleCellType.WATER)) {
            return true;
        } else if (next != null) {
            return next.move(from, to, board);
        }
        return false;
    }
}
