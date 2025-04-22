package framework.patterns.structural.adapter;

import external.GraphicEngine;
import framework.core.GameBoard;
import framework.core.Player;
import framework.patterns.creational.prototype.Position;
import framework.patterns.structural.flyweight.GamePiece;
import framework.patterns.structural.proxy.IGameSession;

import java.util.List;

public class GraphicEngineImp implements IGraphicEngineAdapter {
    private final GraphicEngine graphicEngine;

    public GraphicEngineImp(GraphicEngine graphicEngine) {
        this.graphicEngine = graphicEngine;
    }

    @Override
    public void render(IGameSession session) {
        GameBoard board = session.board();
        List<Player> players = List.of(session.currentPlayer());
        List<GamePiece> pieces = board.getPieces().getAll();

        graphicEngine.renderBoard(board);
        graphicEngine.renderPlayers(players);
        graphicEngine.renderPieces(pieces);
    }

    @Override
    public void highlighPosition(Position position) {
        if (position != null) {
            graphicEngine.highlightPosition(position.x(), position.y());
        }
    }
}
