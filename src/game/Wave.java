package game;

import java.util.Random;

import game.enemies.DropFire;
import game.enemies.Enemy;
import game.enemies.FastFire;
import game.enemies.ShooterFire;
import misc.SpawnPosition;
import misc.Vector2;

public class Wave {
	
	//The number of enemies spawned
	public int spawnEnemies;
	
	//The number of each type still needed to spawn
	private int normalFires;
	private int dropFires;
	@SuppressWarnings("unused")
	private int fastFires;
	private int shooterFires;
	
	public double countdown;
	public double spawnRate;
	
	public Wave(int normalFires, int dropFires, int shooterFires, int fastFires, double spawnRate, double countdown) {
		this.normalFires = normalFires;
		this.countdown = countdown;
		this.spawnRate = spawnRate;
		this.dropFires = dropFires;
		this.shooterFires = shooterFires;
		this.fastFires = fastFires;
		this.spawnEnemies = normalFires + dropFires + shooterFires + fastFires;
	}
	
	public Enemy spawnEnemy(int index) {
		
		//Generate a random position
		Random random = new Random();
		Vector2 position = SpawnPosition.getValidPosition();
		
		//Figure out which enemy we have to spawn and then spawn it at position with a random speed and direction
		if(index <= normalFires) {
			return new Enemy(position, (random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100),(random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100));
		}else if (index <= normalFires + dropFires){
			return new DropFire(position, (random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100), (random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100));
		}else if (index <= normalFires + dropFires + shooterFires){
			return new ShooterFire(position, (random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100), (random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100));
		}else {
			return new FastFire(position, (random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100), (random.nextInt(2) == 1 ? -1 : 1) * (random.nextInt(200) + 100));
		}
	}
	
}
