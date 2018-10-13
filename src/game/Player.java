package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import components.CircleColliderComponent;
import components.SpriteRenderer;
import coreEngine.Entity;
import coreEngine.Input;
import coreEngine.LevelManager;
import coreEngine.ResourceManager;
import coreEngine.Time;
import coreEngine.Input.KeyCode;
import graphics.GameWindow;
import gui.ProgressBar;
import misc.Vector2;
import physics.Collision;
import physics.CollisionEventListener;

public class Player extends Entity implements CollisionEventListener{
	
	//The player, singleton
	public static Player player;
	
	//The speed boost from hitting shift
	private float speedBoost = 1.75f;
	//The base resistance
	private float resistance = 1;
	
	//The base speed of the player
	float speed = 200f;
	//The radius of the player
	float radius = 10;
	//The health and sprint bars showing the amount of health and sprint
	private ProgressBar healthBar;
	private ProgressBar sprintBar;
	
	//Wheter or not the player has the compass
	private boolean hasCompass = false;
	
	public Player() {
		
		addTag("Player");
		
		addComponent(new SpriteRenderer(ResourceManager.GetImage("Player")));
		getComponent(SpriteRenderer.class).setSize(new Vector2(16, 16));
		position = new Vector2(GameWindow.MainWindow.getWidth()/2,GameWindow.MainWindow.getHeight()/2);
		
		player = this;
		
		addComponent(new CircleColliderComponent());
		getComponent(CircleColliderComponent.class).setRadius(10);
		getComponent(CircleColliderComponent.class).subscribeCollisionListener(this);
		
		//Initialize the health bars
		healthBar = new ProgressBar(10, 660, 150, 20,100);
		healthBar.setProgress(100);
		sprintBar = new ProgressBar(10, 630, 150, 20,100);
		sprintBar.setBarColor(Color.BLUE);
		sprintBar.setProgress(100);
	}
	
	@Override
	public void update() {
		super.update();
		
		Vector2 moveVector = new Vector2(0,0);
		
		//Calculate the speed boost
		float sprintBoost = 1;
		if(sprintBar.getProgress() > 0)
			sprintBoost = (Input.GetKey(KeyCode.Shift) ? speedBoost : 1.0f);
		
		//Get the players direction of movment
		if(Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.LeftArrow)) {
			moveVector.x += -speed;
		}
		if(Input.GetKey(KeyCode.D) || Input.GetKey(KeyCode.RightArrow)) {
			moveVector.x += speed;
		}
		if(Input.GetKey(KeyCode.W) || Input.GetKey(KeyCode.UpArrow)) {
			moveVector.y += -speed;
		}
		if(Input.GetKey(KeyCode.S) || Input.GetKey(KeyCode.DownArrow)) {
			moveVector.y += speed;
		}
		
		if(moveVector.magnitude() > speed) {
			moveVector.normalize();
			moveVector.multiply(speed);
		}
		moveVector.multiply(sprintBoost);
		
		//Take away sprint if it is being used
		if(moveVector.magnitude() > 0 && Input.GetKey(KeyCode.Shift) && sprintBar.getProgress() > 0) {
			sprintBar.decreaseProgress(25 * Time.getDeltaTimef());
		}
		
		//Move the player
		translate(( moveVector.x * Time.getDeltaTimef()), (moveVector.y * Time.getDeltaTimef()));
		
		//Ensure The player cant leave the play area
		float x = Math.min(GameWindow.MainWindow.getWidth() - radius, Math.max(radius, getPosition().x));
		float y = Math.min(GameWindow.MainWindow.getHeight() - 140 - (radius * 4), Math.max(radius, getPosition().y));
		setPosition(new Vector2(x,y));
		
		//Regenerate Sprint
		if(!Input.GetKey(KeyCode.Shift) && moveVector.magnitude() == 0) {
			sprintBar.increaseProgress(10*Time.getDeltaTimef());
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		//Draw the player
		super.draw(graphics);
		
		//Draw the compass if the player has one
		if(hasCompass) {
			
			HealthPickUp pickUp = PickUpManager.getHealthPickUp();
			float dx = pickUp.getPosition().x - position.x;
			float dy = pickUp.getPosition().y - position.y;
			
			Vector2 fromTo = new Vector2(dx,dy);
			fromTo.normalize();
			fromTo.multiply(12);
					
			graphics.setColor(new Color(255, 0, 0, 128));
			Graphics2D graphics2d = (Graphics2D) graphics;
			graphics2d.setStroke(new BasicStroke(3));
			graphics2d.drawLine((int)position.x, (int)position.y, (int)(position.x + fromTo.x), (int)(position.y + fromTo.y));
			graphics2d.setStroke(new BasicStroke(1));
			
			Coin bitcoin = PickUpManager.getBitCoin();
			
			dx = bitcoin.getPosition().x - position.x;
			dy = bitcoin.getPosition().y - position.y;
			
			fromTo = new Vector2(dx,dy);
			fromTo.normalize();
			fromTo.multiply(12);
					
			graphics.setColor(new Color(255, 165, 0, 128));
			graphics2d = (Graphics2D) graphics;
			graphics2d.setStroke(new BasicStroke(3));
			graphics2d.drawLine((int)position.x, (int)position.y, (int)(position.x + fromTo.x), (int)(position.y + fromTo.y));
			graphics2d.setStroke(new BasicStroke(1));
		}
	}
	
	//Set whether or not the player has a compass
	public void setHasCompass(boolean hasCompass) {
		this.hasCompass = hasCompass;
	}
	
	@Override
	public void onCollision(Collision collision) {
		
		//If the player is colliding with an enemy, take away health
		
		if(!collision.getOtherEntity().hasTag("Enemy")) {
			return;
		}
		
		healthBar.decreaseProgress(50 * Time.getDeltaTimef() * (1.0f / resistance));
		
		if(healthBar.getProgress() <= 0 ) {
			LevelManager.switchLevels("GameOver");
		
		}
	}
	
	//Accessors
	
	public void setSprintBoost(float amount) {
		speedBoost = amount;
	}
	
	public void setMaxBoost(float amount) {
		sprintBar.setMax(amount);
	}
	
	public void setMaxHealth(float amount) {
		healthBar.setMax(amount);
	}
	
	public float getMaxHealth() {
		return healthBar.getMax();
	}
	
	public float getHealth() {
		return healthBar.getProgress();
	}
	
	public void heal(float amount) {
		healthBar.increaseProgress(amount);
	}

	public void setResistance(float resistance) {
		this.resistance = resistance;
		
	}
}
