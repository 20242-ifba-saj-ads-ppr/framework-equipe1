package framework.patterns.creational.prototype;

public record Position(
        int x,
        int y
) implements GamePrototype<Position> {
    @Override
    public Position clone() {
        return new Position(x,y);
    }
}
