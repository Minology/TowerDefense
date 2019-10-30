package thegame.character.enemy;

/**
 high HP and ARMOR, low SPEED, high bounty
 */
public class TankerEnemy extends Enemy {
    private static final int HP = 100;
    private static final int ARMOR = 20;
    private static final int SPEED = 3;
    private static final int BOUNTY = 15;
    private static final String PATH = "src/thegame/res/asset/enemy/tankerEnemy.png";

    public TankerEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, PATH);
    }
}
