package graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import coreEngine.KeyAndMouseInput;
import coreEngine.LevelManager;

@SuppressWarnings("serial")
public class GameWindow extends JFrame{
	
	//The main window, singleton
	public static GameWindow MainWindow;
	
	//The graphics panel
	GraphicsPanel graphicsPanel;
	
	//The wodth and height of the image
	private int width;
	private int height;
	
	//Constructor that cosntructs a window of width width and of height height
	public GameWindow(int width, int height) {
		
		super();
		
		MainWindow = this;
		
		this.width = width;
		this.height = height;
		
		//Setting Window Hints
		setTitle("Fire Game");
		setSize(width, height);
		setResizable(false);
		
		//Add the only GraphicsPanel
		graphicsPanel = new GraphicsPanel();
		add(graphicsPanel);
		
		//Set the keyboard and mouse callbacks
		KeyAndMouseInput input = new KeyAndMouseInput();
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	LevelManager.getCurrentLevel().Terminate();
		    	graphicsPanel.stopRenderThread();
		    }
		});
		
		//Set the windows visiblilty to true.
		setVisible(true);
		
	}
	
	//Accessors
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
