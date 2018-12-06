package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Goodguy {
	
	private int xCoord = 0;
	private int yCoord = 0;
	private int width = 10;
	private int height = 10;
	private Image img;
	
	/**
	 * Goodguy default constructor
	 */
	public Goodguy() {
		setxCoord(10);
		setyCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("files/download");
	}

	/**
	 * Goodguy overloaded constructor
	 *  @param x initial x location
	 *  @param y initial y location
	 *  @param w initial width
	 *  @param h initial height
	 */
	public Goodguy(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);	
	}
	
	public void moveIt(int direction, int w, int h) {
		int speed = 20;
		int x = getxCoord();
		int y = getyCoord();
		
		if (direction == 39) {
			x = x + speed;
			if (x > 1350) { x = x - speed *  3; } // check to see if user has moved off game area
			setxCoord(x);
			setImg("files/BobR.png");
		} else if (direction == 37) {
			if (x < 0) {x = x + speed * 3; }
			x = x - speed;
			setxCoord(x);
			setImg("files/BobL.png");
		}else if (direction == 38) {
			if (y < 0) {y = y + speed * 3; }
			y = y - speed;
			setyCoord(y);
			setImg("files/BobR.png");
		} else if (direction == 40) {
			if (y > 700) {y = y - speed * 3; }
			y = y + speed;
			setyCoord(y);
			setImg("files/BobL.png");
		}else if (direction == 68) {
			if (y < 0) {y = y + speed * 3; }
			y = y - speed;
			x = x - speed;
			setyCoord(y);
			setxCoord(x);
			setImg("files/BobR.png");
		}else if (direction == 70) {
			if (y < 0) {y = y + speed * 3; }
			y = y - speed;
			x = x + speed;
			setyCoord(y);
			setxCoord(x);
			setImg("files/BobR.png");
		}else if (direction == 83) {
			if (y < 0) {y = y + speed * 3; }
			y = y + speed;
			x = x + speed;
			setyCoord(y);
			setxCoord(x);
			setImg("files/BobR.png");
		} else if (direction == 65) {
			if (y < 0) {y = y + speed * 3; }
			y = y + speed;
			x = x - speed;
			setyCoord(y);
			setxCoord(x);
			setImg("files/BobR.png");
		}
	}
		 

	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}




}




