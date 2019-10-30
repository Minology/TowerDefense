package thegame.menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import thegame.config.Config;

public class MainMenu {
    public static Parent createContent() {
        Pane root = new Pane();

        root.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        try {
            InputStream is = Files.newInputStream(Paths.get(MenuNavigator.BACKGROUND));
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(Config.SCREEN_WIDTH);
            img.setFitHeight(Config.SCREEN_HEIGHT);
            root.getChildren().add(img);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title ("TOWER DEFENSE");
        title.setTranslateX(50);
        title.setTranslateY(200);

        MenuItem itemPlay = new MenuItem("PLAY");
        itemPlay.setOnMousePressed(event -> MenuController.startNewGame());
        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnMousePressed(event -> MenuController.exitGame());

        MenuBox vbox = new MenuBox(
                itemPlay,
                new MenuItem("LOAD"),
                itemExit);
        vbox.setTranslateX(100);
        vbox.setTranslateY(300);

        root.getChildren().addAll(title,vbox);

        return root;
    }


}