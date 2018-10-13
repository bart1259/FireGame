package game.enemies;

import components.SpriteRenderer;
import coreEngine.Time;
import misc.Vector2;

//The fast green fire
public class FastFire extends Enemy{
	
	
	public FastFire(Vector2 position, int speedX, int speedY) {
		super(position, speedX, speedY);
		getComponent(SpriteRenderer.class).setSprite("GreenFire");
	}

	@Override
	protected Vector2 doAI() {
		//Move the fire at twice the regular speed
		Vector2 ret = new Vector2(speedX, speedY);
		ret.multiply(2.5f * Time.getDeltaTimef());
		
		return ret;
	}
	
}
