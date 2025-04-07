package framework.core;

import framework.core.cellType.CellType;

import java.util.HashMap;
import java.util.Map;

public record GameBoard (
        int width,
        int height,
        Map<Position, Cell> cells
) {

    public GameBoard(int width, int height){
        this(width, height, new HashMap<>());
        this.initializeCells();
    }

    private void initializeCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Position pos = new Position(x, y);
                cells.put(pos, new Cell(pos));
            }
        }
    }

    public void setCellType(int x, int y, CellType type) {
        Position pos = new Position(x, y);
        if (cells.containsKey(pos)) {
            cells.get(pos).setType(type);
        }
    }
}
