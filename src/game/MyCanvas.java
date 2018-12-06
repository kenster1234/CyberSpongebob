package game;

import java.awt.Canvas;
import javax.swing.JButton;
import java.awt.font.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import sun.audio.*;

import javax.swing.ImageIcon;
	

public class MyCanvas extends Canvas implements KeyListener {
	
	Goodguy pay = new Goodguy(0,0,1600,800,"files/pay.jpg");
	Goodguy link = new Goodguy(10,615,120,120,"files/BobR.png");
	Follower krabs = new Follower(-450, 615, 120, 120, "files/Oh.png");// Use the Follower Class
	Goodguy bye = new Goodguy(60,20,200,200,"files/board.png");
	LinkedList badguys = new LinkedList();
	LinkedList fireballs = new LinkedList();

	public void setKrabs(Follower krabs) {
		this.krabs = krabs;
	}

	int time = 0;
	int winheight = this.getHeight();
	int winwidth = this.getWidth();
	int score;
	int boss = 0;
	int countDown = 558;
	int t;
	boolean start = false;
	boolean spacePressed = false;
	boolean Easy = false;
	boolean Hard  = false;
	boolean Extreme = false;
	boolean gameOver = false;


	
	public MyCanvas() {
		
		
		this.setSize(1600,1400); // set same size as MyScreen
		this.addKeyListener(this);// Add the Key Listener
		
        
		TimerTask repeatedTask = new TimerTask() {

			Random rand = new Random();
            public void run() {


                for(int i = 0; i < badguys.size(); i++) {// Move the bad guys around

                    Badguy bg = (Badguy) badguys.get(i);

                    bg.setxCoord(bg.getxCoord() + rand.nextInt((30)));
                    bg.setyCoord(bg.getyCoord() - rand.nextInt(40));
                    bg.setxCoord(bg.getxCoord() - rand.nextInt(30));
                    bg.setyCoord(bg.getyCoord() + rand.nextInt(40));
                    if (bg.getxCoord()  > 1600) {
                        bg.setxCoord(1400);
                    }if (bg.getxCoord() < 0) {
                        bg.setxCoord(100);
                    }if (bg.getyCoord()  > 700) {
                        bg.setyCoord(600);
                    }if (bg.getyCoord() < 0) {
                        bg.setyCoord(100);
                    }

                }

                repaint();

            }
			private int rand(int i) {
				// TODO Auto-generated method stub
				return 0;
			}

        };

        Timer timer = new Timer("Timer");

         

        long delay  = 100L;

        long period = 100L;

        timer.scheduleAtFixedRate(repeatedTask, delay, period);

        gameOver = false;
		for(int i = 0; i < badguys.size(); i++) {
			badguys.remove(i);		
		}
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i < 15; i++) {// Draw the Badguys
			Badguy bg = new Badguy(rand.nextInt(1150) , rand.nextInt(400) + 200,80,100,"files/Jellyfish.png");
            Rectangle r = new Rectangle(200,200,90,90);
			if (r.contains(link.getxCoord(), link.getyCoord())) {
				System.out.println("badguy on top of link");
				continue;
			}
			badguys.add(bg);		
		}
		link.setxCoord(10);
		link.setyCoord(650);
		krabs.setxCoord(-400);
		krabs.setyCoord(650);
		countDown = 558;
        
