package framework.patterns.singleton;

import framework.core.Game;

public class GameManager {
    private static GameManager instance;
    private Game currentGame;

    private GameManager() {

    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }


}

