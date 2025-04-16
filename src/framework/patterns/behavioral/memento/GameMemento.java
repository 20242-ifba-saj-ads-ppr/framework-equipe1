package framework.patterns.behavioral.memento;

import framework.patterns.creational.prototype.GameBoard;
import framework.patterns.creational.prototype.Player;

import java.util.List;

public record GameMemento(
        GameBoard boardSnapshot,
        List<Player> playerSnapshot,
        int turn
) {

}
