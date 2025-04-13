package games.jungle.patterns.factory.flyweight;

import framework.core.PieceType;
import framework.patterns.chainOfRespo.Move;
import framework.patterns.factory.GamePieceFactory;
import framework.patterns.flyweight.GamePiece;
import framework.patterns.flyweight.GamePieceProps;
import games.jungle.core.JunglePieceType;
import games.jungle.patterns.factory.JungleMoveFactory;

import java.util.ArrayList;
import java.util.List;


public class JungleGamePieceFactory extends GamePieceFactory {
    private final JungleMoveFactory moveFactory;

    public JungleGamePieceFactory() {
        super();
        this.moveFactory = JungleMoveFactory.getInstance();
    }

    @Override
    protected GamePiece createGamePiece(PieceType type) {
        if(!(type instanceof JunglePieceType)){
            throw new IllegalArgumentException("Invalid piece type");
        }
        GamePieceProps props = getGamePieceProp((JunglePieceType) type);
        return new GamePiece(props);
    }

    @Override
    public List<GamePiece> createGamePiece(int qtd, PieceType type) {
        List<GamePiece> gamePieces = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            gamePieces.add(createGamePiece(type));
        }
        return gamePieces;
    }

    private GamePieceProps getGamePieceProp(JunglePieceType type) {
        return this.gamePieceProMap.computeIfAbsent(type, k -> {
            Move move = moveFactory.createMoveChain(type);
            return new GamePieceProps(type, move);
        });
    }
}
