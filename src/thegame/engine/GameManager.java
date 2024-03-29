package thegame.engine;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import thegame.character.enemy.EnemyType;
import thegame.character.projectile.Projectile;
import thegame.character.enemy.Enemy;
import thegame.character.enemy.EnemyWaves;
import thegame.character.tower.MachineGunTower;
import thegame.character.tower.NormalTower;
import thegame.character.tower.SniperTower;
import thegame.character.tower.Tower;
import thegame.config.Config;
import thegame.menu.MenuNavigator;
import thegame.menu.PostGameMenu;
import thegame.service.MusicPlayer;
import thegame.tilemap.TileMap;
import thegame.tilemap.TileType;

import javax.crypto.Mac;
import javax.net.ssl.SNIHostName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private TileMap tileMap;
    private Pane enemyLayer;
    private Pane towerLayer;
    private Pane projectileLayer;
    private GameField gameField;
    private EnemyWaves enemyWaves;
    private MusicPlayer backgroundTheme;
    private static Stage stage;
    private GameController gameController;
    private AnimationTimer gameLoop;
    private Scene gameScene;

    public void initialize() throws IOException {
        gameField = new GameField();
        tileMap = new TileMap(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        StackPane gamePane = new StackPane();
        Group tilemapGroup = new Group();
        enemyLayer = new Pane();
        towerLayer = new Pane();
        projectileLayer = new Pane();
        enemyLayer.getChildren().add(tilemapGroup);
        tilemapGroup.getChildren().add(tileMap);
        gamePane.getChildren().addAll(enemyLayer, towerLayer, projectileLayer);
        gameScene = new Scene(gamePane);
        GameManager.stage.setScene(gameScene);
        enemyWaves = new EnemyWaves();
        backgroundTheme = new MusicPlayer(MusicPlayer.BACKGROUND, true);
        FXMLLoader loader = new FXMLLoader(MenuNavigator.GAMEUI);
        Node gameUI = loader.load(MenuNavigator.GAMEUI.openStream());
        gameController = loader.getController();
        gamePane.getChildren().add(gameUI);
        createTower();
        startGame();
    }

    public static void setGameStage(Stage stage1) {
        stage = stage1;
    }

    public static Stage getGameStage() {
        return stage;
    }

    private void createTower() {
        ArrayList<ArrayList<Integer>> spots;
        spots = tileMap.getBuildSpots(TileType.NORMAL);
        for (ArrayList<Integer> spot : spots) {
            NormalTower normalTower = new NormalTower(
                    Config.TILE_SIZE * spot.get(1) - Config.TILE_SIZE,
                    Config.TILE_SIZE * spot.get(0) - Config.TILE_SIZE);
            gameField.addTower(normalTower);
            towerLayer.getChildren().addAll(normalTower.getView());
        }
        spots = tileMap.getBuildSpots(TileType.SNIPER);
        for (ArrayList<Integer> spot : spots) {
            SniperTower sniperTower = new SniperTower(
                    Config.TILE_SIZE * spot.get(1) - Config.TILE_SIZE,
                    Config.TILE_SIZE * spot.get(0) - Config.TILE_SIZE);
            gameField.addTower(sniperTower);
            towerLayer.getChildren().addAll(sniperTower.getView());
        }
        spots = tileMap.getBuildSpots(TileType.MACHINE);
        for (ArrayList<Integer> spot : spots) {
            MachineGunTower machineGunTower = new MachineGunTower(
                    Config.TILE_SIZE * spot.get(1) - Config.TILE_SIZE,
                    Config.TILE_SIZE * spot.get(0) - Config.TILE_SIZE);
            gameField.addTower(machineGunTower);
            towerLayer.getChildren().addAll(machineGunTower.getView());
        }
      /**  NormalTower normalTower1 = new NormalTower(Config.SCREEN_WIDTH - Config.TILE_SIZE*2, Config.TILE_SIZE*5);
        MachineGunTower machineGunTower1 = new MachineGunTower(Config.SCREEN_WIDTH - Config.TILE_SIZE*7, Config.TILE_SIZE*7);
        SniperTower sniperTower1 = new SniperTower(Config.TILE_SIZE*10, Config.TILE_SIZE*5);
        MachineGunTower machineGunTower2 = new MachineGunTower(Config.TILE_SIZE*8, Config.TILE_SIZE*5);
        gameField.addTower(normalTower1);
        gameField.addTower(machineGunTower1);
        gameField.addTower(sniperTower1);
        gameField.addTower(machineGunTower2);
        towerLayer.getChildren().addAll(normalTower1.getView(), machineGunTower1.getView(), sniperTower1.getView(),
                    machineGunTower2.getView()); */
    }

    private void spawnEnemies(EnemyType enemyType) {
        gameField.addEnemy(enemyType);
        Enemy enemy = gameField.getLastEnemy();
        enemy.move(enemyLayer, tileMap.getEnemyPath(), tileMap.getLength());
    }

    private void createProjectiles(long currentNanoTime) {
        for (Tower tower : gameField.getTowerList()) {
            if (tower.cooledDown(currentNanoTime)) {
                Enemy nearestEnemy = tower.getNearestEnemy(gameField.getEnemyList());
                if (nearestEnemy != null) {
                    tower.setLastShootTime(currentNanoTime);
                    tower.createProjectile(nearestEnemy, projectileLayer);
                }
            }
        }
    }

    private void chaseEnemy() {
        for (Tower tower : gameField.getTowerList()) {
            ArrayList<Projectile> updatedProjectiles = new ArrayList<>();
            for (Projectile projectile: tower.getProjectiles()) {
                projectile.chase();
                if (projectile.hit()) {
                    projectile.remove();
                }
                else updatedProjectiles.add(projectile);
            }
            tower.setProjectiles(updatedProjectiles);
        }
    }

    private void removeEnemies() {
        if (gameField.getEnemyList().isEmpty()) return;
        ArrayList<Enemy> enemyList = new ArrayList<>();
        for (Enemy enemy : gameField.getEnemyList()) {
            if (!enemy.isAlive() || enemy.isFinished()) {
                enemyLayer.getChildren().remove(enemy.getView());
                enemyLayer.getChildren().remove(enemy.getHealthBarPane());
            } else {
                enemyList.add(enemy);
            }
            if (enemy.isAlive() && enemy.isFinished()) {
                gameField.minusLive(1);
            }
            if (!enemy.isAlive()) {
                gameField.addResource(enemy.getBounty());
            }
        }
        gameField.getEnemyList().clear();
        gameField.setEnemyList(enemyList);
    }

    private void updateLabels(boolean isDelayed, long delay) {
        gameController.updateLabels(
                Integer.toString(enemyWaves.getCurrentWave() + 1),
                Integer.toString(gameField.getLives()),
                Integer.toString(gameField.getResources()),
                isDelayed,
                Integer.toString((int) (EnemyWaves.WAVE_DELAY_IN_SECOND + 1 - (delay + 1e7) / 1e9))
        );
    }

    private void stopGame(String message) {
        GameManager.stage.setScene(new Scene(PostGameMenu.setPostScreen(message)));
        gameLoop.stop();
        backgroundTheme.stop();
    }

    private void startGame() {
        final AnimationTimer timer = new AnimationTimer() {
            long lastEnemySpawnNanoTime = System.nanoTime();
            long lastWaveFinishedTime = System.nanoTime();
            boolean waveFinishedFlag = false;
            @Override
            public void handle(long currentNanoTime) {
                if (!enemyWaves.hasNextEnemyInCurrentWave() && !gameField.hasEnemy() && !waveFinishedFlag) {
                    lastWaveFinishedTime = currentNanoTime;
                    waveFinishedFlag = true;
                }
                double waveDelay = (currentNanoTime - lastWaveFinishedTime) / 1e9;
                if (enemyWaves.readyToNextWave(waveDelay) && waveFinishedFlag) {
                    enemyWaves.toNextWave();
                    waveFinishedFlag = false;
                }
                double elapsedEnemySpawnTime = (currentNanoTime - lastEnemySpawnNanoTime) / 1e9;
                if (enemyWaves.readyToNextEnemy(elapsedEnemySpawnTime)) {
                    spawnEnemies(enemyWaves.getNextEnemy());
                    lastEnemySpawnNanoTime = currentNanoTime;
                }
                if (waveFinishedFlag && !enemyWaves.hasNextWave() && waveDelay >= PostGameMenu.POST_GAME_DELAY_IN_SECOND) {
                    stopGame(PostGameMenu.WIN_TEXT);
                }
                if (gameField.getLives() == 0) {
                    stopGame(PostGameMenu.LOST_TEXT);
                }
                createProjectiles(currentNanoTime);
                chaseEnemy();
                removeEnemies();
                updateLabels(waveFinishedFlag & enemyWaves.hasNextWave(), currentNanoTime - lastWaveFinishedTime);
            }
        };
        gameLoop = timer;
        timer.start();
    }
}
