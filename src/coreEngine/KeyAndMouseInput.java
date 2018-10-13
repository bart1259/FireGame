package coreEngine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

//A class filled with callbacks to different keyboard and mouse commands
public class KeyAndMouseInput extends KeyAdapter implements MouseInputListener {

	public KeyAndMouseInput() {
		
	}
	
	@Override
    public void keyPressed(KeyEvent event) {
		Input.KeyPressed(event.getKeyCode());
    }
	
	@Override
    public void keyReleased(KeyEvent event) {
		Input.KeyReleased(event.getKeyCode());
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		Input.MousePressed(e.getModifiers(), true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Input.MousePressed(e.getModifiers(), false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		Input.MouseMoved(e.getX(), e.getY());
	}
	
}
