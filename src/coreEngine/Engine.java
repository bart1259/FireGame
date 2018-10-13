package coreEngine;

public class Engine {
	
	public static void update() {
		try {
			Thread.sleep(1,0);
			
			//UPdate engine components
			Debug.update();
			Time.update();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
