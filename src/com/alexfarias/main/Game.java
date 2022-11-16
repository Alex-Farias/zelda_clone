package com.alexfarias.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.alexfarias.entities.Entity;
import com.alexfarias.entities.Player;
import com.alexfarias.graphics.Spritesheet;
import com.alexfarias.verifications.Gameover;
import com.alexfarias.world.World;

import Interface.Hud;

public class Game extends Canvas implements Runnable, KeyListener{
	 
	private BufferedImage image;
	private boolean isRunning;
	private Thread thread;
	
	public static World world;
	public static Player player;
	public Hud hud;
	
	public static Random rand;
	
	public static JFrame frame;
	public static Spritesheet spritesheet;
	public static List<Entity> entities;
	public static List<Entity> enemies;
	
	public static final int WIDTH = 240, HEIGHT = 160, SCALE = 3;
	
	//Método construtor
	public Game() {
		rand = new Random();
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		hud = new Hud();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/Spritesheet.png");
		player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
		entities.add(player);
		world = new World("/map.png");
	}
	
	//Método para setar configurações de Janela principal
	public void initFrame() {
		frame = new JFrame("Game base");
		frame.add(this);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//Método principal do Java
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	//Método de rendenização avançada - Start
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	//Método de rendenização avançada - Stop
	public synchronized void stop() {
		isRunning = false;
	}
	
	//Método de tick
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
	}
	
	//Método de render
	public void render() {
		if(player.getLife() <= 0)
			Gameover.isGameover();
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		
		g.setColor(new Color(19,19,19));
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		world.render(g);
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		hud.render(g);
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);		
		bs.show();
	}
	
	//Overrides de implements
	
	@Override
	public void run() {
		//Maniera profissional de rendenização por fps
		while(isRunning) {
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			double timer = System.currentTimeMillis();
			while(isRunning) {
				long now = System.nanoTime();
				delta+= (now - lastTime) / ns;
				lastTime = now;
				if(delta >= 1) {
					tick();
					render();
					delta--;
				}
				
				if(System.currentTimeMillis() - timer >= 1000) {
					timer+=1000;
				}
			}
			stop();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
		   e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				 e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP ||
		   e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				 e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
		   e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				 e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP ||
		   e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				 e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
	}

}
