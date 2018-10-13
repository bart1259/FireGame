package game;

import java.util.ArrayList;

import coreEngine.Time;
import game.enemies.Enemy;
import gui.TextLabel;

public class WaveSpawnwer {
	
	//The countdown until the game starts
	final static double INTIAL_COUNTDOWN = 2.5;
	
	//The cuurent wave
	private static int currentWave = 0;
	
	//The number of enemies spawned
	private static int enemiesSpawned = 0;
	//Time until the next enemy is spawned
	private static double nextEnemyCountDown = 1;
	//Time until the next wave is spawned
	private static double nextWaveCountDown = 0;
	
	static TextLabel waveLabel;
	
	static ArrayList<Enemy> enemies;
	
	//All the waves
	static Wave[] waves = {new Wave(10, 0 ,0, 0, 1, 10),
			new Wave(5, 2, 0, 0, 1, 15),
			new Wave(0, 3, 0, 0, 1, 20),
			new Wave(5, 0, 0, 2, 2, 10),
			new Wave(0, 1, 1, 1, 1, 20),
			new Wave(2, 0, 3, 3, 1, 20),
			new Wave(0, 2, 2, 2, 1, 20),
			new Wave(10, 0, 0, 2, 1, 20)};

	public static void Initialize() {
		currentWave = 0;
		enemiesSpawned = 0;
		nextEnemyCountDown = 1;
		nextWaveCountDown = 0;
		enemies = new ArrayList<>();
		nextWaveCountDown = INTIAL_COUNTDOWN;
		waveLabel = new TextLabel(10, 570, 150, 20);
		waveLabel.setTextSize(20.0f);
		waveLabel.setDrawOutLine(false);
		waveLabel.setText("Wave: " + 1 );
	}
	
	public static void update() {
		
		if(currentWave > waves.length) {
			//There are no more waves
			return;
		}
		
		//Figure out which timer is running and then run it
		if(currentWave == 0 || enemiesSpawned == waves[currentWave - 1].spawnEnemies)
			nextWaveCountDown -= Time.getDeltaTime();
		else
			nextEnemyCountDown -= Time.getDeltaTime();
		
		//When it is time to spawn the next enemy, we spawn the next enemy
		if(nextEnemyCountDown <= 0) {
			nextEnemyCountDown = waves[currentWave - 1].spawnRate;
			enemiesSpawned ++;
			spawnEnemy();
		}
		
		//If the wave is over, we start the next wave
		if(nextWaveCountDown <= 0) {
			
			currentWave++;
			
			if(currentWave > waves.length) {
				return;
			}
			
			enemiesSpawned = 0;

			nextEnemyCountDown = waves[currentWave - 1].spawnRate;
			nextWaveCountDown = waves[currentWave - 1].countdown;
			
			waveLabel.setText("Wave: " + currentWave);
			
			
		}
		
		
		
	}
	
	//Spawn an enemy
	public static void spawnEnemy() {
		enemies.add(waves[currentWave-1].spawnEnemy(enemiesSpawned));
	}
	
	//Returns the cuurent wave number
	public static int getCurrentWave() {
		return currentWave;
	}
	
}
