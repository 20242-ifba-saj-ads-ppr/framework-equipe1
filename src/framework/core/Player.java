package framework.core;

import framework.core.piece.GamePiece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String id;
    private String name;
    private List<GamePiece> pieces;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.pieces = new ArrayList<>();
    }

    public void addPiece(GamePiece piece) {
        pieces.add(piece);
    }

    public void removePiece(GamePiece piece) {
        pieces.remove(piece);
    }

    public boolean hasPieces() {
        return !pieces.isEmpty();
    }

    public List<GamePiece> getPieces() {
        return pieces;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
