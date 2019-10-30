package thegame.character.tower;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import thegame.config.Config;
import thegame.service.MusicPlayer;
import thegame.character.entity.GameEntity;
import thegame.character.projectile.Projectile;
import thegame.character.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower extends GameEntity {
    private int range;
    private int damage;
    private double speed;
    private Color projectileColor;
    private int projectileSize;
    private long lastShootNanoTime;
    private ArrayList<Projectile> projectiles;
    private MusicPlayer musicPlayer;
    private int rotateRadius;
    private TowerBase towerBase;
    private int cost;

    public Tower(int range, int damage, double speed, int cost, int x, int y, int centerX, int centerY,
                 int psize, Color color, int radius, String imagePath, String effect) {
        super(imagePath);
        musicPlayer = new MusicPlayer(effect, false);
        this.setCoords(x + Config.TILE_SIZE / 2.0, y + Config.TILE_SIZE / 2.0);
        this.range = range;
        this.damage = damage;
        this.speed = speed;
        this.cost = cost;
        this.projectileColor = color;
        this.projectileSize = psize;
        this.rotateRadius = radius;
        this.projectiles = new ArrayList<>();
        this.towerBase = new TowerBase(x, y);
        this.towerBase.setView(x, y);
        this.view.setX(x + centerX);
        this.view.setY(y + centerY);
    }

    private double distanceTo(Enemy enemy) {
        return this.getCoords().distance(enemy.getCurrentCoordinates());
    }

    public boolean canHitEnemy(Enemy enemy) {
        return distanceTo(enemy) <= range;
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
        double angle = Math.toRadians(90 - getAngle(enemy, this.getCoords()));
        double x = Math.cos(angle) * this.rotateRadius + this.getCoords().getX();
        double y = -Math.sin(angle) * this.rotateRadius + this.getCoords().getY();
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
        return (360 + Math.toDegrees(Math.atan2(enemyX, enemyY) - Math.atan2(0, 1))) % 360;
    }

    private void pointTowardsEnemy(Enemy enemy) {
        Rotate rotate = new Rotate(getAngle(enemy, this.getCoords()), this.getCoords().getX(), this.getCoords().getY());
        this.view.getTransforms().clear();
        this.view.getTransforms().add(rotate);
    }

    public Node getView() {
        Pane pane = new Pane();
        pane.getChildren().addAll(towerBase.getView(), super.getView());
        return pane;
    }

    public Enemy getNearestEnemy(List<Enemy> enemies) {
        if (enemies.isEmpty()) return null;
        Enemy nearestEnemy = enemies.get(0);
        for (Enemy enemy : enemies) {
            if (this.distanceTo(enemy) < this.distanceTo(nearestEnemy))
                nearestEnemy = enemy;
        }
        if (this.canHitEnemy(nearestEnemy)) return nearestEnemy;
        else return null;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
}
