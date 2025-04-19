package framework.patterns.behavioral.command;

import framework.patterns.creational.prototype.Position;
import framework.core.GameBoard;
import framework.patterns.structural.flyweight.GamePiece;

public class CapturePieceCommand implements GameCommand{
    private final GameBoard board;
    private final Position target;
    private GamePiece captured;

    public CapturePieceCommand(GameBoard board, Position target) {
        this.board = board;
        this.target = target;
    }

    @Override
    public void execute() {
        captured = board.getPieceAt(target).orElse(null);
        if (captured != null) {
            board.getPieces().getAll().remove(captured);
        }
    }
}
