package coreEngine;

import java.awt.Graphics;
import java.util.ArrayList;

//Entity Manager is responsible for updating and drawing all entities
public class EntityManager {
	
	//All the entities in the scene
	public static ArrayList<Entity> entities;

	public static void Initialize() {
		entities = new ArrayList<>();
	}
	
	//Update all entities components
	public static void updateAllComponents() {
		
		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).update();
		}

	}
	
	//Draw all entities components
	public static void drawAllComponents(Graphics graphics) {
		for (Entity entity : entities) {
			entity.draw(graphics);
		}
	}
	
	//Destroy all entities
	public static void terminate() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).terminate();
		}
		entities.clear();
	}
	
	//Add an entity
	public static void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	//Remove an entity
	public static void removeEntity(Entity entity) {
		entities.remove(entity);
	}
}
