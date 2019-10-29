package thegame.character.enemy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Minology on 09:29 CH
 */
public class EnemyWaves {
    public static double DELAY_TO_NEXT_ENEMY  = 1;
    public static double WAVE_DELAY_IN_SECOND = 3;
    private int numOfWaves;
    private int currentWave;
    private int currentEnemy;
    private ArrayList<Enemy.type>[] wave;

    public EnemyWaves() {
        currentEnemy = 0;
        currentWave = 0;
        try {
            File src = new File("src/thegame/res/enemywave/enemywaves.txt");
            Scanner in = new Scanner(src);
            numOfWaves = in.nextInt();
            wave = new ArrayList[numOfWaves];
            for (int i = 0; i < numOfWaves; ++i) {
                int numOfEnemies = in.nextInt();
                wave[i] = new ArrayList<>();
                for (int j = 0; j < numOfEnemies; ++j) {
                    switch (in.nextInt()){
                        case 0:
                            wave[i].add(Enemy.type.NORMAL);
                            break;
                        case 1:
                            wave[i].add(Enemy.type.SMALLER);
                            break;
                        case 2:
                            wave[i].add(Enemy.type.TANKER);
                            break;
                        case 3:
                            wave[i].add(Enemy.type.BOSS);
                            break;
                    }
                }
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

    public Enemy.type getNextEnemy() {
        Enemy.type enemyType = wave[currentWave].get(currentEnemy);
        ++currentEnemy;
        return enemyType;
    }


    public boolean hasNextEnemyInCurrentWave() {
        return currentEnemy < wave[currentWave].size();
    }
}
