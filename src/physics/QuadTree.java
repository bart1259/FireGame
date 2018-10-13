package physics;

import java.awt.Graphics;
import java.util.ArrayList;

public class QuadTree {
	
	//Boundary that this quadTree occupies	
	private AABB boundary;
	//A list of all the colliders that are in the quadtree
	private ArrayList<Collider> colliders;
	
	//The number of nodes in a tree before it splits
	private final int MAX_POINTS_PER_NODE = 4;
	
	//The Child trees, these are null if the tree has not split
	private QuadTree northWest = null;
	private QuadTree northEast = null;
	private QuadTree southWest = null;
	private QuadTree southEast = null;
	
	//Wheter or not the tree has subdivided or not
	private boolean subdivided = false;
	
	//Constructor that constructs a Quadtree with a boundary
	public QuadTree(AABB boundary) {
		
		//Init points array
		colliders = new ArrayList<Collider>();

		//Set boundary
		this.boundary = boundary;
	}
	
	//Inserts a collider into the quadtree
	public boolean insert(Collider collider) {
		
		//System.out.println(p.x + " " + p.y);
		
		//If the boundary doesnt contain the point then there is no point in adding it
		if(!boundary.containsPoint(new Point(collider.x, collider.y))) {
			return false;
		}
		
		//If we are in range then we add it to the list if there is room
		if(colliders.size() < MAX_POINTS_PER_NODE)
		{
			colliders.add(collider);
			return true;
		}
		
		//If there is no room, subdivide the tree if it is not divided already and then add it
		if(!subdivided)
		{
			subdivide();
		}
		
		//We try to add the collider to the child nodes
		if(northWest.insert(collider))
			return true;
		if(northEast.insert(collider))
			return true;
		if(southWest.insert(collider))
			return true;
		if(southEast.insert(collider))
			return true;
		
		//Point cant be added for some reason ?
		return false;
	}
	
	//Returns the number of colliders in the quadtree
	//Deprecated
	public int getNumberOfPoints() {
		
		
		int a = colliders.size();
		
		if(subdivided) {
			a += northWest.getNumberOfPoints();
			a += northEast.getNumberOfPoints();
			a += southEast.getNumberOfPoints();
			a += southWest.getNumberOfPoints();
			return a;
		}else {
			return a;
		}

	}
	
	//Subdivides the tree into four smaller trees
	public void subdivide() {
	
		float x = boundary.xCenter;
		float y = boundary.yCenter;
		float w = boundary.halfWidth;
		float h = boundary.halfHeight;
		
		//Make the new trees
		AABB nw = new AABB(x - (w/2), y + (h/2), w/2,h/2);
		northWest = new QuadTree(nw);
		AABB ne = new AABB(x + (w/2), y + (h/2), w/2,h/2);
		northEast = new QuadTree(ne);
		AABB sw = new AABB(x - (w/2), y - (h/2), w/2,h/2);
		southWest = new QuadTree(sw);
		AABB se = new AABB(x + (w/2), y - (h/2), w/2,h/2);
		southEast = new QuadTree(se);
		
		subdivided = true;
	}
	
	//Returns all the points in a range
	public ArrayList<Collider> query(AABB range) {
		
			//Init a list of colliders that will be returned
	    ArrayList<Collider> collidersInRange = new ArrayList<>();

			//Check if the range we are checking is in the boundary of this quadtree
	    if (!boundary.intersects(range))
	      return collidersInRange;

			//If we find a point then we add it to the list of colliders in range.\
	    for (int p = 0; p < colliders.size(); p++)
	    {
	      if (range.containsPoint(colliders.get(p).getCenterPoint()))
	    	  collidersInRange.add(colliders.get(p));
	    }

			//If we are not subdivided then return
	    if (northWest == null)
	      return collidersInRange;

			//If we are subdivided then add all child points to the list
	    collidersInRange.addAll(northWest.query(range));
	    collidersInRange.addAll(northEast.query(range));
	    collidersInRange.addAll(southWest.query(range));
	    collidersInRange.addAll(southEast.query(range));

	    return collidersInRange;
	}
	
	//Returns all the points in a circle with a center at (x,y) and a radius of r
	public ArrayList<Collider> queryCircle(float x, float y, float r) {
		
		//Find all points in a square that the circle is inscribed into
		ArrayList<Collider> colliders = query(new AABB(x,y,r,r));
		ArrayList<Collider> collidersInCircle = new ArrayList<Collider>();
		
		//Check if the the point also lies within the circle
		for (Collider collider : colliders) {
			
			float dx = collider.x - x;
			float dy = collider.y - y;
			
			if(Math.sqrt((dx * dx) + (dy * dy)) <= r) {
				collidersInCircle.add(collider);
			}
		}
		
		return collidersInCircle;
	}
	
	//Draw the Quadtree, only used for debugging
	public void visualize(Graphics graphics) {
		
		graphics.drawRect(Math.round(boundary.xCenter - boundary.halfWidth),
				Math.round(boundary.yCenter - boundary.halfHeight),
				Math.round(boundary.halfWidth*2),
				Math.round(boundary.halfHeight*2));
		
		if(subdivided) {
			northWest.visualize(graphics);
			northEast.visualize(graphics);
			southWest.visualize(graphics);
			southEast.visualize(graphics);
		}
	}
	
}