package com.alexfarias.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.alexfarias.main.Game;

public class Player extends Entity{

	public boolean right, up, left, down;
	public double spdPlayer = 2.5;
	public int dir = 0;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private boolean moved = false;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
		}
		
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
		}
	}
	
	public void tick() {
		if(moved)
			moved = false;
		
		if(right) {
			moved = true;
			dir = 0;
			setX(x+spdPlayer);
		}else if(left) {
			moved = true;
			dir = 1;
			setX(x-spdPlayer);
		}
		
		if(up) {
			moved = true;
			setY(y-spdPlayer);
		}else if(down) {
			moved = true;
			setY(y+spdPlayer);
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 1;
				}
			}
		}else {
			if(index != 0)
				index = 0;
		}
	}
	
	public void render(Graphics g, int SCALE) {
		if(dir == 0) {
			g.drawImage(rightPlayer[index], this.getX(), this.getY(), this.getWidth()*SCALE, this.getHeight()*SCALE, null);
		}else if(dir == 1) {
			g.drawImage(leftPlayer[index], this.getX(), this.getY(), this.getWidth()*SCALE, this.getHeight()*SCALE, null);
		}
		
	}

}
