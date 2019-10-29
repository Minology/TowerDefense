package thegame.character.enemy;

/**
    extreme HP and ARMOR, low SPEED, extreme bounty
*/
public class BossEnemy extends Enemy {
    private static final int HP = 300;
    private static final int ARMOR = 20;
    private static final int SPEED = 2;
    private static final int BOUNTY = 1500;
    public BossEnemy(int x, int y){
        super(x, y);
    }
    public BossEnemy() {
        super(HP, ARMOR, SPEED, BOUNTY, "src/thegame/res/asset/enemy/towerDefense_tile248.png");
    }
}
