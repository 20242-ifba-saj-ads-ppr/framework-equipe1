package framework.patterns.behavioral.chainOfRespo;

import framework.patterns.creational.prototype.GameBoard;
import framework.core.Position;

public interface Move
{
    boolean move(Position from, Position to, GameBoard board);
}
