package games.jungle.patterns.builder;

import framework.patterns.creational.prototype.Position;
import framework.core.GameBoard;
import framework.patterns.creational.builder.BoardBuilder;
import framework.patterns.structural.flyweight.GamePiece;
import games.jungle.core.JungleCellType;

import java.util.List;

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
        List<GamePiece> pieces = board.getPieces().getAll();

        int[][] positions = {
                {0, 6}, {2, 6}, {4, 6}, {6, 6},
                {0, 2}, {2, 2}, {4, 2}, {6, 2}
        };

        for (int i = 0; i < pieces.size(); i++) {
            GamePiece piece = pieces.get(i);
            int x = positions[i][0];
            int y = positions[i][1];
            board.placePiece(piece, new Position(x, y));
        }
    }
}
