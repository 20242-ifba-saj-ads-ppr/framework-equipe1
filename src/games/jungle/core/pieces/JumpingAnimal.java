package games.jungle.core.pieces;

import framework.core.Board;
import framework.core.Player;
import framework.core.Position;
import framework.core.cell.Cell;
import games.jungle.core.CellType;

public abstract class JumpingAnimal extends Animal {
    public JumpingAnimal(Player owner, int rank) {
        super(owner, rank);
    }

    @Override
    public boolean canMoveTo(Board board, Position from, Position to) {
        if (!super.canMoveTo(board, from, to)) {
            return false;
        }

        int dx = to.x() - from.x();
        int dy = to.y() - from.y();

        if ((dx != 0 && dy != 0) || (dx == 0 && dy == 0)) {
            return super.canMoveTo(board, from, to);
        }

        if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) {
            return super.canMoveTo(board, from, to);
        }

        return isJumpingOverWater(board, from, to, dx, dy);
    }

    private boolean isJumpingOverWater(Board board, Position from, Position to, int dx, int dy) {
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        int stepX = (dx == 0) ? 0 : dx / Math.abs(dx);
        int stepY = (dy == 0) ? 0 : dy / Math.abs(dy);

        for (int i = 1; i < steps; i++) {
            Position pos = new Position(from.x() + (i * stepX), from.y() + (i * stepY));
            Cell cell = board.getCell(pos);
            if (cell.getType() != CellType.WATER || cell.isOccupied()) {
                return false;
            }
        }
        return true;
    }
}
