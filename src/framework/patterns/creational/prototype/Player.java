package framework.patterns.creational.prototype;
import framework.core.ClonePieces;
import framework.patterns.structural.flyweight.GamePiece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements GamePrototype<Player>, ClonePieces<Player> {
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

    @Override
    public Player clone() {
        return cloneWithCache(new HashMap<>());
    }

    @Override
    public Player cloneWithCache(Map<GamePiece, GamePiece> cache) {
        Player clone = new Player(this.id);
        if (this.pieces != null) {
            clone.setPieces(this.pieces.stream()
                    .map(p -> p.cloneWithCache(cache))
                    .toList());
        }
        return clone;
    }
}
