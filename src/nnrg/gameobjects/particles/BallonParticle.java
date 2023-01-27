package nnrg.gameobjects.particles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nnrg.main.Game;

public class BallonParticle {
	public BufferedImage ballon;
	private double velY;
	private boolean shouldRender;

	private int x, y=Game.H-30;

	public BallonParticle(int x, int y, BufferedImage image) {
		this.x = x;
		ballon = image;
	}

	public void Update() {
	

	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(ballon, x, y, ballon.getWidth() * 3, ballon.getHeight() * 3, null);
	}

	public BufferedImage getImage() {
		return this.ballon;
	}
}
