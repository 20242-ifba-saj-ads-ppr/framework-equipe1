package games.jungle.patterns.chainableMoveStrategies;

import framework.core.GameBoard;
import framework.core.Position;
import framework.patterns.chainOfRespo.MoveHandler;
import games.jungle.patterns.JungleBoard;

public class LeapOverRiverStrategy extends MoveHandler {
    @Override
    public boolean move(Position from, Position to, GameBoard board) {
        if(!(board instanceof JungleBoard)){
            throw new IllegalArgumentException("Invalid board type");
        }
        if (((JungleBoard) board).hasLeapPath(from, to)) {
            return true;
        } else if (next != null) {
            return next.move(from, to, board);
        }
        return false;
    }
}