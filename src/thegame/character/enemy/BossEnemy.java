package thegame.character.enemy;

/**
    extreme HP and ARMOR, low SPEED, extreme bounty
*/
public class BossEnemy extends Enemy {
    private static final int HP = 900;
    private static final int ARMOR = 32;
    private static final double SPEED = 0.5;
    private static final int BOUNTY = 30;
    private static final String PATH = "src/thegame/res/asset/enemy/bossEnemy.png";

    public BossEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, PATH);
    }
}
