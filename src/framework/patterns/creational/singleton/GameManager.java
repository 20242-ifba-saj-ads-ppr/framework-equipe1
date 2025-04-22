package framework.patterns.creational.singleton;

import framework.core.GameBoard;
import framework.core.GameRegistry;
import framework.core.Player;
import framework.patterns.creational.prototype.Position;
import framework.patterns.creational.abstractFactory.GameAbstractFactory;
import framework.patterns.structural.adapter.IGraphicEngineAdapter;
import framework.patterns.structural.facade.GameSession;
import framework.patterns.structural.proxy.GameSessionProxy;
import framework.patterns.structural.proxy.IGameSession;

import java.util.Map;

public final class GameManager {
    private static final GameManager INSTANCE = new GameManager();

    private final Map<String, GameAbstractFactory> factories;
    private IGameSession currentSession;
    private IGraphicEngineAdapter graphicEngine;

    private GameManager() {
        this.factories = GameRegistry.getAll();
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public void start(String gameId, String playerId, IGraphicEngineAdapter graphicEngine) {
        GameAbstractFactory factory = factories.get(gameId);
        if (factory == null) {
            throw new IllegalArgumentException("Game not found: " + gameId);
        }

        GameSession session = new GameSession(factory);
        currentSession = new GameSessionProxy(session, playerId);
        this.graphicEngine = graphicEngine;
        graphicEngine.render(currentSession);

    }

    public void move(Position from, Position to) {
        currentSession.move(from, to);
        graphicEngine.highlighPosition(to);
    }

    public void undo() {
        currentSession.undo();
    }

    public void passTurn() {
        currentSession.passTurn();
    }

    public GameBoard board() {
        return currentSession.board();
    }

    public Player currentPlayer() {
        return currentSession.currentPlayer();
    }
}
