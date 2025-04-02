package games.jungle.core.pieces;

import framework.core.Board;
import framework.core.Player;
import framework.core.Position;
import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import games.jungle.core.CellType;

public abstract class Animal implements GamePiece {
    protected Player owner;
    protected int rank;
    protected Position initialPosition;

    public Animal(Player owner, int rank) {
        this.owner = owner;
        this.rank = rank;
        this.initialPosition = null;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position position) {
        if (this.initialPosition == null) {
            this.initialPosition = position;
        }
    }

    @Override
    public boolean canMoveTo(Board board, Position from, Position to) {
        Cell targetCell = board.getCell(to);


        if (targetCell == null) {
            return false;
        }

        if (targetCell.isOccupied() && targetCell.getOccupant().getOwner().equals(owner)) {
            return false;
        }

        if (targetCell.getType() == CellType.WATER && !(this instanceof Rat)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canCapture(GamePiece target) {
        if (target.getOwner().equals(owner)) {
            return false;
        }

        if (target instanceof Animal) {
            Animal animal = (Animal) target;

            Board board = framework.patterns.singleton.GameManager.getInstance().getCurrentGame().getBoard();
            Position targetPos = null;


            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    Position pos = new Position(x, y);
                    Cell cell = board.getCell(pos);
                    if (cell != null && cell.getOccupant() == target) {
                        targetPos = pos;
                        break;
                    }
                }
                if (targetPos != null) break;
            }

            if (targetPos != null) {
                Cell targetCell = board.getCell(targetPos);
                if (targetCell.getType() == CellType.TRAP) {
                    return true;
                }
            }

            return this.rank >= animal.rank;
        }

        return false;
    }
}