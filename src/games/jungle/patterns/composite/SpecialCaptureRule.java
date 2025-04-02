package games.jungle.patterns.composite;

import framework.core.Board;
import framework.core.Move;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import framework.core.rules.imp.GameRule;
import games.jungle.core.CellType;
import games.jungle.core.pieces.Elephant;
import games.jungle.core.pieces.Rat;

public class SpecialCaptureRule implements GameRule {
    @Override
    public boolean validate(Move move, Board board) {
        Position to = move.getTo();
        Cell targetCell = board.getCell(to);

        if (!targetCell.isOccupied()) {
            return true;
        }

        GamePiece attacker = move.getPiece();
        GamePiece defender = targetCell.getOccupant();


        if (attacker instanceof Rat && defender instanceof Elephant) {
            return true;
        }

        Position from = move.getFrom();
        Cell fromCell = board.getCell(from);

        if ((attacker instanceof Rat && fromCell.getType() == CellType.WATER) ||
                (defender instanceof Rat && targetCell.getType() == CellType.WATER)) {
            return false;
        }

        return true;
    }
}
