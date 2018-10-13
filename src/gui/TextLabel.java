package gui;

import java.awt.Color;
import java.awt.Graphics;

//Text on the GUI
public class TextLabel extends GUIComponent{
	
	String text;
	Color textColor = Color.white;
	float textSize = 12.0f;
	boolean drawOutLine = true;
	
	public TextLabel(float x, float y, float w, float h) {
		super(x, y, w, h);
		
	}

	//Draws the text label
	@Override
	public void draw(Graphics graphics) {
		
		graphics.setColor(textColor);
		graphics.setFont(graphics.getFont().deriveFont(textSize));
		
		int stringWidth = graphics.getFontMetrics().stringWidth(text);
		
		if(drawOutLine)
			graphics.drawRect((int)position.x, (int)position.y, (int)size.x, (int)size.y);
		graphics.drawString(text, (int)(position.x + (size.x/2) - (stringWidth/2)),(int) (position.y + (2*size.y/3)));
		
	}
	
	//Accessors
	
	public void setDrawOutLine(boolean outline) {
		drawOutLine = outline;
	}
	
	public void setTextSize(float size) {
		textSize = size;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
}
