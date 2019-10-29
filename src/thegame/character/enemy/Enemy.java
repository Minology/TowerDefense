package thegame.character.enemy;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import thegame.character.healthbar.HealthBar;
import thegame.character.entity.MovableEntity;

/**
 * Created by Minology on 06:07 CH
 */
public abstract class Enemy extends MovableEntity {
    private int armor;
    private int fullHp;
    private int hp;
    private int speed;
    private int bounty;
    private HealthBar healthBar;
    public enum type {
        NORMAL,
        SMALLER,
        TANKER,
        BOSS
    }

    public Enemy(int x, int y){
        super(x, y);
    }

    public Enemy(int hp, int damage, int speed, int bounty, String path) {
        super(path);
        this.fullHp = hp;
        this.hp = hp;
        this.armor = damage;
        this.speed = speed;
        this.bounty = bounty;
        this.healthBar = new HealthBar();
        this.setView(-32, -32);
        this.setTranslate(-50, -50);
    }

    public void move(Pane enemyLayer, Path path) {
        createPathTransition(enemyLayer, path,true, getTravelTime());
        healthBar.createPathTransition(enemyLayer, path, false, getTravelTime());
    }

    private double getTravelTime() {
        return 100000.0 / speed;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void getDamaged(int damage) {
        hp -= damage;
        if (hp > 0) healthBar.setValue(1.0 * hp / fullHp);
    }

    public Node getHealthBarPane() {
        return healthBar.getView();
    }
}
