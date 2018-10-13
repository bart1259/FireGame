package gui;

import java.awt.Color;
import java.awt.Graphics;

//A progress bar (Health bar, loading bar)
public class ProgressBar extends GUIComponent {
	
	//The progress made
	private float progress;
	//Max progress
	private float max;
	private Color textColor = new Color(255, 255, 255);
	private Color barColor = new Color(255, 0, 0, 128);
	
	public ProgressBar(float x, float y, float w, float h, float max) {
		super(x, y, w, h);
		this.progress = 0;
		this.max = max;
	}
	
	public void draw(Graphics graphics) {
		
		float displayProg = Math.round(progress * 10) / 10.0f;
		
		int stringWidth = graphics.getFontMetrics().stringWidth(displayProg + "/" + max);
		
		//Draw bar
		graphics.setColor(barColor);		
		graphics.fillRect((int)position.x, (int)position.y, (int)(size.x * (progress/max)), (int)size.y);
		
		//Draw text
		graphics.setFont(graphics.getFont().deriveFont(12.0f));
		graphics.setColor(textColor);
		if(!new Color(255, 0, 0, 128).equals(barColor)) {
			graphics.drawString(displayProg + "/" + max, (int)(position.x + (size.x/2) - (stringWidth/2)) , (int)(position.y + (2*size.y/3) ));
		}else {
			graphics.drawString(displayProg + "/" + max, (int)(position.x + (size.x/2) - (stringWidth/2)) + 20 , (int)(position.y + (2*size.y/3) ));
		}
	}
	
	//Accessors
	
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	public void setBarColor(Color barColor) {
		this.barColor = barColor;
	}
	
	
	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	public void increaseProgress(float amount) {
		progress += amount;
		progress = Math.min(max, progress);
	}
	
	public void decreaseProgress(float amount) {
		progress -= amount;
		progress = Math.max(0, progress);
	}
	
	public void setMax(float max) {
		this.max = max;
	}
	
	public float getMax() {
		return max;
	}
	
	public float getProgress() {
		return progress;
	}
	
}
