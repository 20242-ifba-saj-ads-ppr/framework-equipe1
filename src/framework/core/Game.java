package framework.core;

import framework.core.cell.Cell;
import framework.core.piece.GamePiece;
import framework.patterns.composite.CompositeRule;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private CompositeRule ruleEngine;
    private List<Move> moveHistory;
    private boolean gameOver = false;
    private Player winner = null;

    public Game(Board board, List<Player> players, CompositeRule ruleEngine) {
        this.board = board;
        this.players = players;
        this.ruleEngine = ruleEngine;
        this.moveHistory = new ArrayList<>();

        if (!players.isEmpty()) {
            this.currentPlayer = players.get(0);
        }

        for (Player player : players) {
            for (GamePiece piece : player.getPieces()) {
                Position position = player.getPiecePositions().get(piece);
                if (position != null) {
                    Cell cell = board.getCell(position);
                    if (cell != null) {
                        cell.setOccupant(piece);
                    }
                }
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean makeMove(Move move) {
        if (gameOver) {
            return false;
        }

        if (!move.validate(ruleEngine, board)) {
            return false;
        }

        if (!move.execute(board)) {
            return false;
        }


        moveHistory.add(move);

        nextPlayer();

        return true;
    }

    private void nextPlayer() {
        if (players.size() <= 1) {
            return;
        }

        int currentIndex = players.indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % players.size();
        currentPlayer = players.get(nextIndex);
    }

    public boolean undoLastMove() {
        if (moveHistory.isEmpty()) {
            return false;
        }

        Move lastMove = moveHistory.remove(moveHistory.size() - 1);
        boolean undoSuccessful = lastMove.undo(board);

        if (undoSuccessful) {
            gameOver = false;
            winner = null;


            previousPlayer();
        }

        return undoSuccessful;
    }

    private void previousPlayer() {
        if (players.size() <= 1) {
            return;
        }

        int currentIndex = players.indexOf(currentPlayer);
        int prevIndex = (currentIndex - 1 + players.size()) % players.size();
        currentPlayer = players.get(prevIndex);
    }

    public List<Move> getLegalMoves(Player player) {
        List<Move> legalMoves = new ArrayList<>();

        for (GamePiece piece : player.getPieces()) {
            Position piecePosition = null;

            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    Position pos = new Position(x, y);
                    Cell cell = board.getCell(pos);
                    if (cell != null && cell.getOccupant() == piece) {
                        piecePosition = pos;
                        break;
                    }
                }
                if (piecePosition != null) break;
            }

            if (piecePosition != null) {
                int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

                for (int[] dir : directions) {
                    int newX = piecePosition.x() + dir[0];
                    int newY = piecePosition.y() + dir[1];

                    if (newX >= 0 && newX < board.getWidth() && newY >= 0 && newY < board.getHeight()) {
                        Position newPos = new Position(newX, newY);
                        Move move = new Move(piecePosition, newPos, piece);

                        if (move.validate(ruleEngine, board)) {
                            legalMoves.add(move);
                        }
                    }
                }
            }
        }

        return legalMoves;
    }
}
