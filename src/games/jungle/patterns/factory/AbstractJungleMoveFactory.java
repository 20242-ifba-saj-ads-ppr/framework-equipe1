package games.jungle.patterns.factory;

import framework.patterns.chainOfRespo.MoveHandler;
import framework.patterns.chainOfRespo.Move;
import games.jungle.core.JunglePieceType;

public abstract class AbstractJungleMoveFactory {
    abstract public Move createMoveChain(JunglePieceType type);
    protected Move chain(MoveHandler... strategies) {
        for (int i = 0; i < strategies.length - 1; i++) {
            strategies[i].setNext(strategies[i + 1]);
        }
        return strategies[0];
    }
}
