package thegame.character.enemy;

/**
 high HP and ARMOR, low SPEED, high bounty
 */
public class TankerEnemy extends Enemy {
    private static final int HP = 100;
    private static final int ARMOR = 20;
    public static final int SPEED = 3;
    private static final int BOUNTY = 300;
    public TankerEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, "src/thegame/res/asset/enemy/towerDefense_tile247.png");
    }
    public TankerEnemy(int x, int y) {
        super(x, y);
    }
}
