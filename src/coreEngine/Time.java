package coreEngine;

public class Time {

	//The start milisieconds
	private static long startMS;
	//The time last frame
	private static long last_time = System.nanoTime();

	//The change in time every frame
	public static double deltaTime;
	
	public static void Initialize() {
		last_time = System.nanoTime();
		startMS = System.currentTimeMillis();
	}

	public static void update() {
		//Claculate the change in time 
		long nowTime = System.nanoTime();
		deltaTime = ((nowTime - last_time) / 1000000000.0);
		last_time = nowTime;
	}

	public static double getFPS() {
		//Returns the fps which is the inverse of the delta time
		return 1.0 / deltaTime;
	}

	//Accessors
	
	public static double getDeltaTime() {
		return deltaTime;
	}

	public static double getTime() {
		return (System.currentTimeMillis() - startMS) / 1000.0f;
	}
	
	public static float getFPSf() {
		return 1 / (float) deltaTime;
	}

	public static float getDeltaTimef() {
		return (float) deltaTime;
	}

}
