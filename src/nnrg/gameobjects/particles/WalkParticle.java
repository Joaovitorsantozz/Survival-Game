package nnrg.gameobjects.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import nnrg.gameobjects.ID;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.Game;
import nnrg.main.LoadImage;
import nnrg.world.Depth;

public class WalkParticle extends Particle implements Renderable, Tickable {
	public int life = 255, rotation;
	public double xx, yy, zz;
	public double xa, ya, za;
	private int width;
	private BufferedImage spr = new LoadImage("/walkparticle.png").getImage();

	public WalkParticle(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		setDepth(Depth.HIGHT - 2);
		setX(x+new Random().nextInt(40));
		width=8+new Random().nextInt(8);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		if (life > 0) {
			life -= 20;
		}
		if (life <= 1)
			Game.handler.DeleteObject(this);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(new Color(255, 255, 255, life));

		g2.rotate(Math.toRadians(rotation), x + 8, y + 8);
		g.drawImage(spr, x, y,width,width, null);
		g2.rotate(-Math.toRadians(rotation), x + 8, y + 8);
	}

}
