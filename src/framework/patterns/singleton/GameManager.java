package framework.patterns.singleton;

import framework.core.GameBoard;
import framework.core.GameRegistry;
import framework.core.Player;
import framework.core.Position;
import framework.patterns.abstractFactory.GameAbstractFactory;
import framework.patterns.command.GameCommand;
import framework.patterns.command.MoveCommand;
import framework.patterns.facade.GameSession;

import java.util.Map;

public final class GameManager {
    private static final GameManager INSTANCE = new GameManager();

    private final Map<String, GameAbstractFactory> factories;
    private GameSession currentSession;

    private GameManager() {
        this.factories = GameRegistry.getAll();
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public void start(String gameId) {
        GameAbstractFactory factory = factories.get(gameId);
        if (factory == null) {
            throw new IllegalArgumentException("Game not founded " + gameId);
        }
        currentSession = new GameSession(factory);
    }

    public void move(Position from, Position to) {
        GameCommand command = new MoveCommand(currentSession.gameBoard(), from, to);
        currentSession.executeCommand(command);
    }

    public void undo() {
        currentSession.undoLastCommand();
    }

    public GameBoard board() {
        return currentSession.gameBoard();
    }

    public Player currentPlayer() {
        return currentSession.currentPlayer();
    }
}
