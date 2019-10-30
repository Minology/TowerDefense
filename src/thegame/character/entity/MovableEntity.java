package thegame.character.entity;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public abstract class MovableEntity extends GameEntity {
    private boolean finished;

    public MovableEntity() {}

    public MovableEntity(String path) {
        super(path);
    }

    public void createPathTransition(Pane enemyLayer, Path path, boolean orientation, double travelTime) {
        enemyLayer.getChildren().add(this.getView());
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(travelTime));
        pathTransition.setNode(this.getView());
        pathTransition.setInterpolator(Interpolator.LINEAR);
        if (orientation) {
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        }
        pathTransition.setOnFinished(e -> {
            enemyLayer.getChildren().removeAll(this.getView());
            finished = true;
        });
        pathTransition.play();
    }

    public boolean isFinished() {
        return finished;
    }
}
