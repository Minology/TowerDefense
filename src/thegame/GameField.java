package thegame;

import thegame.character.enemy.*;
import thegame.character.tower.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minology on 08:29 CH
 */
public class GameField {
    enum state {
        IS_RUNNING,
        IS_PAUSED,
        IS_STOPPED
    }

    private int coin;
    private int lives;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;

    public GameField() {
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        coin = 0;
        lives = 0;
    }

    public void addEnemy(Enemy.type enemyType) {
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

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
}
