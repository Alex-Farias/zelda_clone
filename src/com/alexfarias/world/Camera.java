package com.alexfarias.world;

public class Camera {

	public static int x = 0, y = 0;
	
	public static int clamp(int Atual, int Min, int Max) {
		if(Atual < Min) {
			Atual = Min;
		}
		
		if(Atual > Max) {
			Atual = Max;
		}
		return Atual;
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static void setX(double x) {
		Camera.x = (int)x;
	}
	
	public static void setY(double y) {
		Camera.y = (int)y;
	}
}
