package levels;

import java.awt.Color;
import java.awt.Graphics;

import coreEngine.Input;
import coreEngine.Level;
import coreEngine.LevelManager;
import coreEngine.Time;
import coreEngine.Input.KeyCode;
import gui.UIManager;

public class CreditsLevel extends Level{

	private Graphics graphics;
	private float creditsOffset = 700;
	
	@Override
	public void Initialize() {
		creditsOffset = 700;
	}

	@Override
	public void Loop(Graphics graphics) {
		
		this.graphics =graphics;
		
		//displays the credits
		
		creditsOffset -= Time.getDeltaTimef() * 100;
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 1280, 720);
		
		currentLine = 0;
		drawText("Programmers", 30.0f);
		drawText("Bart G.", 20.0f);
		drawText("",30.0f);
		drawText("Art",30.0f);
		drawText("Bart G.",20.0f);
		drawText("Yiyang C.",20.0f);
		drawText("",30.0f);
		drawText("Beta Testers",30.0f);
		drawText("Yiyang C.",20.0f);
		drawText("Pranav Y.",20.0f);
		drawText("Ben C.",20.0f);
		drawText("Micheal R.",20.0f);
		drawText("Edward M.",20.0f);
		
		UIManager.update();
		UIManager.draw(graphics);
		
		Input.Update();
		
		//If the credits are done, exit
		if(creditsOffset < -385) {
			LevelManager.switchLevels("MainMenu");
		}
		
		//If escape is pressed go back to main menu
		if(Input.GetKey(KeyCode.Escape)) {
			LevelManager.switchLevels("MainMenu");
		}
	}

	@Override
	public void Terminate() {
		UIManager.terminate();
		
	}

	private float currentLine = 0;
	
	//Draws text in sequencial order
	private void drawText( String text, float size) {
		
		int offset = Math.round(creditsOffset);
		int spacing = 5;
		
		graphics.setColor(Color.white);
		graphics.setFont(graphics.getFont().deriveFont(size));
		
		int textWidth = graphics.getFontMetrics().stringWidth(text);
		graphics.drawString(text, 640 - (textWidth/2), offset + ((spacing + (int)size) + (int)currentLine));
		
		currentLine += size + spacing;
	}
	
}
