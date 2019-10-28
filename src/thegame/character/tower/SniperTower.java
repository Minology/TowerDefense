package thegame.character.tower;

import javafx.scene.paint.Color;

/**
 * high range, high damage output, low speed
 */
public class SniperTower extends Tower {
    private static final int RANGE = 250;
    private static final int DAMAGE = 50;
    private static final double SPEED = 1.2;
    private static final int PROJECTILE_SIZE = 5;
    private static final Color PROJECTILE_COLOR = Color.BLACK;
    private static final int CENTER_X = 0;
    private static final int CENTER_Y = 0;
    private static final int ROTATE_RADIUS = 28;
    public SniperTower() {}
    public SniperTower(int x, int y) {
        super(RANGE, DAMAGE, SPEED, x, y, CENTER_X, CENTER_Y, PROJECTILE_SIZE, PROJECTILE_COLOR, ROTATE_RADIUS,
                "src/thegame/res/asset/tower/towerDefense_tile206.png",
                "res/sound/sniperTowerEffect.mp3");
    }
}
