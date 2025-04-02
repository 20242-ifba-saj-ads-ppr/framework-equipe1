package framework.core;

import framework.core.cell.Cell;
import framework.core.piece.GamePiece;

public class Move {
    private Position from;
    private Position to;
    private GamePiece piece;
    private GamePiece capturedPiece;

    public Move(Position from, Position to, GamePiece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public GamePiece getPiece() {
        return piece;
    }

    public GamePiece getCapturedPiece() {
        return capturedPiece;
    }

    public boolean execute(Board board) {
        Cell fromCell = board.getCell(from);
        Cell toCell = board.getCell(to);

        if (fromCell == null || toCell == null) {
            return false;
        }

        if (!piece.canMoveTo(board, from, to)) {
            return false;
        }

        if (toCell.isOccupied()) {
            GamePiece targetPiece = toCell.getOccupant();

            if (!piece.canCapture(targetPiece)) {
                return false;
            }

            capturedPiece = targetPiece;
            targetPiece.getOwner().removePiece(targetPiece);
        }

        fromCell.setOccupant(null);
        toCell.setOccupant(piece);

        return true;
    }

    public boolean undo(Board board) {
        if (board == null) {
            return false;
        }

        Cell fromCell = board.getCell(from);
        Cell toCell = board.getCell(to);

        if (fromCell == null || toCell == null) {
            return false;
        }

        toCell.setOccupant(null);
        fromCell.setOccupant(piece);

        if (capturedPiece != null) {
            toCell.setOccupant(capturedPiece);
            capturedPiece.getOwner().addPiece(capturedPiece);
        }

        return true;
    }

    public boolean validate(RuleEngine rules, Board board) {
        return rules.validateMove(this, board);
    }
}
