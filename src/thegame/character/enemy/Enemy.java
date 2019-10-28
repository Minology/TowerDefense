package thegame.character.enemy;

import javafx.scene.layout.Pane;
import thegame.character.GameEntity;

/**
 * Created by Minology on 06:07 CH
 */
public abstract class Enemy extends GameEntity {
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
    }

    public double getTravelTime() {
        return 100000.0 / speed;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void getDamaged(int damage) {
        hp -= damage;
        if (hp > 0) healthBar.setValue(1.0 * hp / fullHp);
    }

    public Pane getHealthBar() {
        return healthBar.getPane();
    }
}
