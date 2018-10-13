package main;

import coreEngine.Debug;
import coreEngine.Input;
import coreEngine.LevelManager;
import coreEngine.ResourceManager;
import coreEngine.Time;
import graphics.GameWindow;
import gui.UIManager;
import levels.CreditsLevel;
import levels.GameLevel;
import levels.GameOverLevel;
import levels.HowToPlayLevel;
import levels.MainMenuLevel;

public class Launcher {
	
	public static void Launch() {
	
		//Intitialize all core engine managers
		Input.Initialize();
		Time.Initialize();
		ResourceManager.Initialize();
		Debug.Initialize();
		UIManager.Initialize();
		
		
		//Register new levels to to the level manager
		LevelManager.registerLevel(new MainMenuLevel(), "MainMenu");
		LevelManager.registerLevel(new GameLevel(), "GameLevel");
		LevelManager.registerLevel(new CreditsLevel(), "CreditsLevel");
		LevelManager.registerLevel(new GameOverLevel(), "GameOver");
		LevelManager.registerLevel(new HowToPlayLevel(), "HowToPlay");
		LevelManager.switchLevels("MainMenu");
		
		//Make the Game Window
		new GameWindow(1280, 720);
	}
	
	public static void main(String[] args) {
		
		//Launch the application
		Launch();
	}
	
}
