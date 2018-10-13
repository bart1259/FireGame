package physics;

//A class that represents an Axis Alligned Bounding BOx
public class AABB {
	
	//The center of the AABB
	public float xCenter;
	public float yCenter;
	//The Width and Height of the AABB
	public float halfWidth;
	public float halfHeight;
	
	//A constructor that makes the AABB from the center point, width and height
	public AABB(float xCen, float yCen, float hWidth, float hHeight) {
		xCenter = xCen;
		yCenter = yCen;
		halfWidth = hWidth;
		halfHeight = hHeight;
	}
	
	//Returns whether or not a point is inside the AABB
	public boolean containsPoint(Point p) {
		
		return (p.x <= xCenter + halfWidth 
				&& p.x >= xCenter - halfWidth
				&& p.y <= yCenter + halfHeight
				&& p.y >= yCenter - halfHeight);
	}
	
	//Returns wheter or not this AABB intersects with another AABB
	public boolean intersects(AABB other) {
		
		return (xCenter - halfWidth < other.xCenter + other.halfWidth)
				&& (xCenter + halfWidth > other.xCenter - other.halfWidth)
				&& (yCenter - halfHeight < other.yCenter + other.halfHeight)
				&& (yCenter + halfHeight > other.yCenter - other.halfHeight);
	}
}
