package game.enemies;

import java.util.Random;

import components.SpriteRenderer;
import coreEngine.Time;
import misc.Vector2;

public class ShooterFire extends Enemy{
	
	//Speed of the bullets shot
	private static int BULLET_SPEED = 400;
	//How often the tower shoots
	private static double FIRE_SHOOT_RATE = 4.5;
	//The time left until the tower shoots
	private double timeUntilShoot;
	
	public ShooterFire(Vector2 position, int speedX, int speedY) {
		super(position, speedX, speedY);
		
		getComponent(SpriteRenderer.class).setSprite("BrownFire");
		
		timeUntilShoot = FIRE_SHOOT_RATE;	
	}
	
	@Override
	protected Vector2 doAI() {
	
		//If it is time to shoot then we shoot
		timeUntilShoot -= Time.getDeltaTime();
		
		if(timeUntilShoot <= 0) {
			timeUntilShoot = FIRE_SHOOT_RATE;
			shootFire();
		}
		
		
		return new Vector2(speedX * Time.getDeltaTimef(), speedY * Time.getDeltaTimef());
	}
	
	//Shoot fire
	private void shootFire() {
		
		//Pick a random direction and shoot in all directions orthogonal to this direction
		Random random = new Random(System.currentTimeMillis());
		float rotation = random.nextFloat() * 360;
		
		new BulletFire(position.x,position.y,Math.round((float)Math.cos(Math.toRadians(rotation)) * BULLET_SPEED), Math.round((float)Math.sin(Math.toRadians(rotation)) * BULLET_SPEED));
		rotation += 90;
		new BulletFire(position.x,position.y,Math.round((float)Math.cos(Math.toRadians(rotation)) * BULLET_SPEED), Math.round((float)Math.sin(Math.toRadians(rotation)) * BULLET_SPEED));
		rotation += 90;
		new BulletFire(position.x,position.y,Math.round((float)Math.cos(Math.toRadians(rotation)) * BULLET_SPEED), Math.round((float)Math.sin(Math.toRadians(rotation)) * BULLET_SPEED));
		rotation += 90;
		new BulletFire(position.x,position.y,Math.round((float)Math.cos(Math.toRadians(rotation)) * BULLET_SPEED), Math.round((float)Math.sin(Math.toRadians(rotation)) * BULLET_SPEED));
		
	}
}
