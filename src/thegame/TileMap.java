package thegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Minology on 06:00 CH
 */
public class TileMap extends ImageView {
    private int[][] map;
    private final int WIDTH;
    private final int HEIGHT;
    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;
    private final int TILE_SIZE = 64;
    private Path enemyPath;
    private Path healthPath;

    public TileMap(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.TILE_WIDTH = width / Config.TILE_SIZE + 1;
        this.TILE_HEIGHT = height / Config.TILE_SIZE + 1;
        map = new int[TILE_HEIGHT][TILE_WIDTH];
        loadMapArray();
        loadMapImage();
        enemyPath = new Path();
        healthPath = new Path();
        findPath();
    }

    private void loadMapArray()  {
        try{
            File src = new File("src/thegame/res/map/map1.txt");
            Scanner in = new Scanner(src);
            for (int i = 0; i < TILE_HEIGHT; ++i) {
                for (int j = 0; j < TILE_WIDTH; ++j) {
                    map[i][j] = in.nextInt();
                }
            }
        } catch (IOException ex){
            System.out.println("Where in the world is the file map1.txt?");
        }
    }

    private void loadMapImage(){
        try{
            InputStream is = Files.newInputStream(Paths.get("src/thegame/res/map/tilemap.png"));
            Image image = new Image(is);
            this.setFitWidth(Config.SCREEN_WIDTH);
            this.setFitHeight(Config.SCREEN_HEIGHT);
            this.setImage(image);
        }
        catch (IOException e) {
            System.out.println("Where in the world is tilemap.png?");
        }
    }

    private class Coordinate {
        private int x;
        private int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean checkBound(int x, int y) {
        return x >= 0 && x < TILE_HEIGHT && y >= 0 && y < TILE_WIDTH;
    }

    private boolean legal(int x, int y) {
        return checkBound(x, y) && map[x][y] != 1;
    }

    private void appendToPath(int x, int y) {
        enemyPath.getElements().add(new LineTo(TILE_SIZE * y, TILE_SIZE * x));
        healthPath.getElements().add(new LineTo(TILE_SIZE * y, TILE_SIZE * x - 30));
    }

    private void findPath() {
        int[][] origin = map;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int x = 0, y = 0;
        for (int i = 0; i < TILE_HEIGHT; ++i) {
            for (int j = 0; j < TILE_WIDTH; ++j) {
                if (map[i][j] == 2) {
                    x = i;
                    y = j;
                }
            }
        }

        enemyPath.getElements().add(new MoveTo(y * 64, x * 64));
        healthPath.getElements().add(new MoveTo(y * 64, x * 64 - 30));
        do {
            for (int dir = 0; dir < 4; ++dir) {
                if (legal(x + dx[dir], y + dy[dir])) {
                    map[x][y] = 1;
                    x += dx[dir]; y += dy[dir];
                    appendToPath(x, y);
                }
            }
        } while (map[x][y] != 3);
        map = origin;
    }

    public Path getEnemyPath() {
        return enemyPath;
    }

    public Path getHealthPath() {
        return healthPath;
    }
}
