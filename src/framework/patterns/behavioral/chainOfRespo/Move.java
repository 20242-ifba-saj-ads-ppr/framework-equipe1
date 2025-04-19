package framework.patterns.behavioral.chainOfRespo;

import framework.core.GameBoard;
import framework.patterns.creational.prototype.Position;

public interface Move
{
    boolean move(Position from, Position to, GameBoard board);
}
