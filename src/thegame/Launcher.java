package thegame;

import javafx.scene.Scene;
import javafx.stage.Stage;
import thegame.engine.GameManager;
import thegame.menu.MainMenu;

public class Launcher {
    public static void setUp() {
        Scene scene = new Scene(MainMenu.createContent());
        Stage stage = GameManager.getGameStage();
        stage.setTitle("TOWER DEFENSE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
