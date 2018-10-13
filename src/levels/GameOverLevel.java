package levels;

import java.awt.Color;
import java.awt.Graphics;

import coreEngine.Input;
import coreEngine.Level;
import coreEngine.LevelManager;
import game.Score;
import gui.GameButton;
import gui.GameButtonListener;
import gui.UIManager;

public class GameOverLevel extends Level implements GameButtonListener{

	GameButton replayButton;
	GameButton mainMenuButton;
	GameButton quitButton;
	
	public void Initialize() {
		
		
		//Make all the game over buttons
		replayButton = new GameButton(520, 400, 240, 50, "Replay"); 
		mainMenuButton = new GameButton(520, 460, 240, 50, "Back to Menu"); 
		quitButton = new GameButton(520, 520, 240, 50, "Rage Quit!"); 
		
		replayButton.subscribeListener(this);
		mainMenuButton.subscribeListener(this);
		quitButton.subscribeListener(this);
	}

	public void Loop(Graphics graphics) {
		
		//Draw all the buttons and text
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 1280, 720);
		
		graphics.setColor(new Color(100, 100, 100));
		graphics.fillRect(200, 100, 880, 500);
		
		graphics.setColor(Color.WHITE);
		graphics.setFont(graphics.getFont().deriveFont(50.0f));
		int stringWidth = graphics.getFontMetrics().stringWidth("Game Over!");
		
		graphics.drawString("Game Over!",640 - (stringWidth/2), 150);
		
		graphics.setFont(graphics.getFont().deriveFont(25.0f));
		graphics.drawString("Waves Survived: ", 300, 200);
		graphics.drawString("Time Survived: ", 300, 230);
		graphics.drawString("Coins Collected: ", 300, 260);
		
		graphics.drawString(Score.getWavesSurvived() + "", 900 - graphics.getFontMetrics().stringWidth(Score.getWavesSurvived() + ""), 200);
		graphics.drawString(Math.round(Score.getTimeSurvived()) + "", 900 - graphics.getFontMetrics().stringWidth(Math.round(Score.getTimeSurvived()) + ""), 230);
		graphics.drawString(Score.getCoinsCollected() + "", 900 - graphics.getFontMetrics().stringWidth(Score.getCoinsCollected() + ""), 260);
		
		graphics.drawString("Score: ", 300, 320);
		graphics.drawString(Score.getScore(true) + "", 900 - graphics.getFontMetrics().stringWidth(Score.getScore(true) + ""), 320);
		
		graphics.drawString("High Score: ", 300, 350);
		graphics.drawString(Score.getHighScore() + "", 900 - graphics.getFontMetrics().stringWidth(Score.getHighScore() + ""), 350);
		
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
		
		if(button == quitButton) {
			System.exit(0);
		} else if (button == mainMenuButton){
			Input.MouseMoved(0, 0);
			LevelManager.switchLevels("MainMenu");
		}else if (button == replayButton){
			Input.MouseMoved(0, 0);
			LevelManager.switchLevels(new GameLevel());
		}
		
		
	}

}
