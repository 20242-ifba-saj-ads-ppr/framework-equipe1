package framework.patterns.singleton;

import framework.core.Game;
import framework.core.Move;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;

public class GameManager {
    private static GameManager instance;
    private Game currentGame;

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game game) {
        this.currentGame = game;
    }


    public boolean makeMove(Position from, Position to) {
        if (currentGame == null) {
            return false;
        }

        Cell fromCell = currentGame.getBoard().getCell(from);
        if (fromCell == null || !fromCell.isOccupied()) {
            return false;
        }

        GamePiece piece = fromCell.getOccupant();
        Move move = new Move(from, to, piece);

        return currentGame.makeMove(move);
    }

}

