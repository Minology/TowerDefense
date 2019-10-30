package thegame.character.tower;

import javafx.scene.paint.Color;

/**
 * high range, high damage output, low speed
 */
public class SniperTower extends Tower {
    private static final int RANGE = 250;
    private static final int DAMAGE = 65;
    private static final double SPEED = 0.7;
    private static final int PROJECTILE_SIZE = 5;
    private static final Color PROJECTILE_COLOR = Color.BLACK;
    private static final int CENTER_X = 0;
    private static final int CENTER_Y = 0;
    private static final int ROTATE_RADIUS = 28;
    private static final int COST = 150;
    private static final String PATH = "src/thegame/res/asset/tower/sniperTower.png";
    private static final String SOUND_EFFECT = "/thegame/res/sound/sniperTowerEffect.mp3";

    public SniperTower(int x, int y) {
        super(RANGE, DAMAGE, SPEED, COST, x, y, CENTER_X, CENTER_Y, PROJECTILE_SIZE, PROJECTILE_COLOR, ROTATE_RADIUS,
                PATH, SOUND_EFFECT);
    }
}
