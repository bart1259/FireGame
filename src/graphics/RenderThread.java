package graphics;

import coreEngine.Engine;

//This is the render thread which renders graphics
public class RenderThread implements Runnable {
	
	//The graphics panel
	public GraphicsPanel panel;
	//WHeter or not to exit abort the thread
	public boolean exit = false;
	

	
	public void run() {

		//Loop infinitley until we want to exir
		while(!exit) {
			//Update the engine and then draw the
			Engine.update();
			panel.repaint();
			
		}
		System.exit(0);
	}

	
}
