package framework.patterns.creational.builder;

import framework.core.GameBoard;

public class GameBoardDirector {
    private final BoardBuilder builder;

    public GameBoardDirector(BoardBuilder builder) {
        this.builder = builder;
    }

    public GameBoard construct(int width, int height) {
        builder.createBoard(width, height);
        builder.configureCells();
        builder.populatePieces();
        return builder.getResult();
    }
}
