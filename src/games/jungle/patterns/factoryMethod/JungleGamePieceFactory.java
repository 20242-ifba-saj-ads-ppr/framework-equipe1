package games.jungle.patterns.factoryMethod;

import framework.core.Player;
import framework.core.piece.GamePiece;
import framework.core.piece.PieceType;
import framework.patterns.factoryMethod.GamePieceFactory;
import games.jungle.core.pieces.*;

public class JungleGamePieceFactory implements GamePieceFactory {
    @Override
    public GamePiece createPiece(PieceType type, Player owner) {

        if(!(type instanceof games.jungle.core.PieceType)){
            throw new IllegalArgumentException("Invalid piece type: " + type);
        }

        return switch ((games.jungle.core.PieceType) type) {
            case ELEPHANT -> new Elephant(owner);
            case LION -> new Lion(owner);
            case TIGER -> new Tiger(owner);
            case LEOPARD -> new Leopard(owner);
            case DOG -> new Dog(owner);
            case WOLF -> new Wolf(owner);
            case CAT -> new Cat(owner);
            case RAT -> new Rat(owner);
            default -> throw new IllegalArgumentException("Unknown animal type: " + type);
        };
    }
}
