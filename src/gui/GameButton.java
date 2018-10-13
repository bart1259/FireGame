package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import coreEngine.Input;
import coreEngine.Time;
import coreEngine.Input.MouseCode;
import physics.AABB;
import physics.Point;

//A button that can be pressed
public class GameButton extends GUIComponent{

	private AABB boundry;
	
	private Color baseColor = Color.orange;
	private Color pressedColor = new Color(79, 48, 0);
	private Color disabledColor = new Color(136, 107, 41);
	private Color textColor = Color.black;
	private float textSize = 20;
	
	private String text = "Button";
	private boolean pressed = false;
	private boolean disabled = false;
	
	private static final double PRESS_TIME = 0.125;
	private double pressTimeLeft = 0;
	private ArrayList<GameButtonListener> listeners;
	
	public GameButton(float x, float y, float w, float h, String text) {
		super(x, y, w, h);
		
		this.text = text;
		
		listeners = new ArrayList<>();
		boundry = new AABB(x + (w/2),y + (h) ,w/2,h/2);
	}

	@Override
	public void update() {
		super.update();
		
		pressed = false;
		
		if(disabled)
			return;
		
		//Check if the button is pressed
		if(boundry.containsPoint(new Point(Input.getMousePosition())) && Input.getMouseButtonDown(MouseCode.left)) {
			//If it is notify all listeners
			pressed = true;
			pressTimeLeft = PRESS_TIME;
			for (GameButtonListener gameButtonListener : listeners) {
				gameButtonListener.buttonPressed(this);
			}
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		
		
		//Draw the button based on its current state
		pressTimeLeft -= Time.getDeltaTime();
		
		if(disabled)
			graphics.setColor(disabledColor);
		else if(pressed || pressTimeLeft > 0)
			graphics.setColor(pressedColor);
		else
			graphics.setColor(baseColor);
		
		graphics.fillRect((int)position.x,(int) (position.y),(int) size.x,(int) size.y);
		
		graphics.setColor(textColor);
		graphics.setFont(graphics.getFont().deriveFont(textSize));
		
		int stringWidth = graphics.getFontMetrics().stringWidth(text);		
		graphics.drawString(text,(int) (position.x + (size.x/2) - (stringWidth/2)), (int)(position.y + (2 * size.y / 3)));
		
	}

	//Fake a button click
	public void click() {
		pressed = true;
		pressTimeLeft = PRESS_TIME;
		for (GameButtonListener gameButtonListener : listeners) {
			gameButtonListener.buttonPressed(this);
		}
	}
	
	//Accessors
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void subscribeListener(GameButtonListener listener) {
		listeners.add(listener);
	}
	
	public boolean getDisabled() {
		return disabled;
	}
	
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}

	public void setPressedColor(Color pressedColor) {
		this.pressedColor = pressedColor;
	}

	public void setDisabledColor(Color disabledColor) {
		this.disabledColor = disabledColor;
	}
	
}
