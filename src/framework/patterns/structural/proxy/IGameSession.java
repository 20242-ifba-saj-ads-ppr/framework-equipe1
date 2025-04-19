package framework.patterns.structural.proxy;
import framework.patterns.creational.prototype.Position;
import framework.core.GameBoard;
import framework.core.Player;

public interface IGameSession {
    void move(Position from, Position to);
    void undo();
    void passTurn();
    GameBoard board();
    Player currentPlayer();
}
