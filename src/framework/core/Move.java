package framework.core;

import framework.core.cell.Cell;
import framework.core.piece.GamePiece;

public class Move {
    private Position from;
    private Position to;
    private GamePiece piece;
    private GamePiece catpuredPiece;

    public Move(Position from, Position to, GamePiece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public void execute(Board board) {
        Cell fromCell = board.getCell(from);
        Cell toCell = board.getCell(to);

        var capturedPiece = toCell.getOccupant();

        fromCell.setOccupant(null);

        toCell.setOccupant(piece);

        if(catpuredPiece != null) {
            capturedPiece.getOwner().removePiece(catpuredPiece);
        }
    }
}
