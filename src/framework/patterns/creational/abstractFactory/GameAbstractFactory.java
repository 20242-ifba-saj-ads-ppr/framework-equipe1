package framework.patterns.creational.abstractFactory;

import framework.core.GameBoard;
import framework.core.Player;
import framework.patterns.structural.flyweight.GamePiece;

import java.util.List;

public interface GameAbstractFactory {
    List<GamePiece> createGamePieces();
    GameBoard createGameBoard();
    List<Player> createPlayers();
}

