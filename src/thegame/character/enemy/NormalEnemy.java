package thegame.character.enemy;

/**
 average stats
 */
public class NormalEnemy extends Enemy {
    private static final int HP = 50;
    private static final int ARMOR = 10;
    private static final int SPEED = 6;
    private static final int BOUNTY = 10;
    private static final String PATH = "src/thegame/res/asset/enemy/normalEnemy.png";

    public NormalEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, PATH);
    }
}
