package com.alexfarias.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource("/map.png"));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int i = 0; i < pixels.length; i++) {
				if(pixels[i] == 0xFFFF1000) {
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
	}
	
	public void render() {
	
	}
}
