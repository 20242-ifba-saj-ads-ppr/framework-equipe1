package games.patterns.abstractFactory;

import framework.core.Board;
import framework.core.Player;
import framework.core.Position;
import framework.core.piece.GamePiece;
import games.core.PieceType;
import framework.patterns.abstractFactory.GameFactory;
import framework.patterns.builder.BoardBuilder;
import framework.patterns.factoryMethod.GamePieceFactory;
import games.core.CellType;

import java.util.ArrayList;
import java.util.List;

public class JungleGameFactory implements GameFactory {
    @Override
    public Board createBoard() {
        // Criar um tabuleiro personalizado para o jogo da Selva
        return new BoardBuilder()
                .setDimensions(7, 9)
                .setCellTypes(CellType.NORMAL)
                .addSpecificCellType(new Position(1, 2), CellType.WATER)
                .addSpecificCellType(new Position(2, 2), CellType.WATER)
                .addSpecificCellType(new Position(4, 2), CellType.WATER)
                .addSpecificCellType(new Position(5, 2), CellType.WATER)
                .addSpecificCellType(new Position(1, 3), CellType.WATER)
                .addSpecificCellType(new Position(2, 3), CellType.WATER)
                .addSpecificCellType(new Position(4, 3), CellType.WATER)
                .addSpecificCellType(new Position(5, 3), CellType.WATER)
                .addSpecificCellType(new Position(1, 4), CellType.WATER)
                .addSpecificCellType(new Position(2, 4), CellType.WATER)
                .addSpecificCellType(new Position(4, 4), CellType.WATER)
                .addSpecificCellType(new Position(5, 4), CellType.WATER)
                .addSpecificCellType(new Position(1, 5), CellType.WATER)
                .addSpecificCellType(new Position(2, 5), CellType.WATER)
                .addSpecificCellType(new Position(4, 5), CellType.WATER)
                .addSpecificCellType(new Position(5, 5), CellType.WATER)
                .addSpecificCellType(new Position(2, 0), CellType.TRAP)
                .addSpecificCellType(new Position(4, 0), CellType.TRAP)
                .addSpecificCellType(new Position(3, 1), CellType.TRAP)
                .addSpecificCellType(new Position(2, 8), CellType.TRAP)
                .addSpecificCellType(new Position(4, 8), CellType.TRAP)
                .addSpecificCellType(new Position(3, 7), CellType.TRAP)
                .addSpecificCellType(new Position(3, 0), CellType.DEN)
                .addSpecificCellType(new Position(3, 8), CellType.DEN)
                .build();
    }

    @Override
    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("1", "Player 1"));
        players.add(new Player("2", "Player 2"));
        return players;
    }

    @Override
    public List<GamePiece> createPieces(Player player) {
        List<GamePiece> pieces = new ArrayList<>();
        GamePieceFactory pieceFactory = new JungleGamePieceFactory();

        // Criar peças com base no jogador
        if (player.getId().equals("1")) {
            // Peças para o jogador 1 (posições iniciais)
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.ELEPHANT, player, new Position(0, 0)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.LION, player, new Position(6, 0)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.TIGER, player, new Position(0, 8)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.LEOPARD, player, new Position(4, 2)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.DOG, player, new Position(5, 1)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.WOLF, player, new Position(1, 1)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.CAT, player, new Position(5, 3)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.RAT, player, new Position(0, 2)));
        } else {
            // Peças para o jogador 2 (posições iniciais)
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.ELEPHANT, player, new Position(6, 8)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.LION, player, new Position(0, 8)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.TIGER, player, new Position(6, 0)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.LEOPARD, player, new Position(2, 6)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.DOG, player, new Position(1, 7)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.WOLF, player, new Position(5, 7)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.CAT, player, new Position(1, 5)));
            pieces.add(createAndPlacePiece(pieceFactory, PieceType.RAT, player, new Position(6, 6)));
        }

        return pieces;
    }


    private GamePiece createAndPlacePiece(GamePieceFactory factory, PieceType type, Player owner, Position position) {
        GamePiece piece = factory.createPiece(type, owner);
        owner.addPieceWithPosition(piece, position);
        return piece;
    }

    @Override
    public RuleEngine createRuleEngine() {
        RuleEngine engine = new RuleEngine();

        // Adicionar regras básicas
        engine.addRule(new TurnBasedRule());
        engine.addRule(new AdjacentMovementRule());

        // Adicionar regras específicas do jogo da selva
        CompositeRule captureRules = new CompositeRule();
        captureRules.addRule(new RankBasedCaptureRule());
        captureRules.addRule(new SpecialCaptureRule()); // Para exceções como Rato vs Elefante

        engine.addRule(captureRules);
        engine.addRule(new WaterMovementRule());
        engine.addRule(new TrapRule());
        engine.addRule(new DenRule());

        // Regra de fim de jogo
        engine.addRule(new JungleGameOverRule());

        return engine;
    }

}
