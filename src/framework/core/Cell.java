package framework.core;

import framework.core.cellType.BasicCellType;
import framework.core.cellType.CellType;
import framework.patterns.creational.prototype.Position;

public class Cell {
    private final Position position;
    private CellType type;

    public Cell(Position position) {
        this.position = position;
        this.type = BasicCellType.NORMAL;
    }

    public Cell(Position position, CellType type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }
}
