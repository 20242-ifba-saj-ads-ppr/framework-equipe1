package framework.patterns.factory;

import framework.core.PieceType;
import framework.patterns.flyweight.GamePiece;

import java.util.List;

public abstract class GamePieceFactory {
   protected abstract GamePiece createGamePiece(PieceType type);
   public abstract List<GamePiece> createGamePiece(int qtd, PieceType type);
}
