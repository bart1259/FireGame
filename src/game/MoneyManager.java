package game;

import coreEngine.Time;
import gui.TextLabel;

//Class that manages the player's money
public class MoneyManager {
	
	//The money the player has
	private static float money = 0;
	//The text label displaying the players money
	private static TextLabel label;
	//The number of money machines the player has bought
	private static int numberOfMachines = 0;
	
	public static void Initialize() {
		money = 0;
		numberOfMachines = 0;
		
		//Make the text label
		label = new TextLabel(10, 600, 150, 20);
		label.setText("Money: ");
	}
	
	public static void Update() {
		label.setText("Money: " + (int) Math.floor( (double)money));
		
		money += (numberOfMachines * Time.getDeltaTime() * 0.05f);
	}
	
	//Returns how much money the player has
	public static int getMoney() {
		return (int) Math.floor( (double)money);
	}
	
	//Increases the amount of money the player has by amount
	public static void increaseMoney(int amount) {
		money += amount;
	}
	//Decreases the amount of money the player has by amount
	public static void decreaseMoney(int amount) {
		money -= amount;
	}
	
	//Set the amount of money the player has to money
	public static void setMoney(int amount) {
		money = amount;
	}
	
	//returns whether or not the player has more money than amount
	public static boolean hasMoney(int amount) {
		return (money >= amount);
	}
	
	//Purchases a money machine
	public static void buyMoneyMachine() {
		numberOfMachines++;
	}
	
}
