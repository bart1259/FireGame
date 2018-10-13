package misc;

public class Vector2 {

	public static Vector2 zero = new Vector2(0,0);
	public float x, y;
	
	public Vector2() {
		this(0, 0);
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void multiply(float a) {
		this.x *= a;
		this.y *= a;
	}
	
	public float magnitude() {
		return (float) Math.sqrt((x*x) + (y*y));
	}
	
	public void normalize() {
		
		float mag = magnitude();
		
		this.x /= mag;
		this.y /= mag;
	}
}
