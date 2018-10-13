package coreEngine;

import java.util.HashMap;

//Manages the levels
public class LevelManager {

	//A list of all the levels
	public static HashMap<String, Level> levels;
	
	//Reference to the current level
	private static Level currentLevel;
	
	//Returns the currentLevel
	public static Level getCurrentLevel() {
		return currentLevel;
	}
	
	//Switches levels to a new level with id of name
	public static void switchLevels(String name) {
		
		//If there is a current level (this isn't the first level loaded) terminate it first
		if(currentLevel != null)
			currentLevel.Terminate();
		
		currentLevel = levels.get(name);
		currentLevel.Initialize();
	}
	
	//Switches level to a new level
	public static void switchLevels(Level level) {
		
		//If there is a current level (this isn't the first level loaded) terminate it first
		if(currentLevel != null)
			currentLevel.Terminate();
		
		currentLevel = level;
		currentLevel.Initialize();
	}
	
	//Adds a level to the level dictionary as a key of name and a value of level
	public static void registerLevel(Level level, String name) {
		
		if(levels == null)
			levels = new HashMap<>();
		
		levels.put(name, level);
	}
	
	//Returns the level with a key of name
	public static Level getLevel(String name) {
		return levels.get(name);
	}
	
}
