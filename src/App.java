import framework.core.GameBoard;
import framework.core.Player;
import framework.patterns.creational.prototype.Position;
import framework.patterns.creational.singleton.GameManager;

public class App {
    public static void main(String[] args) {
        String gameId = "Jungle";
        String playerId = "JunglePlayer1";

        GameManager manager = GameManager.getInstance();
        manager.start(gameId, playerId);
        GameBoard board = manager.board();
        Player current = manager.currentPlayer();
        System.out.println("Jogo iniciado com sucesso!");
        System.out.println("Jogador atual: " + current.getId());
        System.out.println("Tamanho do tabuleiro: " + board.getWidth() + "x" + board.getHeight());
        try {
            Position from = new Position(0, 6);
            Position to = new Position(0, 5);
            manager.move(from, to);
            System.out.println("Movimento realizado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao mover peça: " + e.getMessage());
        }
    }
}