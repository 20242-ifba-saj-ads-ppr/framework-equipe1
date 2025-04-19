package framework.patterns.structural.proxy;

import framework.patterns.creational.prototype.Position;
import framework.patterns.structural.facade.GameSession;
import framework.core.GameBoard;
import framework.core.Player;

public class GameSessionProxy implements IGameSession {

    private final GameSession realSession;
    private final String allowedPlayerId;

    public GameSessionProxy(GameSession realSession, String allowedPlayerId) {
        this.realSession = realSession;
        this.allowedPlayerId = allowedPlayerId;
    }

    private void validateAccess() {
        if (!realSession.currentPlayer().getId().equals(allowedPlayerId)) {
            throw new IllegalStateException("Acesso negado! Você não pode fazer isso.");
        }
    }

    @Override
    public void move(Position from, Position to) {
        validateAccess();
        realSession.move(from, to);
    }

    @Override
    public void undo() {
        validateAccess();
        realSession.undo();
    }

    @Override
    public void passTurn() {
        validateAccess();
        realSession.passTurn();
    }

    @Override
    public GameBoard board() {
        return realSession.board();
    }

    @Override
    public Player currentPlayer() {
        return realSession.currentPlayer();
    }
}
