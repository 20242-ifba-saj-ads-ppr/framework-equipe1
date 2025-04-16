package framework.patterns.structural.facade;

import framework.core.Position;
import framework.patterns.behavioral.memento.GameMemento;
import framework.patterns.behavioral.memento.HistoryManager;
import framework.patterns.behavioral.memento.Originator;
import framework.patterns.creational.prototype.GameBoard;
import framework.patterns.creational.prototype.Player;
import framework.patterns.creational.abstractFactory.GameAbstractFactory;
import framework.patterns.behavioral.command.GameCommand;
import framework.patterns.behavioral.command.MoveCommand;
import framework.patterns.behavioral.command.PassTurnCommand;
import framework.patterns.structural.flyweight.GamePiece;
import framework.patterns.structural.proxy.IGameSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSession implements Originator<GameMemento>, IGameSession {

    private GameBoard gameBoard;
    private List<Player> players;
    private final HistoryManager historyManager = new HistoryManager();
    private int turn = 0;

    public GameSession(GameAbstractFactory factory) {
        gameBoard = factory.createGameBoard();
        players = factory.createPlayers();
        List<GamePiece> gamePieces = factory.createGamePieces();
        gameBoard.setPieces(gamePieces);
        distribute(gamePieces);
    }

    @Override
    public void move(Position from, Position to) {
        GameCommand command = new MoveCommand(gameBoard, from, to);
        executeCommand(command);
    }

    @Override
    public void passTurn() {
        GameCommand command = new PassTurnCommand();
        executeCommand(command);
    }

    @Override
    public void undo() {
        undoLastCommand();
    }

    @Override
    public GameBoard board() {
        return gameBoard;
    }

    @Override
    public Player currentPlayer() {
        return players.get(turn);
    }

    public void executeCommand(GameCommand command) {
        historyManager.backup(this);
        command.execute();
        turn = (turn + 1) % players.size();
    }

    public void undoLastCommand() {
        historyManager.undo(this);
    }

    private void distribute(List<GamePiece> pieces) {
        int per = pieces.size() / players.size();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPieces(pieces.subList(i * per, (i + 1) * per));
        }
    }

    @Override
    public GameMemento saveState() {
        Map<GamePiece, GamePiece> cache = new HashMap<>();
        GameBoard clonedBoard = gameBoard.cloneWithCache(cache);
        List<Player> clonedPlayers = players.stream()
                .map(p -> p.cloneWithCache(cache))
                .toList();
        return new GameMemento(clonedBoard, clonedPlayers, turn);
    }

    @Override
    public void restoreState(GameMemento memento) {
        this.gameBoard = memento.boardSnapshot();
        this.players = memento.playerSnapshot();
        this.turn = memento.turn();
    }
}
