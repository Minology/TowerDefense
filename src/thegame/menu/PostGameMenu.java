package thegame.menu;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import thegame.Launcher;
import thegame.config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PostGameMenu {
    public static final int POST_GAME_DELAY_IN_SECOND = 5;
    public static final String WIN_TEXT = "You've won";
    public static final String LOST_TEXT = "You've lost";

    public static Parent setPostScreen(String message) {
        Pane pane = new Pane();
        try {
            InputStream is = Files.newInputStream(Paths.get(MenuNavigator.BACKGROUND));
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(Config.SCREEN_WIDTH);
            img.setFitHeight(Config.SCREEN_HEIGHT);
            pane.getChildren().add(img);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title (message);
        title.setTranslateX(500);
        title.setTranslateY(200);

        MenuItem itemBackToMenu = new MenuItem("BACK TO MENU");
        itemBackToMenu.setOnMousePressed(event -> Launcher.setUp());
        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnMousePressed(event -> MenuController.exitGame());

        MenuBox vbox = new MenuBox(
                itemBackToMenu,
                itemExit);
        vbox.setTranslateX(500);
        vbox.setTranslateY(500);

        pane.getChildren().addAll(title, vbox);

        return pane;
    }
}
