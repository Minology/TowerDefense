package thegame;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import thegame.character.projectile.Projectile;
import thegame.character.enemy.Enemy;
import thegame.character.enemy.EnemyWaves;
import thegame.character.tower.MachineGunTower;
import thegame.character.tower.NormalTower;
import thegame.character.tower.SniperTower;
import thegame.character.tower.Tower;
import thegame.service.MusicPlayer;
import thegame.tilemap.TileMap;

import java.util.ArrayList;

/**
 * Created by Minology on 08:11 CH
 */
public class GameStage {
    private TileMap tileMap;
    private Pane enemyLayer;
    private Pane towerLayer;
    private Pane projectileLayer;
    private GameField gameField;
    private Scene gameScene;
    private EnemyWaves enemyWaves;
    private MusicPlayer background;
    private static Stage stage;

    public void initialize() {
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
        GameStage.stage.setScene(gameScene);
        enemyWaves = new EnemyWaves();
        createTower();
        startGame();
        background = new MusicPlayer("res/sound/theme.mp3", true);
    }

    public static void setGameStage(Stage stage1) {
        stage = stage1;
    }

    private void createTower() {
        NormalTower normalTower1 = new NormalTower(Config.SCREEN_WIDTH - 64*2, 64*5);
        MachineGunTower machineGunTower1 = new MachineGunTower(Config.SCREEN_WIDTH - 64*7, 64*7);
        SniperTower sniperTower1 = new SniperTower(64*10, 64*5);
        gameField.addTower(normalTower1);
        gameField.addTower(machineGunTower1);
        gameField.addTower(sniperTower1);
        towerLayer.getChildren().addAll(normalTower1.getView(), machineGunTower1.getView(), sniperTower1.getView());
    }

    private void spawnEnemies(Enemy.type enemyType) {
        gameField.addEnemy(enemyType);
        Enemy enemy = gameField.getLastEnemy();
        enemy.move(enemyLayer, tileMap.getEnemyPath());
    }

    private void createProjectiles(long currentNanoTime) {
        for (Tower tower : gameField.getTowerList()) {
            if (tower.cooledDown(currentNanoTime)) {
                for (Enemy enemy : gameField.getEnemyList()) {
                    if (tower.canHitEnemy(enemy)) {
                        tower.setLastShootTime(currentNanoTime);
                        tower.createProjectile(enemy, projectileLayer);
                        break;
                    }
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
        }
        gameField.getEnemyList().clear();
        gameField.setEnemyList(enemyList);
    }

    private void startGame() {
        final AnimationTimer timer = new AnimationTimer() {
            long lastEnemySpawnNanoTime = System.nanoTime();
            long lastWaveFinishedTime = System.nanoTime();
            boolean finishedFlag = false;
            @Override
            public void handle(long currentNanoTime) {
                if ((currentNanoTime - lastWaveFinishedTime) >= EnemyWaves.WAVE_DELAY_IN_SECOND * 1e9 && finishedFlag) {
                    enemyWaves.toNextWave();
                    finishedFlag = false;
                }
                if (!enemyWaves.hasNextEnemyInCurrentWave() && !gameField.hasEnemy()) {
                    if (enemyWaves.hasNextWave() && !finishedFlag) {
                        lastWaveFinishedTime = currentNanoTime;
                        finishedFlag = true;
                    } else {}
                }
                double elapsedEnemySpawnTime = (currentNanoTime - lastEnemySpawnNanoTime) / 1e9;
                if (elapsedEnemySpawnTime > EnemyWaves.DELAY_TO_NEXT_ENEMY && enemyWaves.hasNextEnemyInCurrentWave()) {
                    spawnEnemies(enemyWaves.getNextEnemy());
                    lastEnemySpawnNanoTime = currentNanoTime;
                }
                createProjectiles(currentNanoTime);
                chaseEnemy();
                removeEnemies();
            }
        };
        timer.start();
    }
}
