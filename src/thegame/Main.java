package thegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import thegame.menu.Menu;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(Menu.createContent());
        primaryStage.setTitle("TOWER DEFENSE");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        GameStage.setGameStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}