package nnrg.main.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nnrg.gameobjects.GameObject;
import nnrg.main.LoadImage;

public class Lifebar {
	private BufferedImage lifebar = new LoadImage("/lifebar.png").getImage();
	private GameObject user;
	private double life, maxlife,countlife;

	public Lifebar(GameObject ee, double life, double maxLife) {
		user = ee;
		this.life=life;
		this.maxlife = maxLife;
	}

	public void render(Graphics g,int offX,int offY) {
		if(getLife()!=user.getLife()) {
			life-=0.5;
		}
		int mw = (int) ((life / maxlife) * 70);
		g.setColor(new Color(175, 0, 52));
		g.fillRect(user.getX()+offX, user.getY() + offY, 70, 12);
		g.setColor(new Color(52, 175, 0));
		g.fillRect(user.getX()+offX, user.getY() + offY, mw, 12);
		g.drawImage(lifebar, user.getX()+offX, user.getY() + offY, 70, 12, null);

	}

	public int getLife() {
		return (int) life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}