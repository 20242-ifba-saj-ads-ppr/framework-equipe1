package games.jungle.patterns.abstractFactory;

import framework.core.GameBoard;
import framework.core.PieceType;
import framework.core.Player;
import framework.patterns.abstractFactory.GameAbstractFactory;
import framework.patterns.builder.BoardBuilder;
import framework.patterns.builder.GameBoardDirector;
import framework.patterns.flyweight.GamePiece;
import games.jungle.patterns.factory.flyweight.JungleGamePieceFactory;

import java.util.List;
import java.util.Map;

public class JungleAbstractFactory implements GameAbstractFactory {

    private final JungleGamePieceFactory gamePieceFactory;
    private GameBoardDirector gameBoardDirector;

    public JungleAbstractFactory() {
        this.gamePieceFactory = new JungleGamePieceFactory();
    }

    @Override
    public List<GamePiece> createGamePieces(Map<PieceType, Integer> gamePiecesRequest) {
        return gamePiecesRequest.entrySet().stream()
                .map(entry -> gamePieceFactory.createGamePiece(entry.getValue(), entry.getKey()))
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public GameBoard createGameBoard(BoardBuilder boardBuilder) {
        if(gameBoardDirector == null) {
            gameBoardDirector = new GameBoardDirector(boardBuilder);
        }
        return gameBoardDirector.construct(7, 9);
    }

    @Override
    public List<Player> createPlayers() {
        return List.of(new Player("JunglePlayer1"), new Player("JunglePlayer2"));
    }
}
