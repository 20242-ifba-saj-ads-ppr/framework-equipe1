package framework.core;

import framework.core.cellType.CellType;
import framework.patterns.flyweight.GamePiece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard{
    private final int width, height;
    private final Map<Position, Cell> cells;
    private List<GamePiece> pieces;

    public GameBoard(int width, int height){
        this.width = width;
        this.height = height;
        this.cells = new HashMap<>();
        initializeCells();
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

    public Cell getCell(Position pos) {
        return cells.get(pos);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<GamePiece> getPieces() {
        return pieces;
    }

    public void setPieces(List<GamePiece> pieces) {
        this.pieces = pieces;
    }
}
