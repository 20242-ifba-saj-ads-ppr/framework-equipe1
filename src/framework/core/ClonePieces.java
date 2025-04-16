package framework.core;

import framework.patterns.structural.flyweight.GamePiece;

import java.util.Map;

public interface ClonePieces<T> {
    T cloneWithCache(Map<GamePiece, GamePiece> cache);
}
