package framework.patterns.flyweight;

import framework.core.PieceType;
import framework.patterns.chainOfRespo.Move;


public record GamePieceProps(
        PieceType type,
        Move moveChain
) {
}
