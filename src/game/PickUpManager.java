package game;

import java.util.ArrayList;

import components.SpriteRenderer;
import misc.SpawnPosition;

public class PickUpManager {
	
	//The number of coins
	private static final int NUMBER_OF_COINS = 20;
	
	//The health pickup
	private static HealthPickUp healthPickUp;
	//The coins
	private static ArrayList<Coin> coins;
	//The bitcoin
	private static Coin bitCoin;
	
	public static void Initialize() {
		
		//Make the all the coins, the bitcoin and the health pack
		
		coins = new ArrayList<>();
		healthPickUp = new HealthPickUp(SpawnPosition.getValidPosition());
		
		bitCoin = new Coin(SpawnPosition.getValidPosition());
		bitCoin.getComponent(SpriteRenderer.class).setSprite("BitCoin");
		bitCoin.setWorth(5);
		
		for (int i = 0; i < NUMBER_OF_COINS; i++) {
			coins.add(new Coin(SpawnPosition.getValidPosition()));
		}
	}
	
	public static void update() {
		
		//Check if any pickup has been collected, if it has generate a new one
		if(healthPickUp.isDead()) {
			healthPickUp = new HealthPickUp(SpawnPosition.getValidPosition());
		}
		if(bitCoin.isDead()) {
			bitCoin = new Coin(SpawnPosition.getValidPosition());
			bitCoin.getComponent(SpriteRenderer.class).setSprite("BitCoin");
			bitCoin.setWorth(5);
		}
		
		for (int i = NUMBER_OF_COINS - 1; i >= 0; i--) {
			if(coins.get(i).isDead()) {
				coins.remove(i);
				coins.add(i,new Coin(SpawnPosition.getValidPosition()));
			}
		}
		
	}
	
	//Return the health pick up
	public static HealthPickUp getHealthPickUp() {
		return healthPickUp;
	}
	
	//Return the bitcoin
	public static Coin getBitCoin() {
		return bitCoin;
	}
	
}
