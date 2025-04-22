package external;

import framework.core.GameBoard;
import framework.core.Player;
import framework.patterns.creational.prototype.Position;
import framework.patterns.structural.flyweight.GamePiece;

import java.util.List;

public class SuperConsoleEngine implements GraphicEngine {
    @Override
    public void renderBoard(GameBoard board) {
        System.out.println("=== Tabuleiro ===");
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                System.out.print("[" + board.getCell(new Position(x, y)).getType().toString().charAt(0) + "]");
            }
            System.out.println();
        }
    }

    @Override
    public void renderPlayers(List<Player> players) {
        System.out.println("=== Jogadores ===");
        for (Player player : players) {
            System.out.println(player.getId());
        }
    }

    @Override
    public void renderPieces(List<GamePiece> pieces) {
        System.out.println("=== Peças ===");
        for (GamePiece piece : pieces) {
            System.out.printf("Tipo: %s, Pos: (%d,%d)%n",
                    piece.getProps().type().getName(),
                    piece.getPosition().x(),
                    piece.getPosition().y());
        }
    }

    @Override
    public void highlightPosition(int x, int y) {
        System.out.printf("Posição destacada: (%d,%d)%n", x, y);
    }
}
