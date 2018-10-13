package levels;

import java.awt.Color;
import java.awt.Graphics;

import coreEngine.Input;
import coreEngine.Level;
import coreEngine.LevelManager;
import gui.GameButton;
import gui.GameButtonListener;
import gui.UIManager;

public class HowToPlayLevel extends Level implements GameButtonListener{

	GameButton menuButton;
	Graphics graphics;
	int offset = 100;
	
	@Override
	public void Initialize() {
		menuButton = new GameButton(550, 600, 260, 50, "Back to Main Menu");
		menuButton.subscribeListener(this);
	}

	@Override
	public void Loop(Graphics graphics) {
		
		this.graphics = graphics;
		
		currentLine = 0;
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 1280, 720);
		
		drawText("1. Avoid the fires with WASD or arrow keys", 20.0f);
		drawText("2. Collect the coins", 20.0f);
		drawText("3. Collect the bitcoins", 20.0f);
		drawText("4. Buy upgrades (Can use number keys)", 20.0f);
		drawText("5. Survive for very long", 20.0f);
		drawText("6. Beat your high score", 20.0f);
		
		UIManager.update();
		UIManager.draw(graphics);
		
		Input.Update();
	}

	int currentLine = 0;
	
	private void drawText( String text, float size) {
		
		int spacing = 5;
		
		graphics.setColor(Color.white);
		graphics.setFont(graphics.getFont().deriveFont(size));
		
		int textWidth = graphics.getFontMetrics().stringWidth(text);
		graphics.drawString(text, 640 - (textWidth/2), offset + ((spacing + (int)size) + (int)currentLine));
		
		currentLine += size + spacing;
	}
	
	@Override
	public void Terminate() {
		
		
		UIManager.terminate();
	}

	@Override
	public void buttonPressed(GameButton button) {
		if(button == menuButton) {
			LevelManager.switchLevels("MainMenu");
		}
		
	}

}
