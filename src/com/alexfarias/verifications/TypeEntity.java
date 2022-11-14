package com.alexfarias.verifications;

import com.alexfarias.entities.Entity;
import com.alexfarias.main.Game;

public class TypeEntity {
	
	public static final String ID_PLAYER = "001";
	public static final String ID_ENEMY = "002";
	public static final String ID_WEAPON = "101";
	public static final String ID_BULLET = "102";
	public static final String ID_LIFEPACK = "103";
	
	public static String verifyEntityList(Entity e) {
		if(e == Game.player) {
			return ID_PLAYER;
		}else if(e == Game.enemies) {
			return ID_ENEMY;
		}
		
		return null;
	}

}
