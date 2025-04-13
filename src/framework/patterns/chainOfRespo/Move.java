package framework.patterns.chainOfRespo;

import framework.core.GameBoard;
import framework.core.Position;

public interface Move
{
    boolean move(Position from, Position to, GameBoard board);
}
