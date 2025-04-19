package framework.patterns.behavioral.memento;

import framework.patterns.creational.prototype.Position;
import framework.patterns.structural.flyweight.GamePiece;

public record GameMemento(
        GamePiece piece,
        Position previous,
        Position current,
        int turn
) {

}
