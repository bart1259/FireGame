package components;

import physics.CircleCollider;
import physics.CollisionEventListener;
import physics.PhysicsManager;

public class CircleColliderComponent extends Component{
	
	//The registered collider in the physics manager
	private CircleCollider circleCollider;

	
	public CircleColliderComponent() {

	}
	
	public void initialize() {
		super.initialize();
		circleCollider = new CircleCollider(entity.getPosition().x, entity.getPosition().y, 1,this);
	}
	
	//Sets the radius of the circle collider
	public void setRadius(float radius) {
		if(circleCollider != null)
			PhysicsManager.removeCollider(circleCollider);
		//Reconstructs the circle collider
		circleCollider = new CircleCollider(entity.getPosition().x, entity.getPosition().y, radius,this);
	}
	
	//Add a collision listener to the collider
	public void subscribeCollisionListener(CollisionEventListener listener) {
		circleCollider.subscribeCollisionListener(listener);
	}
	
	public void update() {
		super.update();
		//Update the position of the circle collider
		circleCollider.setPosition(entity.getPosition().x, entity.getPosition().y);
	}
	
	public void terminate() {
		super.terminate();
		//Once the entity is dead we want to unregister the collider
		PhysicsManager.removeCollider(circleCollider);
	}
	
	//Return the collider, Accessor
	public CircleCollider getCollider() {
		return circleCollider;
	}
	
}
