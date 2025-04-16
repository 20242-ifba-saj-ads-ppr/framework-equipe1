package framework.patterns.creational.abstractFactory;

import framework.patterns.creational.prototype.GameBoard;
import framework.patterns.creational.prototype.Player;
import framework.patterns.structural.flyweight.GamePiece;

import java.util.List;

public interface GameAbstractFactory {
    List<GamePiece> createGamePieces();
    GameBoard createGameBoard();
    List<Player> createPlayers();
}

