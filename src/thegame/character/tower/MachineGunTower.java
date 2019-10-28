package thegame.character.tower;

import javafx.scene.paint.Color;

/**
 * low range, average damage output, high speed
 */
public class MachineGunTower extends Tower{
    private static final int RANGE = 130;
    private static final int DAMAGE = 25;
    private static final double SPEED = 0.6;
    private static final int PROJECTILE_SIZE = 5;
    private static final Color PROJECTILE_COLOR = Color.RED;
    private static final int CENTER_X = 0;
    private static final int CENTER_Y = -13;
    private static final int ROTATE_RADIUS = 44;
    public MachineGunTower() {}
    public MachineGunTower(int x, int y) {
        super(RANGE, DAMAGE, SPEED, x, y, CENTER_X, CENTER_Y, PROJECTILE_SIZE, PROJECTILE_COLOR, ROTATE_RADIUS,
                "src/thegame/res/asset/tower/towerDefense_tile250.png",
                "res/sound/machineTowerEffect.mp3");
    }
}
