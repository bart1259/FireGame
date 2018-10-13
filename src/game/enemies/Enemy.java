package game.enemies;

import java.util.Random;

import components.CircleColliderComponent;
import components.SpriteRenderer;
import coreEngine.Entity;
import coreEngine.ResourceManager;
import coreEngine.Time;
import graphics.GameWindow;
import misc.Vector2;

//Base class for enemies
//Also class for standard fire enemy
public class Enemy extends Entity{
	
	//Speed of the fire
	protected int speedX = 200;
	protected int speedY = -200;
	//radius of the fire's collider
	protected float radius = 10.0f;
	
	protected boolean bounceOffBoundries = true;
	
	//Makes an enemy at the appropriate position and speed
	public Enemy(Vector2 position, int speedX, int speedY) {
		this.speedX = speedX;
		this.speedY = speedY;
		setPosition(position);
		
		addTag("Enemy");
		
		addComponent(new SpriteRenderer(ResourceManager.GetImage("Enemy")));
		addComponent(new CircleColliderComponent());
		getComponent(CircleColliderComponent.class).setRadius(radius);
	}
	
	//Make the default enemy at 0,0
	public Enemy() {
		this(new Vector2(0,0),100 ,100);
	}
	
	public void update() {
		super.update();
		
		//Move the fire by what the AI tells it to move
		translate(doAI());
		
		//If we are not bouncing, return
		if(!bounceOffBoundries)
			return;
		
		//Code to bounce off of boundaries
		Random random = new Random(System.currentTimeMillis());
		
		if(getPosition().x < radius)
			speedX = random.nextInt(200) + 100;
		if(getPosition().x > GameWindow.MainWindow.getWidth() - radius)
			speedX = -(random.nextInt(200) + 100);
		if(getPosition().y < radius)
			speedY = random.nextInt(200) + 100;
		if(getPosition().y > GameWindow.MainWindow.getHeight() - 140 - (4*radius))
			speedY = -(random.nextInt(200) + 100);
	}
	
	//Do the AI, this is commonly overridden
	protected Vector2 doAI() {
		return new Vector2(speedX * Time.getDeltaTimef(), speedY * Time.getDeltaTimef());
	}
	
}
