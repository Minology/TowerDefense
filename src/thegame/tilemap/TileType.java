package thegame.tilemap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minology on 09:28 SA
 */
public enum TileType {
    ROAD(0),
    OBSTACLE(1),
    START(2),
    END(3),
    NORMAL(4),
    SNIPER(5),
    MACHINE(6),
    TILE_UNKNOWN(-1);

    private final int value;

    TileType(int value) {
        this.value = value;
    }

    private static final Map<Integer, TileType> intToTypeMap = new HashMap<Integer, TileType>();
    static {
        for (TileType type : TileType.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    public static TileType fromInt(int i) {
        TileType type = intToTypeMap.get(i);
        if (type == null)
            return TileType.TILE_UNKNOWN;
        return type;
    }

    public int getValue() {
        return value;
    }
}
