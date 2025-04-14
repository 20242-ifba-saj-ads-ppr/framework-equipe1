package framework.core;
import framework.patterns.flyweight.GamePiece;

import java.util.List;

public class Player{
    private List<GamePiece> pieces;
    private final String id;
    public Player(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<GamePiece> getPieces() {
        return pieces;
    }

    public void setPieces(List<GamePiece> pieces) {
        this.pieces = pieces;
    }
}
