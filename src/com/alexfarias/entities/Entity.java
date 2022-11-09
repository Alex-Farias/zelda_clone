package com.alexfarias.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	
	protected double x, y, width, height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}

	public int getWidth() {
		return (int)this.width;
	}
	
	public int getHeight() {
		return (int)this.height;	
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setHeight(double height) {
		this.height = height;	
	}
	
	public void tick() {
		
	}	
	
	public void render(Graphics g, int SCALE) {
		g.drawImage(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
	}
}
