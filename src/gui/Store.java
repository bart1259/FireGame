package gui;

import java.awt.Graphics;

import coreEngine.Input;
import coreEngine.Input.KeyCode;
import game.MoneyManager;


//The store in game
public class Store{
	
	//All items in the store
	private static StoreItem[] storeItems = {new StoreItem("FireResistance", 0, new int[]{5,7,10,20,50} ),
			new StoreItem("MaxBoost", 1, new int[]{5,7,10,20,50}),
			new StoreItem("MaxHealth", 2, new int[]{5,10,20,40,80}),
			new StoreItem("Compass", 3, new int[]{15}),
			new StoreItem("MoneyMachine", 4, new int[]{5,10,20,40,80})};
	
	public static void Initialize() {
		
		StoreButtonListener storeButtonListener = new StoreButtonListener();
		
		//Init all store items
		for (StoreItem storeItem : storeItems) {
			storeItem.reset();
			storeItem.getButton().subscribeListener(storeButtonListener);
		}
		
	}
	
	public static void update() {
		
		//If the alpha keys are pressed, items are automatically bought
		if(Input.GetKey(KeyCode.Alpha1) && !storeItems[0].getButton().getDisabled())
			storeItems[0].getButton().click();
		if(Input.GetKey(KeyCode.Alpha2) && !storeItems[1].getButton().getDisabled())
			storeItems[1].getButton().click();
		if(Input.GetKey(KeyCode.Alpha3) && !storeItems[2].getButton().getDisabled())
			storeItems[2].getButton().click();
		if(Input.GetKey(KeyCode.Alpha4) && !storeItems[3].getButton().getDisabled())
			storeItems[3].getButton().click();
		if(Input.GetKey(KeyCode.Alpha5) && !storeItems[4].getButton().getDisabled())
			storeItems[4].getButton().click();
		
		//Check if an item can be bought
		for (StoreItem storeItem : storeItems) {
			if(storeItem.isLocked()) 
				continue;
			
			if(MoneyManager.hasMoney(storeItem.getCurrentPrice())) {
				storeItem.getButton().setDisabled(false);
			}else {
				storeItem.getButton().setDisabled(true);
			}
		}
	}
	
	public static void draw(Graphics graphics) {
		//draw the store items
		for (StoreItem storeItem : storeItems) {
			storeItem.draw(graphics);
		}
	}
	
	//Accessors
	
	public static StoreItem[] getSoreItems() {
		return storeItems;
	}

}
