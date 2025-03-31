package framework.patterns.builder;

import framework.core.Board;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.cell.CellType;

import java.util.HashMap;
import java.util.Map;

public class BoardBuilder implements Builder<Board>{
    private int width;
    private int height;
    private Map<Position, CellType> cellTypes = new HashMap<>();

    public BoardBuilder setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public BoardBuilder setCellTypes(CellType cellType) {
       for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y ++) {
                cellTypes.put(new Position(x, y), cellType);
            }
        }
       return this;
    }

    public BoardBuilder addSpecificCellType(Position position, CellType cellType) {
        if (position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        cellTypes.put(position, cellType);
        return this;
    }


    @Override
    public Board build() {
        Board board = new Board(width, height);
        for(Map.Entry<Position, CellType> entry : cellTypes.entrySet()) {
            Position position = entry.getKey();
            CellType cellType = entry.getValue();
            var cell = new Cell(position, cellType);
            board.addCell(position, cell);
        }
        return board;
    }
}