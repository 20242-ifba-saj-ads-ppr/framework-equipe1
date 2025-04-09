package framework.patterns.chainOfRespo;

import framework.patterns.strategy.MoveStrategy;

public abstract class MoveHandler implements MoveStrategy {
    protected MoveHandler next;

    public MoveHandler setNext(MoveHandler next) {
        this.next = next;
        return next;
    }
}
