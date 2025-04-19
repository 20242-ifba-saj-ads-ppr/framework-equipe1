package framework.patterns.behavioral.iterator;

import framework.patterns.structural.flyweight.GamePiece;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public record PieceDeck(
        List<GamePiece> pieces
) implements Iterable<GamePiece>{

    public void add(GamePiece piece) {
        pieces.add(piece);
    }

    public GamePiece get(int index) {
        return pieces.get(index);
    }

    public int size() {
        return pieces.size();
    }

    public List<GamePiece> getAll(){
        return pieces;
    }

    public Stream<GamePiece> stream() { return pieces.stream(); }

    @Override
    public Iterator<GamePiece> iterator() {
       return pieces.iterator();
    }
}
