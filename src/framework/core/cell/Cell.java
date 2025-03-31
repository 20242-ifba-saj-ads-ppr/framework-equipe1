package framework.core.cell;

import framework.core.Position;
import framework.core.piece.GamePiece;

public class Cell {
    private Position position;
    private CellType type;
    private GamePiece occupant;

    public Cell(Position position, CellType type) {
        this.position = position;
        this.type = type;
    }

    public boolean isOccupied() {
        return occupant != null;
    }

    public GamePiece getOccupant() {
        return occupant;
    }

    public void setOccupant(GamePiece piece) {
        this.occupant = piece;
    }

    public Position getPosition() {
        return position;
    }

    public CellType getType() {
        return type;
    }
}
