package games.jungle.patterns.composite;

import framework.core.Board;
import framework.core.Move;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import framework.patterns.composite.rules.AdjacentMovementRule;
import games.jungle.core.CellType;
import games.jungle.core.pieces.Lion;
import games.jungle.core.pieces.Tiger;

public class JungleAdjacentMovementRule extends AdjacentMovementRule {
    @Override
    public boolean validate(Move move, Board board) {
        Position from = move.getFrom();
        Position to = move.getTo();

        int dx = Math.abs(to.x() - from.x());
        int dy = Math.abs(to.y() - from.y());

        GamePiece piece = move.getPiece();

        if (piece instanceof Lion || piece instanceof Tiger) {
            // Regra especial para Lion e Tiger pulando sobre a água
            if (isJumpingOverWater(from, to, board)) {
                return true;
            }
        }

        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    private boolean isJumpingOverWater(Position from, Position to, Board board) {
        int dx = to.x() - from.x();
        int dy = to.y() - from.y();

        // Movimento deve ser em linha reta
        if (dx != 0 && dy != 0) {
            return false;
        }

        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        int stepX = (dx == 0) ? 0 : dx / Math.abs(dx);
        int stepY = (dy == 0) ? 0 : dy / Math.abs(dy);

        boolean allWater = true;
        boolean hasLand = false;

        for (int i = 1; i < steps; i++) {
            Position pos = new Position(from.x() + (i * stepX), from.y() + (i * stepY));
            Cell cell = board.getCell(pos);

            if (cell.getType() != CellType.WATER) {
                allWater = false;
                break;
            } else {
                if (cell.isOccupied()) {
                    return false;
                }
            }
            hasLand = true;
        }

        Cell destCell = board.getCell(to);
        return allWater && hasLand && destCell.getType() != CellType.WATER;
    }
}
