package framework.core;
import framework.patterns.flyweight.GamePiece;

import java.util.List;

public record Player(String id, List<GamePiece> pieces) {
    public Player(String id) {
        this(id, List.of());
    }
}
