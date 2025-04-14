package framework.core;

import framework.patterns.abstractFactory.GameAbstractFactory;

import java.util.HashMap;
import java.util.Map;

public class GameRegistry {
    private static final Map<String, GameAbstractFactory> registry = new HashMap<>();

    public static void register(String gameId, GameAbstractFactory factory) {
        registry.put(gameId, factory);
    }

    public static GameAbstractFactory get(String gameId) {
        return registry.get(gameId);
    }

    public static Map<String, GameAbstractFactory> getAll() {
        return registry;
    }
}
