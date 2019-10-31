package thegame.character.enemy;

import thegame.tilemap.TileType;

import java.util.HashMap;
import java.util.Map;

public enum EnemyType {
    NORMAL(0),
    SMALLER(1),
    TANKER(2),
    BOSS(3),
    ENEMY_UNKNOWN(-1);

    private final int value;

    EnemyType(int value) {
        this.value = value;
    }

    private static final Map<Integer, EnemyType> intToTypeMap = new HashMap<Integer, EnemyType>();

    static {
        for (EnemyType type : EnemyType.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    public static EnemyType fromInt(int i) {
        EnemyType type = intToTypeMap.get(i);
        if (type == null)
            return EnemyType.ENEMY_UNKNOWN;
        return type;
    }
}
