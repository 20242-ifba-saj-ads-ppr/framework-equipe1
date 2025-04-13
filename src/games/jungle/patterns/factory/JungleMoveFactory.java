package games.jungle.patterns.factory;

import framework.patterns.chainOfRespo.MoveHandler;
import framework.patterns.chainOfRespo.Move;
import games.jungle.core.JunglePieceType;
import games.jungle.patterns.chainOfRespo.LeapOverRiver;
import games.jungle.patterns.chainOfRespo.Range;
import games.jungle.patterns.chainOfRespo.TerritoryRestriction;
import games.jungle.patterns.chainOfRespo.WaterBlock;

import java.util.Map;
import java.util.function.Supplier;

public class JungleMoveFactory extends AbstractJungleMoveFactory {

    private static Map<String, Supplier<MoveHandler>> moveStrategyMap;

    private static JungleMoveFactory instance;

    public static JungleMoveFactory getInstance() {
        if (instance == null) {
            instance = new JungleMoveFactory();
        }
        return instance;
    }

    private JungleMoveFactory() {
        moveStrategyMap = Map.of(
                "1", TerritoryRestriction::new,
                "2", WaterBlock::new,
                "3", LeapOverRiver::new,
                "4", Range::new
        );
    }

    @Override
    public Move createMoveChain(JunglePieceType type) {
        return switch (type) {
            case  MOUSE-> chain(
                    moveStrategyMap.get("1").get(),
                    moveStrategyMap.get("4").get()
            );

            case LION, TIGER -> chain(
                    moveStrategyMap.get("1").get(),
                    moveStrategyMap.get("2").get(),
                    moveStrategyMap.get("3").get(),
                    moveStrategyMap.get("4").get()
            );

            case ELEPHANT, LEOPARD, WOLF, DOG, CAT -> chain(
                    moveStrategyMap.get("1").get(),
                    moveStrategyMap.get("2").get(),
                    moveStrategyMap.get("4").get()
            );
        };
    }
}
