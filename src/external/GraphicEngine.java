package external;

import framework.core.GameBoard;
import framework.core.Player;
import framework.patterns.structural.flyweight.GamePiece;

import java.util.List;

/*
    Não teria como uma interface externa de um motor gráfico
    saber as classes do framework que ela vai renderizar,
    ao menos que fizesse um adaptador para cada uma delas,
    entretanto vamos abstrair essa ideia e pensar apenas na ideia do motor
 */
public interface GraphicEngine {
    void renderBoard(GameBoard board);
    void renderPlayers(List<Player> players);
    void renderPieces(List<GamePiece> pieces);
    void highlightPosition(int x, int y);
}
