package physics;

import java.awt.Graphics;
import java.util.ArrayList;

import graphics.GameWindow;

public class PhysicsManager {

	static QuadTree collisionTree;
	static ArrayList<CircleCollider> colliders;
	
	public static void Initialize() {
		collisionTree = new QuadTree(new AABB(GameWindow.MainWindow.getWidth()/2,(GameWindow.MainWindow.getHeight()/2) - 170,GameWindow.MainWindow.getWidth()/2,GameWindow.MainWindow.getHeight()/2));
		colliders = new ArrayList<CircleCollider>();
	}
	
	public static void drawQuadTree(Graphics graphics) {
		collisionTree.visualize(graphics);
	}
	
	public static void drawColliders(Graphics graphics) {
		for (CircleCollider collider : colliders) {
			graphics.drawOval(Math.round(collider.x - collider.r), Math.round(collider.y - collider.r), Math.round(collider.r*2), Math.round(collider.r*2));
		}
	}
	
	public static void update() {
		
		//Construct a new quad tree
		collisionTree = new QuadTree(new AABB(GameWindow.MainWindow.getWidth()/2,GameWindow.MainWindow.getHeight()/2,GameWindow.MainWindow.getWidth()/2,GameWindow.MainWindow.getHeight()/2));
		for (int i = 0; i < colliders.size(); i++) {
			//Clear all collisions
			colliders.get(i).clearCollisions();
			collisionTree.insert(colliders.get(i));
		}
		
		//Check collisions
		
		for (int i = 0; i < colliders.size(); i++) {
			CircleCollider collider = colliders.get(i);
			ArrayList<Collider> possibleColliders = collisionTree.query(new AABB(collider.x, collider.y, collider.r * 2, collider.r * 2));
			if(possibleColliders.size() > 1) {
				
				//We are potentially colliding
				for (int j = 0; j < possibleColliders.size(); j++) {
					
					//Make sure we are not colliding with ourselves
					if(possibleColliders.get(j) == collider) {
						continue;
					}
					
					if(precislyColliding(collider,possibleColliders.get(j))) {
						Collision collision1 = new Collision(collider, possibleColliders.get(j),collider.getColliderComponent().getEntity(),possibleColliders.get(j).getColliderComponent().getEntity());
						collider.collided(collision1);
						Collision collision2 = new Collision(possibleColliders.get(j), collider,possibleColliders.get(j).getColliderComponent().getEntity(),collider.getColliderComponent().getEntity());
						possibleColliders.get(j).collided(collision2);
					}
				}
			}
		}
	}
	
	public static boolean precislyColliding(Collider collider1, Collider collider2) {
		
		//Check if we are precisely colliding
		//Assumes colliders are circle colliders
		
		CircleCollider circle1 = (CircleCollider)collider1;
		CircleCollider circle2 = (CircleCollider)collider2;
		
		float dx = circle1.x - circle2.x;
		float dy = circle1.y - circle2.y;
		float dr = circle1.r + circle2.r;
		
		return ((dx*dx)+(dy*dy) < (dr*dr));
	}
	
	public static void terminate() {
		colliders.clear();
	}
	
	public static void addCollider(CircleCollider collider) {
		colliders.add(collider);
	}
	
	public static void removeCollider(CircleCollider collider) {
		colliders.remove(collider);
	}
	
}
