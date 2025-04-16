package framework.patterns.structural.proxy;
import framework.core.Position;
import framework.patterns.creational.prototype.GameBoard;
import framework.patterns.creational.prototype.Player;

public interface IGameSession {
    void move(Position from, Position to);
    void undo();
    void passTurn();
    GameBoard board();
    Player currentPlayer();
}
