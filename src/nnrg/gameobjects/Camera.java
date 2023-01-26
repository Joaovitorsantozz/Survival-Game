package nnrg.gameobjects;

import nnrg.main.Game;
import nnrg.main.HandlerGame;

public class Camera {
	private float x, y;
	private GameObject obj;
	public Camera(int x, int y,GameObject obj) {
		this.x = x;
		this.y = y;
		this.obj=obj;
	}
	
	public void tick() {

		x += ((obj.getX() - x) - Game.W / 2f) * 0.03f;
		y += ((obj.getY() - y) - Game.H / 2f) * 0.03f;

		if (y <= 0)
			y = 0;
		if (y + Game.H >= HandlerGame.level.getHeight() * 32)
			y = HandlerGame.level.getHeight() * 32 - Game.H;
		if (x <= 0)
			x = 0;
		if (x + Game.W >= HandlerGame.level.getWidth() * 32)
			x = HandlerGame.level.getWidth() * 32 - Game.W;

		
	}
	public void makeShake() {
		x += ((obj.getX() - x) - Game.W / 2f) * 0.4f;
		y += ((obj.getY() - y) - Game.H / 2f) * 0.4f;
	}
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
