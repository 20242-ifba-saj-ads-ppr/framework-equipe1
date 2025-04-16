package framework.patterns.behavioral.command;

import framework.patterns.creational.prototype.GameBoard;
import framework.core.Position;
import framework.patterns.structural.flyweight.GamePiece;

public class MoveCommand implements GameCommand{

    private final GameBoard gameBoard;
    private final Position from, to;
    private GamePiece piece;

    public MoveCommand(GameBoard gameBoard, Position from, Position to) {
        this.gameBoard = gameBoard;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        piece = gameBoard.getPieceAt(from).orElseThrow(() -> new IllegalArgumentException("No piece at " + from));
        piece.move(to, gameBoard);
    }
}
