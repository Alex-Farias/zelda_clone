package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.alexfarias.main.Game;

public class Hud {

	public void render(Graphics g) {
		int life = Game.player.getLife();
		int maxlife = Game.player.getMaxLife();
		double percent = (((life*100)/maxlife));
		
		g.setColor(Color.BLACK);
		g.fillRect(4, 4, 52, 8);
		
		g.setColor(Color.RED);
		g.fillRect(5, 5, (int)((percent*50)/100), 5);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 7));
		g.drawString(life+"/"+maxlife, 16, 10);
	}
}
