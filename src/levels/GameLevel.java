package levels;

import java.awt.Color;
import java.awt.Graphics;

import coreEngine.Debug;
import coreEngine.EntityManager;
import coreEngine.Input;
import coreEngine.Level;
import coreEngine.Time;
import game.MoneyManager;
import game.PickUpManager;
import game.Player;
import game.Score;
import game.WaveSpawnwer;
import graphics.GameWindow;
import gui.Store;
import gui.UIManager;
import physics.PhysicsManager;

public class GameLevel extends Level {

	private static Graphics graphics;
	
	private double timeSurvived = 0;
	
	public void Initialize() {
		
		EntityManager.Initialize();
		MoneyManager.Initialize();
		Store.Initialize();
		PhysicsManager.Initialize();
		WaveSpawnwer.Initialize();
		PickUpManager.Initialize();
		
		Score.loadHighScore();
		
		Score.resetScore();
		
		new Player();

	}
	
	public void Loop(Graphics g) {
		
		//Update and draw everything
		
		timeSurvived += Time.getDeltaTime();
		
		EntityManager.updateAllComponents();
		
		graphics = g;
		
		//Background
		graphics.setColor(new Color(30, 30, 30));
		graphics.fillRect(0, 0, GameWindow.MainWindow.getWidth(), GameWindow.MainWindow.getHeight());

		
		WaveSpawnwer.update();
		PhysicsManager.update();
		
		EntityManager.drawAllComponents(graphics);
		
		graphics.setColor(Color.green);

		
		graphics.setColor(new Color(0, 0, 0));
		graphics.fillRect(0, 550, GameWindow.MainWindow.getWidth(), 170);
		
		//Draw score
		graphics.setFont(graphics.getFont().deriveFont(15.0f));
		graphics.setColor(new Color(255, 255, 255,100));
		graphics.drawString("Score: " + Score.getScore(false), 10, 540);
		graphics.drawString("HighScore: " + Math.max(Score.getScore(false),Score.getHighScore()), 10, 520);
		
		PickUpManager.update();
		MoneyManager.Update();
		Store.update();
		Store.draw(graphics);
		UIManager.update();
		UIManager.draw(graphics);
		
		//Draw debug stuff
		graphics.setFont(graphics.getFont().deriveFont(12.0f));
		graphics.setColor(Color.green);
		Debug.drawFPS(graphics);

		//Update score
		Score.timeSurvived((float) timeSurvived);
		Score.setWavesSurvived(WaveSpawnwer.getCurrentWave());
		Input.Update();
		
		
	}
	
	public void Terminate() {
		EntityManager.terminate();
		PhysicsManager.terminate();
		UIManager.terminate();
	}
	
}
