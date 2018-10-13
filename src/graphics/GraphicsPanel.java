package graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

import coreEngine.LevelManager;

@SuppressWarnings("serial")
public class GraphicsPanel extends JPanel{
	//The render thread	
	public RenderThread renderThread;
	
	//Dfault constructor
	public GraphicsPanel()
	{
		super();	
		startRenderThread();
		
	}

	//Method that starts the thread
	public void startRenderThread() {
		//Make the thread
		renderThread = new RenderThread();
		renderThread.panel = this;
		Thread thread = new Thread(renderThread);
		thread.start();
	}
	
	//Exit the render thread
	public void stopRenderThread() {
		renderThread.exit = true;
	}
	//Overriding the paintComponent to draw the graphics.
	public void paintComponent(Graphics graphics) {
		LevelManager.getCurrentLevel().Loop(graphics);
	}
	
}
