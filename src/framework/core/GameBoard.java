package framework.core;

import framework.core.cellType.CellType;
import framework.patterns.behavioral.iterator.PieceDeck;
import framework.patterns.creational.prototype.Position;
import framework.patterns.structural.flyweight.GamePiece;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameBoard {
    private final int width, height;
    private final Map<Position, Cell> cells;
    private PieceDeck pieces;

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

    public PieceDeck getPieces() {
        return pieces;
    }

    public void setPieces(PieceDeck pieces) {
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

        if (!this.pieces.getAll().contains(piece)) {
            this.pieces.add(piece);
        }
    }
}
