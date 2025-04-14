package games.jungle.patterns.abstractFactory;

import framework.core.*;
import framework.patterns.abstractFactory.GameAbstractFactory;
import framework.patterns.builder.BoardBuilder;
import framework.patterns.builder.GameBoardDirector;
import framework.patterns.flyweight.GamePiece;
import games.jungle.core.JunglePieceType;
import games.jungle.patterns.builder.JungleBoardBuilder;
import games.jungle.patterns.factory.flyweight.JungleGamePieceFactory;

import java.util.List;
import java.util.Map;

@GameId("Jungle")
public class JungleAbstractFactory implements GameAbstractFactory {

    private final JungleGamePieceFactory gamePieceFactory;
    private GameBoardDirector gameBoardDirector;


    static {
        GameRegistry.register("Jungle", new JungleAbstractFactory());
    }

    public JungleAbstractFactory() {
        this.gamePieceFactory = new JungleGamePieceFactory();
    }

    @Override
    public List<GamePiece> createGamePieces() {
        var gamePiecesRequest = Map.of(
                JunglePieceType.CAT, 2,
                JunglePieceType.DOG, 2,
                JunglePieceType.WOLF, 2,
                JunglePieceType.LEOPARD, 2,
                JunglePieceType.TIGER, 2,
                JunglePieceType.ELEPHANT, 2,
                JunglePieceType.MOUSE, 2,
                JunglePieceType.LION,2
        );
        return gamePiecesRequest.entrySet().stream()
                .map(entry -> gamePieceFactory.createGamePiece(entry.getValue(), entry.getKey()))
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public GameBoard createGameBoard() {
        if(gameBoardDirector == null) {
            gameBoardDirector = new GameBoardDirector(new JungleBoardBuilder());
        }
        return gameBoardDirector.construct(7, 9);
    }

    @Override
    public List<Player> createPlayers() {
        return List.of(new Player("JunglePlayer1"), new Player("JunglePlayer2"));
    }
}
