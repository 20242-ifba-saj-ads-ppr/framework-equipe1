package games.jungle.patterns.chainOfRespo;

import framework.core.GameBoard;
import framework.core.Position;
import framework.patterns.chainOfRespo.MoveHandler;
import games.jungle.core.JungleCellType;

public class TerritoryRestriction extends MoveHandler {

    @Override
    public boolean move(Position from, Position to, GameBoard board) {
        var gamePiece = board.getPieces().stream().filter(p -> p.getPosition().equals(from)).findFirst().orElseThrow(
                () -> new IllegalArgumentException("No game piece found at the given position"));;
        var Cell = board.getCell(to);


        if (!(Cell.getType().equals(JungleCellType.DEN)) && isOwnDen(gamePiece.getInitialPosition(), Cell.getPosition())) {
            return true;
        } else if (next != null) {
            return next.move(from, to, board);
        }
        return false;
    }

    private boolean isOwnDen(Position gamePieceInitialPosition, Position denPosition) {
        return (gamePieceInitialPosition.x() == denPosition.x() && gamePieceInitialPosition.y() == denPosition.y()) ||
                (gamePieceInitialPosition.x() == denPosition.x() && gamePieceInitialPosition.y() + 1 == denPosition.y()) ||
                (gamePieceInitialPosition.x() == denPosition.x() && gamePieceInitialPosition.y() - 1 == denPosition.y()) ||
                (gamePieceInitialPosition.x() + 1 == denPosition.x() && gamePieceInitialPosition.y() == denPosition.y()) ||
                (gamePieceInitialPosition.x() - 1 == denPosition.x() && gamePieceInitialPosition.y() == denPosition.y());
    }
}
