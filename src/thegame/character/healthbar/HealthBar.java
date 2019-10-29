package thegame.character.healthbar;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import thegame.character.entity.MovableEntity;

public class HealthBar extends MovableEntity {
    private Rectangle outerHealthRect;
    private Rectangle innerHealthRect;
    private Pane pane;

    public HealthBar() {
        double height = 10;
        double outerWidth = 60;
        double innerWidth = 60;
        double x = 0.0;
        double y = 0.0;
        outerHealthRect = new Rectangle(x, y, outerWidth, height);
        outerHealthRect.setStroke(Color.BLACK);
        outerHealthRect.setStrokeWidth(2);
        outerHealthRect.setStrokeType( StrokeType.OUTSIDE);
        outerHealthRect.setFill(Color.RED);
        innerHealthRect = new Rectangle(x, y, innerWidth, height);
        innerHealthRect.setStrokeType(StrokeType.OUTSIDE);
        innerHealthRect.setFill(Color.LIMEGREEN);
        pane = new Pane();
        pane.getChildren().addAll(outerHealthRect, innerHealthRect);
        pane.setLayoutY(-30);
    }

    public void setValue(double value) {
        innerHealthRect.setWidth( outerHealthRect.getWidth() * value);
    }

    public Node getView() {
        return pane;
    }
}