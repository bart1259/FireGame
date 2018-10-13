package coreEngine;

import java.awt.Graphics;

//The base class for every level
public abstract class Level {

	//A level can decide what happens at each of these times	
	public abstract void Initialize();
	public abstract void Loop(Graphics graphics);
	public abstract void Terminate();
}
