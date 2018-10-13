package physics;

import misc.Vector2;

public class Point {
	
	//The x and y components of the point
	public float x;
	public float y;
	
	//Creates a point from the x and y components
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	//Creates a point from a position vector
	public Point(Vector2 point) {
		this.x = point.x;
		this.y = point.y;
	}
	
}
