package framework.patterns.composite.rules;

import framework.core.Board;
import framework.core.Move;
import framework.core.Position;
import framework.core.rules.imp.GameRule;

public class AdjacentMovementRule implements GameRule {
    @Override
    public boolean validate(Move move, Board board) {
        Position from = move.getFrom();
        Position to = move.getTo();

        int dx = Math.abs(to.x() - from.x());
        int dy = Math.abs(to.y() - from.y());

        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

}
