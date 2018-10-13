package gui;

import game.MoneyManager;
import game.Player;

public class StoreButtonListener implements GameButtonListener{

	//Upgrade values
	float[] maxHealths = {110,120,130,140,150};
	float[] maxSprints = {110,125,145,160,190};
	float[] fireResitances = {1.1f,1.2f,1.4f,1.7f,2.0f};
	
	//Button press callback
	@Override
	public void buttonPressed(GameButton button) {
		
		if(button == Store.getSoreItems()[0].getButton()) {
			fireResitanceBought(Store.getSoreItems()[0]);
		}else if (button == Store.getSoreItems()[1].getButton()) {
			maxBoostBought(Store.getSoreItems()[1]);
		}else if (button == Store.getSoreItems()[2].getButton()) {
			maxHealthBought(Store.getSoreItems()[2]);
		}else if (button == Store.getSoreItems()[3].getButton()) {
			compassBought(Store.getSoreItems()[3]);
		}else if (button == Store.getSoreItems()[4].getButton()) {
			moneyMachineBought(Store.getSoreItems()[4]);
		}
		
	}
	
	//Callbacks for each idividual buttons, they upgrade what they are supposed to
	//Upgrade and then check if they shoudl be locked or still buyable
	
	private void fireResitanceBought(StoreItem storeItem) {
		Player.player.setResistance(fireResitances[storeItem.getTimesBought()]);
		storeItem.bought();
		
		if(storeItem.isLocked())
		{
			storeItem.getButton().setText("Bought");
			storeItem.getButton().setDisabled(true);
		}
	}

	private void maxBoostBought(StoreItem storeItem) {
		Player.player.setMaxBoost(maxSprints[storeItem.getTimesBought()]);
		storeItem.bought();
		
		if(storeItem.isLocked())
		{
			storeItem.getButton().setText("Bought");
			storeItem.getButton().setDisabled(true);
		}
	}

	private void maxHealthBought(StoreItem storeItem) {
		Player.player.setMaxHealth(maxHealths[storeItem.getTimesBought()]);
		storeItem.bought();
		
		if(storeItem.isLocked())
		{
			storeItem.getButton().setText("Bought");
			storeItem.getButton().setDisabled(true);
		}
	}
	
	private void compassBought(StoreItem storeItem) {
		
		Player.player.setHasCompass(true);
		
		storeItem.bought();
		
		if(storeItem.isLocked())
		{
			storeItem.getButton().setText("Bought");
			storeItem.getButton().setDisabled(true);
		}
	}
	
	private void moneyMachineBought(StoreItem storeItem) {
		storeItem.bought();
		MoneyManager.buyMoneyMachine();
		
		if(storeItem.isLocked())
		{
			storeItem.getButton().setText("Bought");
			storeItem.getButton().setDisabled(true);
		}
	}
	
}
