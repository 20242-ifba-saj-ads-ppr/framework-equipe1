package framework.patterns.structural.flyweight;

import framework.core.PieceType;
import framework.patterns.behavioral.chainOfRespo.Move;


public record GamePieceProps(
        PieceType type,
        Move moveChain
) {
}
