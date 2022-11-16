package com.alexfarias.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.alexfarias.main.Game;
import com.alexfarias.verifications.TypeEntity;
import com.alexfarias.world.World;

public class Enemy extends Entity{

	private double spdEnemy = 0.6;
	
	public Enemy(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
		
	public void tick() {
		List<Entity> entitiesColidding = new ArrayList<Entity>();
		entitiesColidding.add(Game.player);
		entitiesColidding.addAll(Game.enemies);
		
		String coliddingRight = isColidding(entitiesColidding, (int)(x + spdEnemy), (int)getY(), getWidth(), getHeight());
		String coliddingLeft = isColidding(entitiesColidding, (int)(x - spdEnemy), (int)getY(), getWidth(), getHeight());
		String coliddingDown = isColidding(entitiesColidding, (int)getX(), (int)(y + spdEnemy), getWidth(), getHeight());
		String coliddingUp = isColidding(entitiesColidding, (int)getX(), (int)(y - spdEnemy), getWidth(), getHeight());
		
		if(coliddingRight != TypeEntity.ID_PLAYER && coliddingLeft != TypeEntity.ID_PLAYER) {
			if(getX() < Game.player.getX() && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), x + spdEnemy, getY()) &&
			   coliddingRight == null) {
				setX(getX() + spdEnemy);
			}else if(getX() > Game.player.getX() && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), x - spdEnemy, getY()) &&
					 coliddingLeft == null) {
				setX(getX() - spdEnemy);
			}
		}else {
			atack(Game.player, damage);
		}
		
		if(coliddingDown != TypeEntity.ID_PLAYER && coliddingUp != TypeEntity.ID_PLAYER) {
			if(getY() < Game.player.getY() && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), getX(), y + spdEnemy) &&
			   coliddingDown == null) {
				setY(getY() + spdEnemy);
			}else if(getY() > Game.player.getY() && World.isFree(this.hitBox(getHBExists(), (int)getX(), (int)getY(), getWidth(), getHeight()), getX(), y - spdEnemy) &&
					coliddingUp == null) {
				setY(getY() - spdEnemy); 
			}
		}else {
			//atack(Game.player, damage);
		}
}
}
