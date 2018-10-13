package game.enemies;

import components.CircleColliderComponent;
import components.DestroyIn;
import components.SpriteRenderer;
import misc.Vector2;

//The little blue fires
public class StaticEnemy extends Enemy {

	//The time until the static enemy is destroyed
	private static final double MAX_TIME_ALIVE = 0.75;
	//The radius of the fire
	private static final int RADIUS = 5;
	
	//Makes a fire
	public StaticEnemy(float x, float y, int speedX, int speedY) {
		super(new Vector2(x,y), speedX, speedY);
		
		//Add the destroy component so it gets destoyed after MAX_TIME_ALIVE		
		addComponent(new DestroyIn(MAX_TIME_ALIVE));
		//Change the sprite to the small blue fire image
		getComponent(SpriteRenderer.class).setSprite("SmallBlueFire");
		//Set the radius of the Fire
		getComponent(CircleColliderComponent.class).setRadius(RADIUS);
	}
	
	@Override
	protected Vector2 doAI() {
		//The fire has no AI so it doesn't move
		return new Vector2(0,0);
	}
}
