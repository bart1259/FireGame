package physics;

import components.CircleColliderComponent;
import misc.Vector2;

public class CircleCollider extends Collider{
	
	//The radisu of the circle collider	
	public float r;
	
	//Set the position of the Circle Collider from a vector
	public void setPosition(Vector2 position) {
		this.x = position.x;
		this.y = position.y;
	}
	
	//Set the position of the Circle Collider from the components
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	//Constructor of the circle collider, takes the position and radius of the circle as well as the collider component refrence.
	public CircleCollider(float x, float y, float r, CircleColliderComponent colliderComponent) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		
		colliderComp = colliderComponent;
		
		//Register the collider with the Physics Manager.
		PhysicsManager.addCollider(this);
	}
	
}
