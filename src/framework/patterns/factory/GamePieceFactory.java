package framework.patterns.factory;

import framework.core.PieceType;
import framework.patterns.flyweight.GamePiece;
import framework.patterns.flyweight.GamePieceProps;

import java.util.List;
import java.util.Map;

public abstract class GamePieceFactory {
   protected Map<PieceType, GamePieceProps> gamePieceProMap;

   public GamePieceFactory() {
         gamePieceProMap = Map.of();
   }
   protected abstract GamePiece createGamePiece(PieceType type);
   public abstract List<GamePiece> createGamePiece(int qtd, PieceType type);
}
