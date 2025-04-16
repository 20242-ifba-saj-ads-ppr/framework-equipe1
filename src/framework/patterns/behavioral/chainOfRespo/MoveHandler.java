package framework.patterns.behavioral.chainOfRespo;

public abstract class MoveHandler implements Move {
    protected MoveHandler next;

    public MoveHandler setNext(MoveHandler next) {
        this.next = next;
        return next;
    }
}
