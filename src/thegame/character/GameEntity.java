package thegame.character;

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
    protected Point2D coords;
    private Image image;
    protected ImageView view;

    public GameEntity() {}
    public GameEntity(int x, int y) {
        coords = new Point2D(x, y);
    }

    public GameEntity(String path) {
        try {
            InputStream is = Files.newInputStream(Paths.get(path));
            image = new Image(is);
            view = new ImageView(image);
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

    public Node getView(){
        return view;
    }

    public void setView(int x, int y) {
        view.setTranslateX(-50);
        view.setTranslateY(-50);
        view.setX(x);
        view.setY(y);
    }
}
