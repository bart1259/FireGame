package components;

import java.awt.Graphics;

import coreEngine.Entity;

//Base component that all components inherit from
public class Component {
	
	//The entity that the component belongs to
	protected Entity entity;
	
	//Accessors 
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	//End Accessors
	
	//Optional methods that components can be overidden.
	public void initialize() {}
	
	public void update() {}
	
	public void draw(Graphics graphics) {}
	
	public void terminate() {}
	
	
}
