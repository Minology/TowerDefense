package thegame.character.enemy;

/**
 low HP and ARMOR, high SPEED, average bounty
 */
public class SmallerEnemy extends Enemy{
    private static final int HP = 30;
    private static final int ARMOR = 7;
    private static final int SPEED = 11;
    private static final int BOUNTY = 10;
    private static final String PATH = "src/thegame/res/asset/enemy/smallerEnemy.png";

    public SmallerEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, PATH);
    }
}
