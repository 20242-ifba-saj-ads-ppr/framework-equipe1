package framework.patterns.structural.facade;

import framework.patterns.creational.prototype.Position;
import framework.patterns.behavioral.iterator.PieceDeck;
import framework.patterns.behavioral.memento.GameMemento;
import framework.patterns.behavioral.memento.HistoryManager;
import framework.patterns.behavioral.memento.Originator;
import framework.core.GameBoard;
import framework.core.Player;
import framework.patterns.creational.abstractFactory.GameAbstractFactory;
import framework.patterns.behavioral.command.GameCommand;
import framework.patterns.behavioral.command.MoveCommand;
import framework.patterns.behavioral.command.PassTurnCommand;
import framework.patterns.structural.flyweight.GamePiece;
import framework.patterns.structural.proxy.IGameSession;

import java.util.List;

public class GameSession implements Originator<GameMemento>, IGameSession {

    private GameBoard gameBoard;
    private List<Player> players;
    private final HistoryManager historyManager = new HistoryManager();
    private int turn = 0;
    private GamePiece lastMovedPiece;
    private Position lastFrom;
    private Position lastTo;

    public GameSession(GameAbstractFactory factory) {
        gameBoard = factory.createGameBoard();
        players = factory.createPlayers();
        List<GamePiece> gamePieces = factory.createGamePieces();
        gameBoard.setPieces(new PieceDeck(gamePieces));
        distribute(gameBoard.getPieces());
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
        if (command instanceof MoveCommand move) {
            this.lastFrom = move.getFrom();
            this.lastTo = move.getTo();
            this.lastMovedPiece = board().getPieceAt(lastFrom).orElseThrow();
        } else {
            this.lastFrom = this.lastTo = null;
            this.lastMovedPiece = null;
        }

        historyManager.backup(this);
        command.execute();
        turn = (turn + 1) % players.size();
    }

    public void undoLastCommand() {
        historyManager.undo(this);
    }

    private void distribute(PieceDeck deck) {
        int per = deck.size() / players.size();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPieces(deck.getAll().subList(i * per, (i + 1) * per));
        }
    }

    @Override
    public GameMemento saveState() {
        if (lastMovedPiece == null) return null;
        return new GameMemento(
                lastMovedPiece,
                lastFrom.clone(),
                lastTo.clone(),
                turn
        );
    }

    @Override
    public void restoreState(GameMemento memento) {
        if (memento == null || memento.piece() == null) return;
        memento.piece().setPosition(memento.previous());
        this.turn = memento.turn();
    }
}