        playIt1("files/Grass.wav");
		
	}




	/** 
	 * paint overload java.awt.Canvas paint method and make it draw an oval
	 * @param graphics context variable called g
	 */
	@Override
	public void paint(Graphics g) {//Using paint to draw graphics
		if (gameOver || score == 10) {
			this.setBackground(Color.MAGENTA);// Set the Backgorund Colour
			Font end = new Font("Comic Sans", 1, 70);
			this.setFont(end);
			g.setColor(Color.WHITE);
			if (gameOver) {
				g.drawString("GAME OVER!", 400, 100);// Draw Game Over
			}
			else {
				g.drawString("YOU WIN!", 400, 100);// Draw Win Screen
			}
			g.drawString("Press [ENTER] for Easy Mode", 200, 300);
			g.drawString("Press [Control] for Hard Mode", 200, 500);
			g.drawString("Press [Z] for Extreme Mode", 200, 700);

		}
		else if (start){
			time++;// Adds the Timer to the game
			if (fireballs.size()==0) {
				countDown--;
			}
			else if (time%15==0) {
				countDown--;
			}
			t = countDown/18;
			if (t == 0) {
				gameOver = true;
				score = 0;
				repaint();
			}
			Font font = new Font ("Helvetica", Font.BOLD, 40);
			g.setFont(font);
			
			g.setColor(Color.DARK_GRAY);
			g.drawImage(pay.getImg(), pay.getxCoord(), pay.getyCoord(), pay.getWidth(), pay.getHeight(), this);// Draw the Spongebob background
			

			if (time%2==0 && Extreme) {// Makes Mr. Krabs move extremely fast
				if (krabs.getxCoord()<link.getxCoord()) {
					krabs.setxCoord(krabs.getxCoord()+16);
				}
				else if (krabs.getxCoord()==link.getxCoord()) {
					
				}
				else {
					krabs.setxCoord(krabs.getxCoord()-16);
				}
				
				if (krabs.getyCoord()<link.getyCoord()) {
					krabs.setyCoord(krabs.getyCoord()+16);
				}
				else if (krabs.getyCoord()==link.getyCoord()) {
					
				}
				else {
					krabs.setyCoord(krabs.getyCoord()-16);
				}
			}
			if (time%2==0 && Hard) {// Makes Mr. Krabs move faster
				if (krabs.getxCoord()<link.getxCoord()) {
					krabs.setxCoord(krabs.getxCoord()+12);
				}
				else if (krabs.getxCoord()==link.getxCoord()) {
					
				}
				else {
					krabs.setxCoord(krabs.getxCoord()-12);
				}
				
				if (krabs.getyCoord()<link.getyCoord()) {
					krabs.setyCoord(krabs.getyCoord()+12);
				}
				else if (krabs.getyCoord()==link.getyCoord()) {
					
				}
				else {
					krabs.setyCoord(krabs.getyCoord()-12);
				}
			}
			if (time%2==0 && Easy ) {// Makes Mr. Krabs move slowly
				if (krabs.getxCoord()<link.getxCoord()) {
					krabs.setxCoord(krabs.getxCoord()+5);
				}
				else if (krabs.getxCoord()==link.getxCoord()) {
					
				}
				else {
					krabs.setxCoord(krabs.getxCoord()-5);
				}
				
				if (krabs.getyCoord()<link.getyCoord()) {
					krabs.setyCoord(krabs.getyCoord()+5);
				}
				else if (krabs.getyCoord()==link.getyCoord()) {
					
				}
				else {
					krabs.setyCoord(krabs.getyCoord()-5);
				}
			}

			
			Rectangle a = new Rectangle(link.getxCoord() , link.getyCoord() , link.getWidth() , link.getHeight());
			Rectangle b = new Rectangle(krabs.getxCoord() - 15, krabs.getyCoord() - 15, krabs.getWidth() - 15, krabs.getHeight() - 15);
			
			if (a.intersects(b)) {
				gameOver = true;
				repaint();
			}
			
			if (score < 6) {
				g.drawString("Welcome to JellyFish Fields.", 450, 62);// removes the title screen

			}
			g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getWidth(), link.getHeight(), this);// Draws Spongebob
			g.drawImage(krabs.getImg(), krabs.getxCoord(), krabs.getyCoord(), krabs.getWidth(), krabs.getHeight(), this);// Draws Mr.Krabs
			g.drawImage(bye.getImg(), bye.getxCoord(), bye.getyCoord(), bye.getWidth(), bye.getHeight(), this);//Draws the scoreboard
			g.setColor(Color.BLACK);
			g.drawString("Score: " + Integer.toString(score), 72, 82);
			g.setColor(Color.BLACK);
			g.drawString("Time: " + String.valueOf(t), 72, 122);
			if (score == 12) {
				g.drawString("Press Enter To Play Again Easy Mode" + "You Win" + "Score: " + Integer.toString(score), 400, 400);
				g.drawString("Press [Control] for Easy Mode", 400, 600);
				g.drawString("Press [Z] for Extreme Mode", 400, 800);
				countDown = 558;
				krabs.setyCoord(650);
				krabs.setxCoord(-200);
				link.setxCoord(10);
				link.setyCoord(650);
				
			}
			for(int i = 0; i < badguys.size(); i++) {// draw bad guys
				Badguy bg = (Badguy) badguys.get(i);
				g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
				Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
				
			for(int j = 0; j  < fireballs.size(); j++ ) {// draws Fireballs
				Projectile k = (Projectile) fireballs.get(j);
					if (k.getxCoord() > this.getWidth()) {fireballs.remove(k); }
					if (time%10==0) {
						k.setxCoord(k.getxCoord() + 5);
					}
					g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);
						Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
							if (kr.intersects(r)) {
								System.out.println("badguy hit");
								badguys.remove(i);
								boss++;
								score++;
								playIt1("files/bi.wav");
							
							}
							repaint();
			}
			}
			
			if (badguys.size()==0) {// Moves and draws Projectiles
				for(int j = 0; j  < fireballs.size(); j++ ) {
					Projectile k = (Projectile) fireballs.get(j);
						if (k.getxCoord() > this.getWidth()) {fireballs.remove(k); }
						if (time%10==0) {
							k.setxCoord(k.getxCoord() + 1);
						}
						k.setxCoord(k.getxCoord() + 90);
						g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);
				}
				repaint();
			}
		}
		else {
			this.setBackground(Color.CYAN); // Sets the Background Colour
			Font s = new Font("Comic Sans", 1, 50);// Sets the Font Colour
			this.setFont(s);
			g.setColor(Color.YELLOW);
			g.drawString("CyberSpongebob", 200, 60);// Draws Title
			g.setColor(Color.BLACK);
			g.drawString("Press [Enter] for Easy Mode", 200, 130); // Draws Menu for Player
			g.drawString("Press [Control] for Hard Mode", 200, 230);
			g.drawString("Press [Z] for Extreme Mode", 200, 330);
			Font font1 = new Font ("Helvetica", Font.BOLD, 30);// Changes Font
			g.setFont(font1);
			g.drawString("Instructions:", 200, 390);// Draws Instructions
			g.drawString("1. Move Spongebob with the Arrowkeys.", 200,450);
			g.drawString("2. If time runs out or Mr. Krabs catches you, you lose.", 200, 550);
			g.drawString("3. Collect 10 Jelyfish with Spongebob's net to win", 200, 650);
			Font font2 = new Font ("Helvetica", Font.BOLD, 50);
			g.setFont(font2);
			g.setColor(Color.RED);
			g.drawString("4. HAVE FUN", 200, 750);

		}
		}
	@Override
	public void keyPressed(KeyEvent e) {
		if (!start) {
			if (e.getKeyCode()== 10) {// Sets Easy Mode and starts the game
				start = true;
				Easy = true;				
			}
		if (!start) {
			if (e.getKeyCode() == 17) {// Sets Hard Mode and starts the game
				start = true;
				Hard = true;
			}
		}
		if (!start) {
		if (e.getKeyCode()== 90) {// Sets Extreme Mode and starts the game
			start = true;
			Extreme = true;
		}
		}
		}
		else if (gameOver || score == 10) {
			if (e.getKeyCode()== 10) {// Draws Bad Guys for easy mode
				gameOver = false;
				for(int i = 0; i < badguys.size(); i++) {
					badguys.remove(i);	
					Easy = true;
					Hard = false;
					Extreme = false;			
				}

				Random rand = new Random();
				int winwidth = this.getWidth();
				int winheight = this.getHeight();
				for(int i = 0; i < 10; i++) {
					Badguy bg = new Badguy(rand.nextInt(1150) , rand.nextInt(400) + 200,80,100,"files/Jellyfish.png");
		            Rectangle r = new Rectangle(150,150,100,100);
					if (r.contains(link.getxCoord(), link.getyCoord())) {
						System.out.println("badguy on top of link");
						continue;
					}
					badguys.add(bg);		
				}
				link.setxCoord(10);
				link.setyCoord(650);
				krabs.setxCoord(-400);
				krabs.setyCoord(650);
				countDown = 558;
				score = 0;
				time = 0;
				
				repaint();
				}  if (e.getKeyCode()== 17) {// Draws Bad Guys for Hard mode
					gameOver = false;
					for(int i = 0; i < badguys.size(); i++) {
						badguys.remove(i);		
						Easy = false;
						Hard = true;
						Extreme = false;
								
					}

				Random rand = new Random();
				int winwidth = this.getWidth();
				int winheight = this.getHeight();
				for(int i = 0; i < 10; i++) {
					Badguy bg = new Badguy(rand.nextInt(1150) , rand.nextInt(400) + 200,80,100,"files/Jellyfish.png");
		            Rectangle r = new Rectangle(150,150,100,100);
					if (r.contains(link.getxCoord(), link.getyCoord())) {
						System.out.println("badguy on top of link");
						continue;
					}
					badguys.add(bg);		
				}
				link.setxCoord(10);
				link.setyCoord(650);
				krabs.setxCoord(-400);
				krabs.setyCoord(650);
				countDown = 558;
				score = 0;
				time = 0;
				
				repaint();
			}
				if (e.getKeyCode()== 90) {// Draws Bad Guys for Extreme mode
					gameOver = false;
					for(int i = 0; i < badguys.size(); i++) {
						badguys.remove(i);		
						Easy = false;
						Hard = false;
						Extreme = true;			
					}

				Random rand = new Random();
				int winwidth = this.getWidth();
				int winheight = this.getHeight();
				for(int i = 0; i < 10; i++) {
					Badguy bg = new Badguy(rand.nextInt(1150) , rand.nextInt(400) + 200,80,100,"files/Jellyfish.png");
		            Rectangle r = new Rectangle(150,150,100,100);
					if (r.contains(link.getxCoord(), link.getyCoord())) {
						System.out.println("badguy on top of link");
						continue;
					}
					badguys.add(bg);		
				}
				link.setxCoord(10);
				link.setyCoord(650);
				krabs.setxCoord(-400);
				krabs.setyCoord(650);
				countDown = 558;
				score = 0;
				time = 0;
				
				repaint();
			}
		}

		else  if (start){

			if (e.getKeyCode() == 32) {// Draws Fireballs 
				Projectile Fireball = new Projectile(link.getxCoord()+40, link.getyCoord()+30,30,30,"files/Patrick.png");
				fireballs.add(Fireball);
				spacePressed = true;
				
			}
			
			//System.out.println(e);
			link.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
			
			for(int i = 0; i < badguys.size(); i++) {// Detects collisions and removes Jellyfish
				Badguy bg = (Badguy) badguys.get(i);
				Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
				
				if (r.contains(link.getxCoord(), link.getyCoord())) {
					
					System.out.println("badguy hit by link");
					playIt1("files/bi.wav");
					
					badguys.remove(i);
					score++;
					boss++;
					
				}
			}	
			repaint();
		}
		}

		

	
	public boolean isSpacePressed() {// Setters and Getters
		return spacePressed;
	}




	public void setSpacePressed(boolean spacePressed) {
		this.spacePressed = spacePressed;
	}




	public Goodguy getPay() {
		return pay;
	}




	public void setPay(Goodguy pay) {
		this.pay = pay;
	}




	public Follower getKrabs() {
		return krabs;
	}






	public Goodguy getBye() {
		return bye;
	}




	public void setBye(Goodguy bye) {
		this.bye = bye;
	}




	public int getTime() {
		return time;
	}




	public void setTime(int time) {
		this.time = time;
	}




	public int getWinheight() {
		return winheight;
	}




	public void setWinheight(int winheight) {
		this.winheight = winheight;
	}




	public int getWinwidth() {
		return winwidth;
	}




	public void setWinwidth(int winwidth) {
		this.winwidth = winwidth;
	}




	public int getScore() {
		return score;
	}




	public void setScore(int score) {
		this.score = score;
	}




	public int getBoss() {
		return boss;
	}




	public void setBoss(int boss) {
		this.boss = boss;
	}




	public int getCountDown() {
		return countDown;
	}




	public void setCountDown(int countDown) {
		this.countDown = countDown;
	}




	public int getT() {
		return t;
	}




	public void setT(int t) {
		this.t = t;
	}




	public boolean isStart() {
		return start;
	}





	public boolean isEasy() {
		return Easy;
	}




	public void setEasy(boolean easy) {
		Easy = easy;
	}




	public boolean isHard() {
		return Hard;
	}




	public void setHard(boolean hard) {
		Hard = hard;
	}




	public boolean isExtreme() {
		return Extreme;
	}




	public void setExtreme(boolean extreme) {
		Extreme = extreme;
	}




	public void setStart(boolean start) {
		this.start = start;
	}




	public boolean isGameOver() {
		return gameOver;
	}




	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}




	public LinkedList getfireballs() {
		return fireballs;
	}

	public LinkedList getFireballs() {
		return fireballs;
	}

	public void setFireballs(LinkedList fireballs) {
		this.fireballs = fireballs;
	}

	public void setKnives(LinkedList fireballs) {
		this.fireballs = fireballs;
	}

	public LinkedList getBadguys() {
		return badguys;
	}

	public void setBadguys(LinkedList badguys) {
		this.badguys = badguys;
	}
	
	public void playIt2(String filename) {// Plays Audio
		
		try {
		    InputStream in = new FileInputStream(filename);
		    AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void playIt1(String filename) {
		
		try {
		    InputStream in = new FileInputStream(filename);
		    AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
   
	public Goodguy getLink() {
		return link;
	}

	public void setLink(Goodguy link) {
		this.link = link;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==32) {
			spacePressed = false;
		}
	}
		
	
	

	
}
