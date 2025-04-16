package framework.patterns.behavioral.memento;

public interface Caretaker<T> {
    void backup(Originator<T> originator);
    void undo(Originator<T> originator);
    boolean hasHistory();
}
