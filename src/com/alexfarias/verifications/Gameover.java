package com.alexfarias.verifications;

import com.alexfarias.main.Game;

public class Gameover {
	
	public static void isGameover() {
		if(Game.player.getLife() <= 0) {
			System.out.print(Game.player.getLife());
			//new Game();
		}
	}

}
