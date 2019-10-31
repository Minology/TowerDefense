package thegame.character.enemy;

/**
 high HP and ARMOR, low SPEED, high bounty
 */
public class TankerEnemy extends Enemy {
    private static final int HP = 300;
    private static final int ARMOR = 24;
    private static final double SPEED = 1;
    private static final int BOUNTY = 15;
    private static final String PATH = "src/thegame/res/asset/enemy/tankerEnemy.png";

    public TankerEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, PATH);
    }
}
