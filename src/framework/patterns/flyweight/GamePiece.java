package framework.patterns.flyweight;

import framework.core.GameBoard;
import framework.core.Position;
import framework.core.exceptions.InvalidMovementException;
import framework.patterns.BasicGamePiece;

public class GamePiece extends BasicGamePiece {
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
}
