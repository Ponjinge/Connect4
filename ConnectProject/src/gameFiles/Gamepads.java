package gameFiles;

import java.awt.Image;

import javax.swing.JFrame;

public class Gamepads extends JFrame{ //JFrame is a superclass to Gamepads 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Default Serial Version recommended by Eclipse quickfixes 
	public Gamepads() {
		initialize();
	}
	public void initialize() { 
		setTitle("Connect 4 Game"); //Title of the window 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Action taken upon window close
		setSize(500, 400); //Dimensions of the window
		setVisible(true); //True as we want to be able to see the window 
		
	}
}
