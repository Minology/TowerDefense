package thegame.character.projectile;

import javafx.scene.shape.Circle;
import thegame.character.enemy.Enemy;
import thegame.character.entity.MovableEntity;

public class Projectile extends MovableEntity {
    private final static double HIT_RANGE = 5;
    private Enemy enemy;
    private double x;
    private double y;
    private double speed;
    private int damage;
    private Circle circle;

    public Projectile(Enemy enemy, int damage, double speed, Circle circle, double x, double y) {
        this.enemy = enemy;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.circle = circle;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void chase() {
        double x2 = enemy.getView().getTranslateX();
        double y2 = enemy.getView().getTranslateY();
        double dis = distance(x, y, x2, y2);
        x += speed * (x2 - x) / dis;
        y += speed * (y2 - y) / dis;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    public boolean hit() {
        if (distance(x, y, enemy.getView().getTranslateX(), enemy.getView().getTranslateY()) <= HIT_RANGE) {
            enemy.getDamaged(damage);
            return true;
        }
        return false;
    }

    public void remove() {
        circle.setVisible(false);
    }
}
