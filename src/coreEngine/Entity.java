package coreEngine;

import java.awt.Graphics;
import java.util.ArrayList;

import components.Component;
import misc.Vector2;

//All Objects in the game (except GUI Objects) inherit from entity
public class Entity {
	
	//Position of the entity
	protected Vector2 position;
	//Tags this entity has
	protected ArrayList<String> tags;
	//List of the entities Component
	private ArrayList<Component> components;

	//Makes an entity at position position
	public Entity(Vector2 position) {
		this.position = position;
		components = new ArrayList<>();
		tags = new ArrayList<>();
		EntityManager.addEntity(this);
	}
	
	//Makes an entity at 0,0
	public Entity() {
		this(new Vector2(0,0));
	}
	
	//Makes an entity at x,y
	public Entity(float x, float y) {
		this(new Vector2(x,y));
	}
	
	//Moves the entity by moveVector
	public void translate(Vector2 moveVector) {
		position.x += moveVector.x;
		position.y += moveVector.y;
	}
	
	//Moves entity by x and y in the x and y direction respectivly
	public void translate(float x, float y) {
		position.x += x;
		position.y += y;
	}
	
	//Add a component to the entity
	public void addComponent(Component component) {
		components.add(component);
		component.setEntity(this);
		component.initialize();
	}
	
	//Returns the component of type classComponent, if there is not component it will return null
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> classComponent) {
		
		T ret = null;
		
		//Linear search to try to find the desired component
		for (int i = 0; i < components.size(); i++) {
			if(classComponent.isInstance(components.get(i)) ) {
				return (T) components.get(i);
			}
		}
		
		return ret;
		
	}
	
	//Returns whether or not the entity has the tag tag
	public boolean hasTag(String tag) {
		return tags.contains(tag);
	}
	
	//Adds a tag to the object
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	//Adds a list of tags
	public void addTags(String... tagsToAdd) {
		for (String string : tagsToAdd) {
			tags.add(string);
		}
	}
	
	//draws entity
	public void draw(Graphics graphics) {
		for (Component component : components) {
			component.draw(graphics);
		}
	}
	
	//updates entity
	public void update() {
		for (Component component : components) {
			
			component.update();
		}
	}
	
	//terminates entity
	public void terminate() {
		for (Component component : components) {
			component.terminate();
		}
		EntityManager.removeEntity(this);
	}
	
	//Accessors
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}
