package game;

import components.CircleColliderComponent;
import components.SpriteRenderer;
import coreEngine.Entity;
import coreEngine.ResourceManager;
import misc.Vector2;
import physics.Collision;
import physics.CollisionEventListener;

//A coin in game
public class Coin extends Entity implements CollisionEventListener{
	
	//How much the coin is worth
	private int worth = 1;
	//The radius of the coin
	private int radius = 5;
	//Whether or not the coin has been picked up or not
	private boolean dead = false;
	
	//Make the coin at the position x,y
	public Coin(float x, float y) {
		super(x, y);
		
		//Make the coin look like a coin
		addComponent(new SpriteRenderer(ResourceManager.GetImage("Coin")));
		//Add a collider component
		addComponent(new CircleColliderComponent());
		//Set the radius of the collider
		getComponent(CircleColliderComponent.class).setRadius(radius);
		//Set the collider callback
		getComponent(CircleColliderComponent.class).subscribeCollisionListener(this);
	}
	
	//Makes the coin at the position vector
	public Coin(Vector2 position) {
		this(position.x, position.y);
	}
	
	//Makes a coin at 0,0
	public Coin() {
		this(0, 0);
	}
	
	//Set the worth of the coin
	public void setWorth(int worth) {
		this.worth = worth;
	}
	
	//Set dead to true
	public boolean isDead() {
		return dead;
	}

	@Override
	public void onCollision(Collision collision) {
		//If the coin is collided with the player
		if(collision.getOtherEntity().hasTag("Player")) {
			//Give the player money, update the score, and destroy the coin
			MoneyManager.increaseMoney(worth);
			Score.collectedCoin(worth);
			dead = true;
			terminate();
		}
	}
}
