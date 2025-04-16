package games.jungle.patterns;

import framework.core.Cell;
import framework.patterns.creational.prototype.GameBoard;
import framework.core.Position;
import framework.patterns.structural.flyweight.GamePiece;
import games.jungle.core.JungleCellType;
import games.jungle.core.JunglePieceType;

public class JungleBoard extends GameBoard {
    public JungleBoard(int width, int height) {
        super(width, height);
    }


    public boolean hasLeapPath(Position from, Position to) {
        if (from.x() != to.x() && from.y() != to.y()) return false;

        int dx = Integer.compare(to.x(), from.x());
        int dy = Integer.compare(to.y(), from.y());

        int x = from.x() + dx;
        int y = from.y() + dy;

        while (x != to.x() || y != to.y()) {
            Position pos = new Position(x, y);
            Cell cell = getCell(pos);

            if (cell == null || cell.getType() != JungleCellType.WATER) return false;
            if (hasRatAt(pos)) return false;

            x += dx;
            y += dy;
        }

        return true;
    }


    private boolean hasRatAt(Position pos) {
        GamePiece piece = getPieces().stream().filter(p -> p.getPosition().equals(pos)).findFirst().orElse(null);
        return piece != null && piece.getProps().type().equals(JunglePieceType.MOUSE);
    }


}
