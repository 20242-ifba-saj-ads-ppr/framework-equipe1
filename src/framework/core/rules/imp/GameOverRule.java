package framework.core.rules.imp;

import framework.core.Game;
import framework.core.Player;
import framework.core.rules.Rule;

public interface GameOverRule extends Rule {
    boolean checkGameOver(Game game);
    Player getWinner(Game game);
}
