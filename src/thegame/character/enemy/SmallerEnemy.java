package thegame.character.enemy;

/**
 low HP and ARMOR, high SPEED, average bounty
 */
public class SmallerEnemy extends Enemy{
    private static final int HP = 30;
    private static final int ARMOR = 7;
    public static final int SPEED = 10;
    private static final int BOUNTY = 150;
    public SmallerEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, "src/thegame/res/asset/enemy/towerDefense_tile246.png");
    }
    public SmallerEnemy(int x, int y){
        super(x, y);
    }
}
