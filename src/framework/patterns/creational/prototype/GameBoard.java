package framework.patterns.creational.prototype;

import framework.core.Cell;
import framework.core.ClonePieces;
import framework.core.Position;
import framework.core.cellType.CellType;
import framework.patterns.structural.flyweight.GamePiece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameBoard implements GamePrototype<GameBoard>, ClonePieces<GameBoard> {
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

    public Optional<GamePiece> getPieceAt(Position pos) {
        return pieces == null ? Optional.empty()
                : pieces.stream().filter(p -> pos.equals(p.getPosition())).findFirst();
    }

    public void placePiece(GamePiece piece, Position position) {
        piece.setPosition(position);

        if (this.pieces == null) {
            throw new IllegalStateException("Peças ainda não foram inicializadas via setPieces().");
        }

        if (!this.pieces.contains(piece)) {
            this.pieces.add(piece);
        }
    }

    @Override
    public GameBoard clone() {
        return cloneWithCache(new HashMap<>());
    }

    @Override
    public GameBoard cloneWithCache(Map<GamePiece, GamePiece> cache) {
        GameBoard clone = new GameBoard(this.width, this.height);

        for (Map.Entry<Position, Cell> entry : this.cells.entrySet()) {
            Cell originalCell = entry.getValue();
            clone.getCell(entry.getKey()).setType(originalCell.getType());
        }

        if (this.pieces != null) {
            clone.setPieces(this.pieces.stream()
                    .map(p -> p.cloneWithCache(cache))
                    .toList());
        }

        return clone;
    }
}
