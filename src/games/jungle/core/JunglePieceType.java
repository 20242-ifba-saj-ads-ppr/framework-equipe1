package games.jungle.core;

import framework.core.PieceType;

public enum JunglePieceType implements PieceType {
    ELEPHANT, LION, TIGER, LEOPARD, DOG, WOLF, CAT, MOUSE;

    @Override
    public String getName() {
        return name();
    }
}
