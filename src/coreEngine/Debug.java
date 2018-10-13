package coreEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import graphics.GameWindow;

//Meant for debugging only
public class Debug {
	
	static ArrayList<Double> lastFPSs;
	static double avgFPS = -1;
	static double minFPS = 100000;
	static double displayMinFPS = 100000;
	
	public static void Initialize() {
		lastFPSs = new ArrayList<>();
	}
	
	public static void update() {
		
		
		
		double fps = Time.getFPS();
		
		if(fps < minFPS)
			minFPS = fps;
		
		lastFPSs.add(fps);
		
		if(lastFPSs.size() > 100) {
			
			double sum = 0;
			
			for(int i = 0; i < lastFPSs.size(); i++) {
				sum += lastFPSs.get(i);
			}
			
			avgFPS = sum / lastFPSs.size();
			lastFPSs.clear();
			displayMinFPS = minFPS;
			minFPS = 10000;
		}
	}
	
	public static void drawFPS(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawString("FPS: " + avgFPS, GameWindow.MainWindow.getWidth() - 100, 10);
	}
	
}
