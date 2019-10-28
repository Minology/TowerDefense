package thegame.character.tower;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import thegame.Config;
import thegame.MusicPlayer;
import thegame.character.GameEntity;
import thegame.character.Projectile;
import thegame.character.enemy.Enemy;

import java.util.ArrayList;

/**
 * Created by Minology on 06:07 CH
 */
public abstract class Tower extends GameEntity {
    private int range;
    private int damage;
    private double speed;
    private Color projectileColor;
    private int projectileSize;
    private long lastShootNanoTime = 0;
    private ArrayList<Projectile> projectiles;
    private MusicPlayer musicPlayer;
    private int rotateRadius;
    public Tower() {}
    public Tower(int x, int y) {
        super(x, y);
    }

    public Tower(int range, int damage, double speed, int x, int y, int centerX, int centerY,
                 int psize, Color color, int radius, String imagePath, String effect) {
        super(imagePath);
        musicPlayer = new MusicPlayer(effect, false);
        this.coords = new Point2D(x + Config.TILE_SIZE / 2.0, y + Config.TILE_SIZE / 2.0);
        this.range = range;
        this.damage = damage;
        this.speed = speed;
        this.projectileColor = color;
        this.projectileSize = psize;
        this.rotateRadius = radius;
        this.projectiles = new ArrayList<>();
        this.view.setX(x + centerX);
        this.view.setY(y + centerY);
    }

    private double distanceTo(Enemy enemy) {
        return this.coords.distance(enemy.getCurrentCoordinates());
    }

    public boolean canHitEnemy(Enemy enemy) {
        return distanceTo(enemy) < range;
    }

    public void setLastShootTime(long nanoTime) {
        lastShootNanoTime = nanoTime;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void createProjectile(Enemy enemy, Pane projectileLayer) {
        Circle circle = new Circle(projectileSize, projectileColor);
        circle.setCenterX(this.getCurrentX());
        circle.setCenterY(this.getCurrentY());
        projectileLayer.getChildren().add(circle);
        double angle = Math.toRadians(90 - getAngle(enemy, this.coords));
        double x = Math.cos(angle) * this.rotateRadius + coords.getX();
        double y = -Math.sin(angle) * this.rotateRadius + coords.getY();

        Projectile projectile1 = new Projectile(enemy, this.damage, circle, x, y);
        pointTowardsEnemy(enemy);
        musicPlayer.play();
        projectiles.add(projectile1);
    }

    public boolean cooledDown(long currentNanoTime) {
        return (currentNanoTime - lastShootNanoTime) / 1000000000.0 >= 1.0 / speed;
    }

    private double getAngle(Enemy enemy, Point2D center) {
        double enemyX = enemy.getView().getTranslateX() - center.getX();
        double enemyY = center.getY() - enemy.getView().getTranslateY();
        return  (360 + Math.toDegrees(Math.atan2(enemyX, enemyY) - Math.atan2(0, 1))) % 360;
        //return point2D1.angle;
    }

    private void pointTowardsEnemy(Enemy enemy) {
        Rotate rotate = new Rotate(getAngle(enemy, this.coords), coords.getX(), coords.getY());
        view.getTransforms().clear();
        view.getTransforms().add(rotate);
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
}
