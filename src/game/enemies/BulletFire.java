package game.enemies;

import components.CircleColliderComponent;
import components.DestroyIn;
import components.SpriteRenderer;
import coreEngine.Time;
import misc.Vector2;

public class BulletFire extends Enemy{
	
	private static final double MAX_TIME_ALIVE = 8;
	private static final int RADIUS = 5;
	
	public BulletFire(float x, float y, int speedX, int speedY) {
		super(new Vector2(x,y), speedX, speedY);
		bounceOffBoundries = false;
		
		addComponent(new DestroyIn(MAX_TIME_ALIVE));
		getComponent(SpriteRenderer.class).setSprite("SmallBrownFire");
		getComponent(CircleColliderComponent.class).setRadius(RADIUS);
	}
	
	@Override
	protected Vector2 doAI() {

		return new Vector2(speedX * Time.getDeltaTimef(),speedY*Time.getDeltaTimef());
	}
}
