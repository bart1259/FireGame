package misc;

import java.util.Random;

import game.Player;

public class SpawnPosition {
	
	static final int radius = 300;
	static Random random;
	
	public static boolean isValidPosition(Vector2 pos) {
		
		if(Player.player == null)
			return true;
		
		Vector2 playerPosition = Player.player.getPosition();
		
		float dx = playerPosition.x - pos.x;
		float dy = playerPosition.y - pos.y;
		
		if( (dx *dx) + (dy * dy) < radius * radius ) {
			return false;
		}
		
		return true;
	}
	
	public static Vector2 getValidPosition() {
		
		if(random == null)
			random = new Random(System.currentTimeMillis());
		
		Vector2 ret = new Vector2(random.nextInt(1240) + 20,random.nextInt(480) + 20);
		
		while(!isValidPosition(ret)) {
			ret = new Vector2(random.nextInt(1240) + 20,random.nextInt(480) + 20);
		}
		
		return ret;
		
	}
	
}
