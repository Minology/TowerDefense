package thegame.character.tower;

import javafx.scene.paint.Color;

/**
 * average stats
 */
public class NormalTower extends Tower{
    private static final int RANGE = 170;
    private static final int DAMAGE = 20;
    private static final double SPEED = 3;
    private static final int PROJECTILE_SIZE = 5;
    private static final Color PROJECTILE_COLOR = Color.GREEN;
    private static final int CENTER_X = 0;
    private static final int CENTER_Y = -13;
    private static final int ROTATE_RADIUS = 44;
    public NormalTower() {}
    public NormalTower(int x, int y) {
        super(RANGE, DAMAGE, SPEED, x, y, CENTER_X, CENTER_Y, PROJECTILE_SIZE, PROJECTILE_COLOR, ROTATE_RADIUS,
                "src/thegame/res/asset/tower/towerDefense_tile249.png",
                "res/sound/normalTowerEffect.mp3");
    }
}
