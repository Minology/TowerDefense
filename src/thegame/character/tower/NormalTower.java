package thegame.character.tower;

import javafx.scene.paint.Color;

/**
 * average stats
 */
public class NormalTower extends Tower{
    private static final int RANGE = 170;
    private static final int DAMAGE = 40;
    private static final double SPEED = 1;
    private static final int PROJECTILE_SIZE = 5;
    private static final Color PROJECTILE_COLOR = Color.GREEN;
    private static final int CENTER_X = 0;
    private static final int CENTER_Y = -13;
    private static final int ROTATE_RADIUS = 44;
    private static final int COST = 100;
    private static final String PATH = "src/thegame/res/asset/tower/normalTower.png";
    private static final String SOUND_EFFECT = "/thegame/res/sound/normalTowerEffect.mp3";

    public NormalTower(int x, int y) {
        super(RANGE, DAMAGE, SPEED, COST, x, y, CENTER_X, CENTER_Y, PROJECTILE_SIZE, PROJECTILE_COLOR, ROTATE_RADIUS,
                PATH, SOUND_EFFECT);
    }
}
