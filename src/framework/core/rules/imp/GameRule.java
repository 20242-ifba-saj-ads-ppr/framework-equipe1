package framework.core.rules.imp;

import framework.core.Board;
import framework.core.Move;
import framework.core.rules.Rule;

public interface GameRule extends Rule {
    boolean validate(Move move, Board board);
}
