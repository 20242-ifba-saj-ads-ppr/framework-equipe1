package framework.patterns.builder;

import framework.core.GameBoard;

public interface BoardBuilder {
    void createBoard(int width, int height);
    void configureCells();
    void populatePieces();
    GameBoard getResult();
}
