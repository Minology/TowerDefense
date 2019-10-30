package thegame.tilemap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import thegame.config.Config;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TileMap extends ImageView {
    private int[][] map;
    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;
    private final int TILE_SIZE = 64;
    private Path path;

    private final static int ROAD = 0;
    private final static int OBSTACLE = 1;
    private final static int START = 2;
    private final static int END = 3;
    private final static int BUILDABLE = 4;

    public TileMap(int width, int height) {
        this.TILE_WIDTH = width / Config.TILE_SIZE + 1;
        this.TILE_HEIGHT = height / Config.TILE_SIZE + 1;
        map = new int[TILE_HEIGHT][TILE_WIDTH];
        loadMapArray();
        loadMapImage();
        path = new Path();
        findPath();
    }

    private void loadMapArray() {
        try {
            File src = new File("src/thegame/res/map/map1.txt");
            Scanner in = new Scanner(src);
            for (int i = 0; i < TILE_HEIGHT; ++i) {
                for (int j = 0; j < TILE_WIDTH; ++j) {
                    map[i][j] = in.nextInt();
                }
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

    private boolean checkBound(int x, int y) {
        return x >= 0 && x < TILE_HEIGHT && y >= 0 && y < TILE_WIDTH;
    }

    private boolean legal(int x, int y) {
        return checkBound(x, y) && map[x][y] != OBSTACLE && map[x][y] != BUILDABLE;
    }

    private void appendToPath(int x, int y) {
        path.getElements().add(new LineTo(TILE_SIZE * y, TILE_SIZE * x));
    }

    private void findPath() {
        int[][] origin = map;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int x = 0, y = 0;
        for (int i = 0; i < TILE_HEIGHT; ++i) {
            for (int j = 0; j < TILE_WIDTH; ++j) {
                if (map[i][j] == START) {
                    x = i;
                    y = j;
                }
            }
        }

        path.getElements().add(new MoveTo(y * TILE_SIZE, x * TILE_SIZE));
        do {
            for (int dir = 0; dir < 4; ++dir) {
                if (legal(x + dx[dir], y + dy[dir])) {
                    map[x][y] = 1;
                    x += dx[dir]; y += dy[dir];
                    appendToPath(x, y);
                }
            }
        } while (map[x][y] != END);
        map = origin;
    }

    public Path getEnemyPath() {
        return path;
    }
}
