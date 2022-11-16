package com.alexfarias.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.alexfarias.entities.Bullet;
import com.alexfarias.entities.Enemy;
import com.alexfarias.entities.Entity;
import com.alexfarias.entities.Lifepack;
import com.alexfarias.entities.Weapon;
import com.alexfarias.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource("/map.png"));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[WIDTH * HEIGHT];
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					int pixelAtual = pixels[xx + (yy*WIDTH)];
					
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, 16, 16, Tile.TILE_FLOOR);
					
					if(pixelAtual == 0xFF000000){
						//Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, 16, 16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFCFFFC) {
						//Wall
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16, 16, 16, Tile.TILE_WALL);
					}else if(pixelAtual == 0xFF0400FF) {
						//Player
						//tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, 16, 16, Tile.TILE_FLOOR);
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFFFF1000){
						//Enemy
						Enemy en = new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
					}else if(pixelAtual == 0xFFFF993F) {
						//Weapon
						Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.WEAPON_EN));
					}else if(pixelAtual == 0xFFFF0F37) {
						//LifePack
						Game.entities.add(new Lifepack(xx*16, yy*16, 16, 16, Entity.LIFEPACK_EN));
					}else if(pixelAtual == 0xFFFFD800) {
						//Bullet
						Game.entities.add(new Bullet(xx*16, yy*16, 16, 16, Entity.BULLET_EN));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(Rectangle r, double xNext, double yNext) {
		
		int x1 = (int)(xNext / TILE_SIZE);
		int y1 = (int)(yNext / TILE_SIZE);
		
		int x2 = (int)((xNext + r.width -1) / TILE_SIZE);
		int y2 = (int)(yNext / r.height);
		
		int x3 = (int)(xNext / TILE_SIZE);
		int y3 = (int)((yNext + r.height -1) / TILE_SIZE);
		
		int x4 = (int)((xNext + r.width -1) / TILE_SIZE);
		int y4 = (int)((yNext + r.height -1) / TILE_SIZE);
		
		return !((tiles[x1 + y1 * World.WIDTH] instanceof WallTile) ||
			     (tiles[x2 + y2 * World.WIDTH] instanceof WallTile) ||
			     (tiles[x3 + y3 * World.WIDTH] instanceof WallTile) ||
			     (tiles[x4 + y4 * World.WIDTH] instanceof WallTile));
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		int xstart = Camera.getX()/16, ystart = Camera.getY()/16;
		int xfinal = xstart + ((Game.WIDTH)/16), yfinal = ystart + ((Game.HEIGHT)/16);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
