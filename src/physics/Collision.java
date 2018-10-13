package physics;

import coreEngine.Entity;

public class Collision {
	
	//This collider	
	private Collider thisCollider;
	//The other collider that is being collided
	private Collider otherCollider;
	//The entity that this collider belongs to
	private Entity thisEntity;
	//The entity that the other collider belongs to
	private Entity otherEntity;
	
	//Makes a Colllsiosn from this collider and entity and from the other collider and entity
	public Collision(Collider thisCollider, Collider otherCollider, Entity thisEntity, Entity otherEntity) {
		this.thisCollider = thisCollider;
		this.otherCollider = otherCollider;
		this.thisEntity = thisEntity;
		this.otherEntity = otherEntity;
	}

	//Accessors
	public Entity getThisEntity() {
		return thisEntity;
	}
	
	public Entity getOtherEntity() {
		return otherEntity;
	}
	
	public Collider getThisCollider() {
		return thisCollider;
	}
	
	public Collider getOtherCollider() {
		return otherCollider;
	}
}
