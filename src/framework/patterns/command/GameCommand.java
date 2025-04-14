package framework.patterns.command;

public interface GameCommand {
    void execute();
    void undo();
}
