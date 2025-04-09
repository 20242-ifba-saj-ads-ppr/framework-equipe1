package framework.patterns.strategy;

import framework.core.GameBoard;
import framework.core.Position;

public interface MoveStrategy
{
    boolean move(Position from, Position to, GameBoard board);
}
