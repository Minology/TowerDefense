package thegame.menu;

import thegame.GameStage;

/**
 * Created by Minology on 09:49 CH
 */

public class MenuController {
    static void startNewGame() {
        try{
            GameStage gameStage = new GameStage();
            gameStage.initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void exitGame() {
        System.exit(1);
    }
}
