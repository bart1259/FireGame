package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import coreEngine.ResourceManager;
import game.MoneyManager;

public class StoreItem {

	//The image to display
	private Image image;
	//The index of the item
	private int index;
	
	//GUI drawing values
	private static final int OFFSETX = 190;
	private static final int OFFSETY = 560;
	private static final int SPACING = 220;
	private static final int WIDTH = 180;
	private static final int HEIGHT = 120;
	
	private boolean locked;
	private int timesBought = 0;
	private int price = -1;
	
	private GameButton button;
	
	private int[] prices;
	
	public StoreItem(String imageName, int index, int[] prices) {
		
		this.prices = prices;
		this.index = index;
		
		image = ResourceManager.GetImage(imageName);
		
	}
	
	//Resets the purchases made
	public void reset() {
		timesBought = 0;
		price = prices[0];
		locked = false;
		
		button = new GameButton(OFFSETX + (index * SPACING), 650, WIDTH, 30, "Buy " + price + "p");
	}
	
	//Buys the item
	public void bought() {
		
		//Takes away money
		MoneyManager.decreaseMoney(prices[timesBought]);
		
		//Register the item was bought
		timesBought++;
		
		if(timesBought >= prices.length) {
			locked = true;
			return;
		}
		
		price = prices[timesBought];
		
		button.setText("Buy " + price + "p");
	}
	
	//Draws the store item
	public void draw(Graphics g) {
		g.setColor(new Color(40, 40, 40));
		g.fillRect(OFFSETX + (SPACING * index), OFFSETY, WIDTH, HEIGHT);
		g.drawImage(image, OFFSETX + (index * SPACING), OFFSETY, WIDTH, HEIGHT - 40, null);
		
		g.setColor(Color.white);
		g.drawString("["+(index+1)+"]", OFFSETX + (index * SPACING) +5, OFFSETY + 15);
		
		for (int i = 0; i < prices.length; i++) {
			if(timesBought > i)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.GRAY);
			g.fillRect(OFFSETX + (index * SPACING) + ((WIDTH / prices.length) * i) + 1, OFFSETY + HEIGHT - 40, (WIDTH/prices.length) - 2, 10);
		}
		
	}
	
	//Accessors
	
	public int getCurrentPrice() {
		return price;
	}
	
	public int getTimesBought() {
		return timesBought;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public GameButton getButton() {
		return button;
	}
	
}
