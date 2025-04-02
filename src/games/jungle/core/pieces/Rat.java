package games.jungle.core.pieces;

import framework.core.Board;
import framework.core.Player;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import games.jungle.core.CellType;
import games.jungle.core.PieceType;

public class Rat extends Animal {
    public Rat(Player owner) {
        super(owner, 1);  // Rato tem rank 1 (mais baixo)
    }

    @Override
    public PieceType getType() {
        return PieceType.RAT;
    }

    @Override
    public boolean canMoveTo(Board board, Position from, Position to) {
        return super.canMoveTo(board, from, to);
    }

    @Override
    public boolean canCapture(GamePiece target) {
        if (target instanceof Elephant) {
            return true;
        }

        Board board = framework.patterns.singleton.GameManager.getInstance().getCurrentGame().getBoard();
        Position ratPos = null;

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Position pos = new Position(x, y);
                Cell cell = board.getCell(pos);
                if (cell != null && cell.getOccupant() == this) {
                    ratPos = pos;
                    break;
                }
            }
            if (ratPos != null) break;
        }

        if (ratPos != null) {
            Cell ratCell = board.getCell(ratPos);
            if (ratCell.getType() == CellType.WATER && !(target instanceof Rat)) {
                return false;
            }
        }

        return super.canCapture(target);
    }
}