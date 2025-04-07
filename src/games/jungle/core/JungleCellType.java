package games.jungle.core;

import framework.core.cellType.CellType;

public enum JungleCellType implements CellType {
    WATER, TRAP, DEN;

    @Override
    public String getName() {
        return name();
    }
}
