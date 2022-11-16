package com.alexfarias.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import com.alexfarias.main.Game;
import com.alexfarias.verifications.TypeEntity;
import com.alexfarias.world.Camera;
import com.alexfarias.world.World;

public class Entity {
	
	protected double x, y;
	protected int width, height;
	protected boolean hbExists = true;
	
	private BufferedImage sprite;
	
	public static int life = 100, maxLife = 100;
	public static int damage = 1;
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(96, 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(112, 16, 16, 16);
	
	public Entity(double x, double y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public static int atack(Entity e, int damage) {
		int lifeAtack = e.getLife() - damage;
		e.setLife(lifeAtack);
		
		return lifeAtack;
	}
	
	public Rectangle hitBox(boolean exists, double x, double y, int width, int height ) {
		if(exists) {
			if(width < World.TILE_SIZE || height < World.TILE_SIZE) {
				x += (World.TILE_SIZE - width) - ((World.TILE_SIZE - width) / 2);
				y -= (World.TILE_SIZE - height) - ((World.TILE_SIZE - height) / 2);
			}
			
			Rectangle hb = new Rectangle((int)x, (int)y, width, height);
			return hb;
		}
		
		return null;
	}
	
	public String isColidding(List<Entity> eList, int x, int y, int width, int height) {
		Rectangle currentEntity = hitBox(getHBExists(), x, y, width, height);
		for(int i = 0; i < eList.size(); i++) {
			Entity e = (Entity) eList.get(i);
			if(e == this)
				continue;
			
			Rectangle targetEntity = hitBox(e.getHBExists(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
			
			if(currentEntity.intersects(targetEntity)) {
				if(TypeEntity.verifyEntityList(e) == TypeEntity.ID_PLAYER) {
					return TypeEntity.ID_PLAYER;
				}else {
					return TypeEntity.ID_ENEMY;
				}
			}
		}
		return null;
	}
	
	public int getLife() {
		return Entity.life;
	}
	
	public int getMaxLife() {
		return Entity.maxLife;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return (int)this.height;	
	}
	
	public void setLife(int life) {
		if(life >= 0)
			Entity.life = life;
	}
	
	public void setMaxLife(int maxLife) {
		Entity.maxLife = maxLife;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;	
	}
	
	public boolean getHBExists() {
		return this.hbExists;
	}
	
	public void setHBExists(boolean hbExists) {
		this.hbExists = hbExists;
	}
	
	public void tick() {
		
	}	
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int)(this.getX() - Camera.getX()), (int)(this.getY() - Camera.getY()), this.getWidth(), this.getHeight(), null);
	}
}
