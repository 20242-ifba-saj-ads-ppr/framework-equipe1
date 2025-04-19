package framework.patterns.structural.flyweight;

import framework.core.ClonePieces;
import framework.core.GameBoard;
import framework.patterns.creational.prototype.Position;
import framework.core.exceptions.InvalidMovementException;
import framework.core.BasicGamePiece;
import framework.patterns.creational.prototype.GamePrototype;

import java.util.Map;

public class GamePiece extends BasicGamePiece implements GamePrototype<GamePiece>, ClonePieces<GamePiece> {
    private final GamePieceProps props;

    public GamePiece(GamePieceProps props) {
        this.props = props;
    }

    @Override
    public void move(Position locale, GameBoard board) throws InvalidMovementException {
        if (getPosition() != null) {
            throw new InvalidMovementException("A peça ainda não foi instanciada");
        }
        if(props.moveChain().move(getPosition(), locale, board)) {
            return;
        }

        throw new InvalidMovementException("A peça" + props.type().getName() + " não pode se mover para " + locale.toString());
    }

    public GamePieceProps getProps() {
        return props;
    }

    @Override
    public GamePiece clone() {
        GamePiece clone = new GamePiece(this.props);
        if (this.getPosition() != null) {
            clone.setPosition(new Position(this.getPosition().x(), this.getPosition().y()));
        }
        return clone;
    }

    @Override
    public GamePiece cloneWithCache(Map<GamePiece, GamePiece> cache) {
        return cache.computeIfAbsent(this, GamePiece::clone);
    }
}
