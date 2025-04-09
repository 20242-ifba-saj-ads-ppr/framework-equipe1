package games.jungle.patterns;

import framework.core.PieceType;
import framework.patterns.chainOfRespo.MoveHandler;
import framework.patterns.strategy.MoveStrategy;
import games.jungle.core.JunglePieceType;
import games.jungle.patterns.chainableMoveStrategies.LeapOverRiverStrategy;
import games.jungle.patterns.chainableMoveStrategies.RangeStrategy;
import games.jungle.patterns.chainableMoveStrategies.TerritoryRestrictionStrategy;
import games.jungle.patterns.chainableMoveStrategies.WaterBlockStrategy;

import java.util.Map;

public class JungleMoveStrategyFactory {

    private static Map<String, MoveHandler> moveStrategyMap;

    public JungleMoveStrategyFactory() {
        moveStrategyMap = Map.of(
                "1", new TerritoryRestrictionStrategy(),
                "2", new WaterBlockStrategy(),
                "3", new LeapOverRiverStrategy(),
                "4", new RangeStrategy()
        );
    }

    public MoveStrategy createStrategy(JunglePieceType type) {
        return switch (type) {
            case  MOUSE-> chain(
                    moveStrategyMap.get("1"),
                    moveStrategyMap.get("4")
            );

            case LION, TIGER -> chain(
                    moveStrategyMap.get("1"),
                    moveStrategyMap.get("2"),
                    moveStrategyMap.get("3"),
                    moveStrategyMap.get("4")
            );

            case ELEPHANT, LEOPARD, WOLF, DOG, CAT -> chain(
                    moveStrategyMap.get("1"),
                    moveStrategyMap.get("2"),
                    moveStrategyMap.get("4")
            );
        };
    }

    private MoveStrategy chain(MoveHandler... strategies) {
        for (int i = 0; i < strategies.length - 1; i++) {
            strategies[i].setNext(strategies[i + 1]);
        }
        return strategies[0];
    }
}
