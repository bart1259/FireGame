package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import coreEngine.Input;
import coreEngine.Level;
import coreEngine.LevelManager;
import coreEngine.ResourceManager;
import gui.GameButton;
import gui.GameButtonListener;
import gui.UIManager;

public class MainMenuLevel extends Level implements GameButtonListener{

	private GameButton startButton;
	private GameButton instructionButton;
	private GameButton exitButton;
	private GameButton creditsButton;
	
	private Image logoImage;
	
	public void Initialize() {
		
		//Make all the buttons
		
		logoImage = ResourceManager.GetImage("Logo");
		
		startButton = new GameButton(560, 300, 260, 50, "Start Game");
		instructionButton = new GameButton(560, 380, 260, 50, "How to Play");
		creditsButton = new GameButton(560, 460, 260, 50, "Credits");
		exitButton = new GameButton(560, 540, 260, 50, "Exit Game");
		
		startButton.subscribeListener(this);
		instructionButton.subscribeListener(this);
		creditsButton.subscribeListener(this);
		exitButton.subscribeListener(this);
	}

	public void Loop(Graphics graphics) {
		
		//Update and draw the main menu buttons
		graphics.setColor(new Color(68, 22, 22));
		graphics.fillRect(0, 0, 1280, 720);
		
		//Draw the logo
		graphics.drawImage(logoImage, 640 -350, -20, null);
		
		UIManager.update();
		UIManager.draw(graphics);
		
		Input.Update();
	}

	public void Terminate() {
		UIManager.terminate();
	}

	@Override
	public void buttonPressed(GameButton button) {
		
		//Button logic
		if(button == startButton) {
			LevelManager.switchLevels(new GameLevel());
		}else if(button == exitButton) {
			System.exit(0);
		}else if(button == creditsButton) {
			LevelManager.switchLevels("CreditsLevel");
		}else if(button == instructionButton) {
			LevelManager.switchLevels("HowToPlay");
		}
		
	}

}
