package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Badguy {
		
		private int xCoord = 0;
		private int yCoord = 0;
		private int width = 10;
		private int height = 10;
		private Image img;
		
		/**
		 * Poetic default constructor
		 */
		public Badguy() {
			setxCoord(10);
			setyCoord(10);
			setWidth(30);
			setHeight(30);
			setImg("files/Jellyfish.png");
		}

		public Badguy(int x, int y, int w, int h, String imgpath) {
			setxCoord(x);
			setyCoord(y);
			setWidth(w);
			setHeight(h);
			setImg(imgpath);	
		}

		private void setImg(String imgpath) {
			this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
			// TODO Auto-generated method stub
			
		}

		public void setImg(Image img) {
			this.img = img;
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
			// TODO Auto-generated method stub
			return img;
		}


}
