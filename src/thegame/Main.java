package thegame;

import javafx.application.Application;
import javafx.stage.Stage;
import thegame.engine.GameManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameManager.setGameStage(primaryStage);
        Launcher.setUp();
    }

    public static void main(String[] args) {
        launch(args);
    }
}