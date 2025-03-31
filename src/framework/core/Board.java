package framework.core;

import framework.core.cell.Cell;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int width;
    private final int height;
    private final Map<Position, Cell> cells;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new HashMap<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void addCell(Position position, Cell cell) {
        if (position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        cells.put(position, cell);
    }

    public Cell getCell(Position position) {
        if (position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        return cells.get(position);
    }
}
