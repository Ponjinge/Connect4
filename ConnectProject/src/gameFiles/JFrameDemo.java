package gameFiles;

import java.awt.Image;

import javax.swing.SwingUtilities;

public class JFrameDemo {
	public static void main(String[] args) {
		//Run this program on the Event Dispatch Thread 
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Gamepads frame = new Gamepads();
				Image icon = new javax.swing.ImageIcon("src/Images/Connect4Logo.png").getImage();
				frame.setIconImage(icon); //Window Logo for the main game window 
			}
		});
	}
}
