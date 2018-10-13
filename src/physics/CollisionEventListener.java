package physics;

public interface CollisionEventListener {
	
	//If a collision takes place you can subscribe a CollisionEventListener to a collidern and get a callback
	
	void onCollision(Collision collision);
}
