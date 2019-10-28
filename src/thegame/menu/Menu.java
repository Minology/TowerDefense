package thegame.menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.*;
import thegame.Config;

public class Menu {
    public static Parent createContent() {
        Pane root = new Pane();

        root.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        try {
            InputStream is = Files.newInputStream(Paths.get("src/thegame/res/menu/menubackground.jpeg"));
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

    private static class Title extends StackPane{
        public Title(String name) {
            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 70));

            setAlignment(Pos.CENTER);
            getChildren().addAll(text);
        }
    }

    private static class MenuBox extends VBox{
        public MenuBox(MenuItem...items) {
            getChildren().add(createSeperator());

            for(MenuItem item : items) {
                getChildren().addAll(item, createSeperator());
            }
        }

        private Line createSeperator() {
            Line sep = new Line();
            sep.setEndX(210);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    private static class MenuItem extends StackPane{
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.DARKBLUE),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKBLUE));

            Rectangle bg = new Rectangle(200,30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });
            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });
            setOnMousePressed(event -> bg.setFill(Color.DARKVIOLET));
            setOnMouseReleased(event -> bg.setFill(gradient));
        }
    }
}