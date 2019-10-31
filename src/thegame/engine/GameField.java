package thegame.engine;

import thegame.character.enemy.*;
import thegame.character.tower.Tower;
import java.util.ArrayList;
import java.util.List;

public class GameField {
    private int resources;
    private int lives;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;

    public GameField() {
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        resources = 0;
        lives = 100;
    }

    public void addEnemy(EnemyType enemyType) {
        switch (enemyType){
            case NORMAL:
                enemies.add(new NormalEnemy());
                break;
            case SMALLER:
                enemies.add(new SmallerEnemy());
                break;
            case TANKER:
                enemies.add(new TankerEnemy());
                break;
            case BOSS:
                enemies.add(new BossEnemy());
                break;
        }
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public List<Tower> getTowerList() {
        return towers;
    }

    public List<Enemy> getEnemyList() {
        return enemies;
    }

    public void setEnemyList(ArrayList<Enemy> enemyList) {
        enemies = enemyList;
    }

    public Enemy getLastEnemy() {
        return enemies.get(enemies.size() - 1);
    }

    public boolean hasEnemy() {
        return !enemies.isEmpty();
    }

    public int getLives() {
        return lives;
    }

    public int getResources() {
        return resources;
    }

    public void minusLive(int lives) {
        this.lives -= lives;
    }

    public void addResource(int resources) {
        this.resources += resources;
    }
}
