package game.enemies;

import components.SpriteRenderer;
import coreEngine.Time;
import misc.Vector2;

//The blue fire that drops little blue fire
public class DropFire extends Enemy{

	//How often it drops a fire
	private static final double dropRate = 0.08;
	
	//The count down until the small blue fire is dropped
	private double timeUntilDrop = 0;
	
	public DropFire(Vector2 position, int speedX, int speedY) {
		super(position, speedX, speedY);
		getComponent(SpriteRenderer.class).setSprite("BlueFire");
	}
	
	@Override
	protected Vector2 doAI() {
	
		//If it is time drop the fire
		timeUntilDrop -= Time.getDeltaTime();
		
		if(timeUntilDrop <= 0) {
			timeUntilDrop = dropRate;
			dropFire();
			
		}
		
		//Move the fire
		return new Vector2(speedX * Time.getDeltaTimef(), speedY * Time.getDeltaTimef());
	}
	
	//Drops a small blue fire at the current position
	private void dropFire() {
		new StaticEnemy(position.x,position.y,0,0);
	}
	
}
