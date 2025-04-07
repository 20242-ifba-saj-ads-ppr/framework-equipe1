package framework.core.cellType;

public enum BasicCellType implements CellType {
    NORMAL;

    @Override
    public String getName() {
        return name();
    }
}
