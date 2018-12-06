package game;

import javax.swing.JFrame;
public class MyScreen extends JFrame {
/**
 * MyScreen basic window for game, inherits properties and methods from JFrame
 * @author kene.ochuba
 * @since Sept. 27, 2018
 */
	
	/**
	 * MyScreen default constructor
	 * @param none, default constructor
	 */
	public MyScreen() {
		this.setSize(1600,1400);// Set the size of the screen
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	
	public static void main(String [] args) {
		MyScreen screen = new MyScreen();
		MyCanvas canvas = new MyCanvas();
		screen.getContentPane().add(canvas);
		
		
	}
}
	