package framework.patterns.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public record HistoryManager(
        Deque<GameMemento> history
) implements Caretaker<GameMemento> {

    public HistoryManager() {
        this(new ArrayDeque<>());
    }

    @Override
    public void backup(Originator<GameMemento> originator) {
        history.push(originator.saveState());
    }

    @Override
    public void undo(Originator<GameMemento> originator) {
        if (!history.isEmpty()) {
            originator.restoreState(history.pop());
        }
    }

    @Override
    public boolean hasHistory() {
        return !history.isEmpty();
    }
}
