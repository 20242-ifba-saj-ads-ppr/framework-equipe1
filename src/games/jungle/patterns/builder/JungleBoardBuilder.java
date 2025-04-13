package games.jungle.patterns.builder;

import framework.core.GameBoard;
import framework.patterns.builder.BoardBuilder;
import games.jungle.core.JungleCellType;

public class JungleBoardBuilder implements BoardBuilder {

    private GameBoard board;

    @Override
    public void createBoard(int width, int height) {
        board = new GameBoard(width, height);
    }

    @Override
    public void configureCells() {
        this.placeWater();
        this.placeTraps();
        this.placeDens();
    }

    @Override
    public void populatePieces() {
        this.placeInitialAnimals();
    }

    @Override
    public GameBoard getResult() {
        return board;
    }

    private void placeWater() {
        for (int x : new int[]{1, 2, 4, 5}) {
            for (int y = 3; y <= 5; y++) {
                board.setCellType(x, y, JungleCellType.WATER);
            }
        }
    }

    private void placeTraps() {
        board.setCellType(2, 0, JungleCellType.TRAP);
        board.setCellType(4, 0, JungleCellType.TRAP);
        board.setCellType(3, 1, JungleCellType.TRAP);
        board.setCellType(2, 8, JungleCellType.TRAP);
        board.setCellType(4, 8, JungleCellType.TRAP);
        board.setCellType(3, 7, JungleCellType.TRAP);
    }

    private void placeDens() {
        board.setCellType(3, 0, JungleCellType.DEN);
        board.setCellType(3, 8, JungleCellType.DEN);
    }

    private void placeInitialAnimals() {
       //Implementar
    }
}
