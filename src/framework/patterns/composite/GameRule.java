package framework.patterns.composite;

import framework.core.Board;
import framework.core.Move;

public interface GameRule {
    boolean validate(Move move, Board board);
}
