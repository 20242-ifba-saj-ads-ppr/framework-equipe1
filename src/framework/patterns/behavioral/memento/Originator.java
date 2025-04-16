package framework.patterns.behavioral.memento;

public interface Originator <T>{
    T saveState();
    void restoreState(T memento);
}
