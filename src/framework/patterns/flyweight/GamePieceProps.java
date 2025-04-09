package framework.patterns.flyweight;

import framework.core.PieceType;
import framework.patterns.strategy.MoveStrategy;

import java.util.List;

public record GamePieceProps(
        PieceType type,
        List<MoveStrategy> moveStrategies
) {
}
