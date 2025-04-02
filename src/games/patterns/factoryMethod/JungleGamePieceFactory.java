package games.patterns.factoryMethod;

import framework.core.Player;
import framework.core.piece.GamePiece;
import games.core.PieceType;
import framework.patterns.factoryMethod.GamePieceFactory;

public class JungleGamePieceFactory implements GamePieceFactory {
    @Override
    public GamePiece createPiece(PieceType type, Player owner) {
        switch (type) {
            case ELEPHANT:
                return new Elep(owner);
            case LION:
                return new Lion(owner);
            case TIGER:
                return new Tiger(owner);
            case LEOPARD:
                return new Leopard(owner);
            case DOG:
                return new Dog(owner);
            case WOLF:
                return new Wolf(owner);
            case CAT:
                return new Cat(owner);
            case RAT:
                return new Rat(owner);
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}
