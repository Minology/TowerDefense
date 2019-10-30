package thegame.character.entity;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Minology on 08:33 CH
 */
public abstract class GameEntity {
    private Point2D coords;
    protected ImageView view;

    public GameEntity() {}

    public GameEntity(String path) {
        try {
            InputStream is = Files.newInputStream(Paths.get(path));
            view = new ImageView(new Image(is));
            is.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Point2D getCurrentCoordinates() {
        return new Point2D(view.getTranslateX(), view.getTranslateY());
    }

    public int getCurrentX() {
        return (int) view.getTranslateX();
    }

    public int getCurrentY() {
        return (int) view.getTranslateY();
    }

    public Node getView() {
        return view;
    }

    public void setView(int x, int y) {
        view.setX(x);
        view.setY(y);
    }

    public void setTranslate(int x, int y) {
        view.setTranslateX(x);
        view.setTranslateY(y);
    }

    public void setCoords(double x, double y) {
        coords = new Point2D(x, y);
    }

    public Point2D getCoords() {
        return coords;
    }
}
