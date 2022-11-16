package com.alexfarias.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.alexfarias.main.Game;
import com.alexfarias.world.Camera;
import com.alexfarias.world.World;
import com.alexfarias.verifications.Gameover;

public class Player extends Entity{
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private boolean moved = false;
	
	public boolean right, up, left, down;
	public double spdPlayer = 2.5;
	public int dir = 0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, World.TILE_SIZE, World.TILE_SIZE);
		}
		
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 16, World.TILE_SIZE, World.TILE_SIZE);
		}
	}
	
	public void tick() {		
		if(moved)
			moved = false;
		
		if(right && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), x + spdPlayer, this.getY())) {
			moved = true;
			dir = 0;
			setX(x+spdPlayer);
		}else if(left && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), x - spdPlayer, this.getY())) {
			moved = true;
			dir = 1;
			setX(x-spdPlayer);
		}
		
		if(up && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), this.getX(), y - spdPlayer)) {
			moved = true;
			setY(y-spdPlayer);
		}else if(down && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), this.getX(), y + spdPlayer)) {
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
		
		Camera.setX(Camera.clamp((int)this.getX() - (((Game.WIDTH))/2), 0, World.WIDTH*16 - Game.WIDTH));
		Camera.setY(Camera.clamp((int)this.getY() - (((Game.HEIGHT))/2), 0, World.HEIGHT*16 - Game.HEIGHT));
	}
	
	public void render(Graphics g) {
		if(dir == 0) {
			g.drawImage(rightPlayer[index], (int)(this.getX() - Camera.getX()), (int)(this.getY() - Camera.getY()), World.TILE_SIZE, World.TILE_SIZE, null);
		}else if(dir == 1) {
			g.drawImage(leftPlayer[index], (int)(this.getX() - Camera.getX()), (int)(this.getY() - Camera.getY()), World.TILE_SIZE, World.TILE_SIZE, null);
		}
		
	}

}
