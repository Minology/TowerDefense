package thegame.menu;

import thegame.engine.GameManager;

public class MenuController {
    static void startNewGame() {
        try {
            GameManager gameStage = new GameManager();
            gameStage.initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void exitGame() {
        System.exit(1);
    }
}
