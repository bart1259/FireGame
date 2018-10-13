package coreEngine;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ResourceManager {

	private static HashMap<String, Image> images;
	
	public static void Initialize() {
		images = new HashMap<String, Image>();
		//Load error Texture in case we cannot load other textures
		LoadImage("res/ErrorTexture.png", "ERROR_TEXTURE");
		
		//Load all images the game depends on
		//Credits
		ResourceManager.LoadImage("res/logo.png", "Logo");
		
		//Game
		ResourceManager.LoadImage("res/player.png", "Player");
		ResourceManager.LoadImage("res/fire.png", "Enemy");
		ResourceManager.LoadImage("res/healthPickUp.png", "HealthPickup");
		ResourceManager.LoadImage("res/blueFire.png", "BlueFire");
		ResourceManager.LoadImage("res/greenFire.png", "GreenFire");
		ResourceManager.LoadImage("res/smallBlueFire.png", "SmallBlueFire");
		ResourceManager.LoadImage("res/brownFire.png", "BrownFire");
		ResourceManager.LoadImage("res/smallBrownFire.png", "SmallBrownFire");
		ResourceManager.LoadImage("res/coin.png", "Coin");
		ResourceManager.LoadImage("res/bitCoin.png", "BitCoin");
		
		//Store
		ResourceManager.LoadImage("res/compass.png", "Compass");
		ResourceManager.LoadImage("res/fireResistance.png", "FireResistance");
		ResourceManager.LoadImage("res/maxBoost.png", "MaxBoost");
		ResourceManager.LoadImage("res/maxHealth.png", "MaxHealth");
		ResourceManager.LoadImage("res/moneyMachine.png", "MoneyMachine");
	}
	
	//Loads an image, returns wheter or not it was succesfull
	public static boolean LoadImage(String path, String name) {
		
		Image loadedImage = null;
		
		if(images.keySet().contains(name)) {
			//If this image already exists throw an error
			System.err.println("Image With name: " + name + " alerady exists");
			return false;
		}
		
		try {
			loadedImage = ImageIO.read(new File(path));
		} catch (IOException e) {
			//If the image doesnt exit throw an error
			System.err.println("Could not load image " + name + " at " + path);
			return false;
		}	
		images.put(name, loadedImage);
		return true;
		
	}
	
	//Returns the image with key name
	public static Image GetImage(String name) {
	
		Image ret = images.get("ERROR_TEXTURE");
		
		//Linear search for the correct image
		for (String i : images.keySet()) {
			if(i.equals(name)) {
				ret = images.get(i);
				return ret;
			}
		}
		
		//If we cannot find the image we load an error texture instead
		System.err.println("Could not load image: " + name + " loaded error texture instead");
		return ret;
	}
	
}
