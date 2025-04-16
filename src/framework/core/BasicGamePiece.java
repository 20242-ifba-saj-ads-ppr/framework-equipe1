package framework.core;

import framework.patterns.creational.prototype.GameBoard;

public abstract class BasicGamePiece {
    private Position position;
    private Position initialPosition;

    public abstract void move(Position locale, GameBoard board);

    public Position getPosition() {
        return position;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setPosition(Position position) {
        if(this.position == null && this.initialPosition == null) {
            this.initialPosition = position;
        }
        this.position = position;
    }


}
