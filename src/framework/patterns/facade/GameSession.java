package framework.patterns.facade;

import framework.core.GameBoard;
import framework.core.Player;
import framework.patterns.abstractFactory.GameAbstractFactory;
import framework.patterns.command.GameCommand;
import framework.patterns.flyweight.GamePiece;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameSession {
    private final GameBoard gameBoard;
    private final List<Player> players;
    private final Deque<GameCommand> history = new ArrayDeque<>();
    private int turn = 0;

    public GameSession(GameAbstractFactory factory) {
        gameBoard = factory.createGameBoard();
        players = factory.createPlayers();
        List<GamePiece> gamePieces = factory.createGamePieces();
        gameBoard.setPieces(gamePieces);
        distribute(gamePieces);
    }

    public void executeCommand(GameCommand command) {
        command.execute();
        history.push(command);
        turn = (turn + 1) % players.size();
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            history.pop().undo();
            turn = (turn - 1 + players.size()) % players.size();
        }
    }

    public GameBoard gameBoard() {
        return gameBoard;
    }
    public Player currentPlayer() {
        return players.get(turn);
    }


    private void distribute(List<GamePiece> pieces) {
        int per = pieces.size() / players.size();
        for (int i = 0; i < players.size(); i++)
            players.get(i).setPieces(pieces.subList(i*per, (i+1)*per));
    }
}
