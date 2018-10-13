package physics;

import java.util.ArrayList;

import components.CircleColliderComponent;

//A general collider
public class Collider {
	
	//The location of this collider
	public float x;
	public float y;
	
	//A list of all other collidiers colliding with this collider
	private ArrayList<Collider> collidingWith;
	//A list of all sbscribed Collision Event Listeners
	private ArrayList<CollisionEventListener> listeners;
	//The Circle Collider Component, Hard coded
	protected CircleColliderComponent colliderComp;
	
	//Reurns the center point of the collider
	public Point getCenterPoint() {
		return new Point(x, y);
	}
	
	//Constructs a new Collider
	public Collider() {
		listeners = new ArrayList<>();
		collidingWith = new ArrayList<>();
	}
	
	//Returns the collider component of this Collider
	public CircleColliderComponent getColliderComponent() {
		return colliderComp;
	}
	
	//Clears all collisions, typically called at the end of every frame
	public void clearCollisions() {
		collidingWith.clear();
	}
	
	/**
	  Do not call, called by the physics engine when collider collides
	 */
	public void collided(Collision collision) {
		
		if(collidingWith.contains(collision.getOtherCollider())) {
			return;
		}
		
		for (CollisionEventListener listener : listeners) {
			listener.onCollision(collision);
		}
	}
	
	//Subscribes a listener to this collider 
	public void subscribeCollisionListener(CollisionEventListener listener) {
		listeners.add(listener);
	}
}
