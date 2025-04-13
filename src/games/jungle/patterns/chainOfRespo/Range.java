package games.jungle.patterns.chainOfRespo;

import framework.core.GameBoard;
import framework.core.Position;
import framework.patterns.chainOfRespo.MoveHandler;
import games.jungle.patterns.JungleBoard;

public class Range extends MoveHandler {
    @Override
    public boolean move(Position from, Position to, GameBoard board) {
        if(!(board instanceof JungleBoard)){
            throw new IllegalArgumentException("Invalid board type");
        }
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        if ((dx + dy) == 1) {
            return true;
        } else if (next != null) {
            return next.move(from, to, board);
        }
        return false;
    }
}
