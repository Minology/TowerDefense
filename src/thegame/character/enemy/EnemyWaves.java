package thegame.character.enemy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EnemyWaves {
    private static double DELAY_TO_NEXT_ENEMY = 1;
    public static double WAVE_DELAY_IN_SECOND = 15;
    private int numOfWaves;
    private int currentWave;
    private int currentEnemy;
    private ArrayList<ArrayList<EnemyType>> waves;

    public EnemyWaves() {
        currentEnemy = 0;
        currentWave = 0;
        try {
            File src = new File("src/thegame/res/enemywave/enemywaves.txt");
            Scanner in = new Scanner(src);
            numOfWaves = in.nextInt();
            waves = new ArrayList<>(numOfWaves);
            for (int i = 0; i < numOfWaves; ++i) {
                int numOfEnemies = in.nextInt();
                ArrayList<EnemyType> wave = new ArrayList<>();
                for (int j = 0; j < numOfEnemies; ++j) {
                    wave.add(EnemyType.fromInt(in.nextInt()));
                }
                waves.add(wave);
            }
        } catch (IOException ex) {
            System.out.println("You're save. I cannot find any enemy~~~");
        }
    }

    public boolean hasNextWave() {
        return currentWave + 1 < numOfWaves;
    }

    public void toNextWave() {
        ++currentWave;
        currentEnemy = 0;
    }

    public EnemyType getNextEnemy() {
        EnemyType enemyType = waves.get(currentWave).get(currentEnemy);
        ++currentEnemy;
        return enemyType;
    }

    public boolean readyToNextWave(double waveDelay) {
        return hasNextWave() && waveDelay >= WAVE_DELAY_IN_SECOND;
    }

    public boolean readyToNextEnemy(double elapsedEnemySpawnTime) {
        return elapsedEnemySpawnTime > EnemyWaves.DELAY_TO_NEXT_ENEMY && hasNextEnemyInCurrentWave();
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public boolean hasNextEnemyInCurrentWave() {
        return currentEnemy < waves.get(currentWave).size();
    }
}
