package com.alexfarias.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.alexfarias.main.Game;

public class Tile {

	private BufferedImage sprite;
	private int x, y, width, height;
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);
	
	public Tile(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.getX(), y- Camera.getY(), width, height, null);
	}
}
