package framework.patterns.structural.adapter;

import framework.patterns.creational.prototype.Position;
import framework.patterns.structural.proxy.IGameSession;

public interface IGraphicEngineAdapter {
    void render(IGameSession session);
    void highlighPosition(Position position);
}
