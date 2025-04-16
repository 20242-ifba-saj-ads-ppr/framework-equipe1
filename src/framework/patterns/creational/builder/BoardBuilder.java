package framework.patterns.creational.builder;

import framework.patterns.creational.prototype.GameBoard;

public interface BoardBuilder {
    void createBoard(int width, int height);
    void configureCells();
    void populatePieces();
    GameBoard getResult();
}
