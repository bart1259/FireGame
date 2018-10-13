package game;

import components.CircleColliderComponent;
import components.SpriteRenderer;
import coreEngine.Entity;
import coreEngine.ResourceManager;
import misc.Vector2;
import physics.Collision;
import physics.CollisionEventListener;

//Health that can be picked up
public class HealthPickUp extends Entity implements CollisionEventListener{
	
	//The amount of health the player gets from picking up the health
	private int healthAmt = 20;
	//The radius of the collider of the health
	private int radius = 10;
	//Whether or not the pack was picked up
	private boolean dead = false;
	
	//Make a health pick up at the postion x,y
	public HealthPickUp(float x, float y) {
		super(x, y);
		
		addComponent(new SpriteRenderer(ResourceManager.GetImage("HealthPickup")));
		addComponent(new CircleColliderComponent());
		getComponent(CircleColliderComponent.class).setRadius(radius);
		getComponent(CircleColliderComponent.class).subscribeCollisionListener(this);
	}
	
	public HealthPickUp(Vector2 position) {
		this(position.x, position.y);
	}
	
	//Returns whether or not the health is picked up or not
	public boolean isDead() {
		return dead;
	}

	@Override
	public void onCollision(Collision collision) {
		//If it collides with the player, heal the player, and kill the healthPickUp
		if(collision.getOtherEntity().hasTag("Player")) {
			Player.player.heal(healthAmt);
			dead = true;
			terminate();
		}
	}
	
}
