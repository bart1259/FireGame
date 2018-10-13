package gui;

import java.awt.Graphics;

import misc.Vector2;

//Base GUI Component
public abstract class GUIComponent implements Comparable<GUIComponent> {
	
	protected Vector2 position;
	protected Vector2 size;
	protected int layer;
	protected boolean isEnabled = true;
	
	public GUIComponent(Vector2 position,Vector2 size) {
		this.position = position;
		this.size = size;
		UIManager.registerNewComponent(this);
	}
	
	public GUIComponent(float x, float y, float w, float h) {
		this(new Vector2(x,y),new Vector2(w,h));
	}

	//Accessors
	
	public boolean getEnabled() {
		return isEnabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.isEnabled = enabled;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void setPosition(float x, float y) {
		this.position = new Vector2(x,y);
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
	
	@Override
	public int compareTo(GUIComponent other){
		
		return layer - other.layer;
		
    }
	
	public abstract void draw(Graphics graphics);
	
	public void update() {
		
	}
}
