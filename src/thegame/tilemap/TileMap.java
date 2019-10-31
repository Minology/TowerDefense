package thegame.tilemap;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import thegame.config.Config;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TileMap extends ImageView {
    private ArrayList<ArrayList<TileType>> map;
    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;
    private final int TILE_SIZE = 64;
    private final int LENGTH;
    private Path path;

    public TileMap(int width, int height) {
        this.TILE_WIDTH = width / Config.TILE_SIZE + 1;
        this.TILE_HEIGHT = height / Config.TILE_SIZE + 1;
        map = new ArrayList<>();
        loadMapArray();
        loadMapImage();
        path = new Path();
        LENGTH = findPath();
    }

    private void loadMapArray() {
        try {
            File src = new File("src/thegame/res/map/map1.txt");
            Scanner in = new Scanner(src);
            for (int i = 0; i < TILE_HEIGHT; ++i) {
                ArrayList<TileType> thisRow = new ArrayList<>();
                for (int j = 0; j < TILE_WIDTH; ++j) {
                    thisRow.add(TileType.fromInt(in.nextInt()));
                }
                map.add(thisRow);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("Where in the world is the file map1.txt?");
        }
    }

    private void loadMapImage() {
        try {
            InputStream is = Files.newInputStream(Paths.get("src/thegame/res/map/tilemap.png"));
            Image image = new Image(is);
            this.setFitWidth(Config.SCREEN_WIDTH);
            this.setFitHeight(Config.SCREEN_HEIGHT);
            this.setImage(image);
            is.close();
        }
        catch (IOException e) {
            System.out.println("Where in the world is tilemap.png?");
        }
    }

    public ArrayList<ArrayList<Integer>> getBuildSpots(TileType type) {
        ArrayList<ArrayList<Integer>> buildSpots = new ArrayList<>();
        for (int i = 0; i < map.size(); ++i) {
            for (int j = 0; j < map.get(0).size(); ++j) {
                if (map.get(i).get(j) == type) {
                    ArrayList<Integer> spot = new ArrayList<>();
                    spot.add(i);
                    spot.add(j);
                    buildSpots.add(spot);
                }
            }
        }
        return buildSpots;
    }

    private boolean checkBound(int x, int y) {
        return x >= 0 && x < TILE_HEIGHT && y >= 0 && y < TILE_WIDTH;
    }

    private boolean legal(int x, int y, boolean[][] vis) {
        return checkBound(x, y) && !vis[x][y] &&
                (map.get(x).get(y) == TileType.ROAD || map.get(x).get(y) == TileType.END);
    }

    private void appendToPath(int x, int y) {
        path.getElements().add(new LineTo(TILE_SIZE * y, TILE_SIZE * x));
    }

    private int findPath() {
        boolean[][] vis = new boolean[map.size()][map.get(0).size()];
        final int[] dx = {0, 1, 0, -1};
        final int[] dy = {1, 0, -1, 0};
        int x = 0, y = 0, length = 0;
        for (int i = 0; i < TILE_HEIGHT; ++i) {
            for (int j = 0; j < TILE_WIDTH; ++j) {
                if (map.get(i).get(j) == TileType.START) {
                    x = i;
                    y = j;
                }
            }
        }

        path.getElements().add(new MoveTo(y * TILE_SIZE, x * TILE_SIZE));
        do {
            for (int dir = 0; dir < 4; ++dir) {
                if (legal(x + dx[dir], y + dy[dir], vis)) {
                    ++length;
                    vis[x][y] = true;
                    x += dx[dir]; y += dy[dir];
                    appendToPath(x, y);
                }
            }
        } while (map.get(x).get(y) != TileType.END);
        return length;
    }

    public Path getEnemyPath() {
        return path;
    }

    public int getLength() {
        return LENGTH;
    }
}
