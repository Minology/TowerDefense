package thegame.character.enemy;

/**
 average stats
 */
public class NormalEnemy extends Enemy {
    public static final int HP = 40;
    public static final int ARMOR = 10;
    public static final int SPEED = 6;
    public static final int BOUNTY = 200;
    public NormalEnemy(int x, int y){
        super(x, y);
    }
    public NormalEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, "src/thegame/res/asset/enemy/towerDefense_tile245.png");
    }
}
