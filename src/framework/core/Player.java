package framework.core;

import framework.core.piece.GamePiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private String id;
    private String name;
    private List<GamePiece> pieces;
    private Map<GamePiece, Position> piecePositions;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.pieces = new ArrayList<>();
        this.piecePositions = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addPiece(GamePiece piece) {
        pieces.add(piece);
    }

    public void addPieceWithPosition(GamePiece piece, Position position) {
        pieces.add(piece);
        piecePositions.put(piece, position);
    }

    public void removePiece(GamePiece piece) {
        pieces.remove(piece);
        piecePositions.remove(piece);
    }

    public boolean hasPieces() {
        return !pieces.isEmpty();
    }

    public List<GamePiece> getPieces() {
        return pieces;
    }

    public Map<GamePiece, Position> getPiecePositions() {
        return piecePositions;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        return id.equals(other.id);
    }
}
