package gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

//Manages the GUI Componenets
public class UIManager {
	
	//All the GUI components
	private static ArrayList<GUIComponent> guiComponents;
	
	public static void Initialize() {
		guiComponents = new ArrayList<>();
	}
	
	//Add a new componenet
	public static void registerNewComponent(GUIComponent component) {
		guiComponents.add(component);
	}
	
	//Update the components
	public static void update() {
		for (int i = 0; i < guiComponents.size(); i++) {
			guiComponents.get(i).update();
		}
	}
	
	//Draw the components
	public static void draw(Graphics graphics) {
		Collections.sort(guiComponents);
		
		for (int i = 0; i < guiComponents.size(); i++) {
			guiComponents.get(i).draw(graphics);
		}
	}

	//Destroys all components
	public static void terminate() {
		guiComponents.clear();
		
	}
}
