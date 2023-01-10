package nnrg.gameobjects.levelobj;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import nnrg.gameobjects.GameObject;
import nnrg.gameobjects.ID;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.Game;
import nnrg.main.LoadImage;
import nnrg.world.Depth;

public class Bush extends GameObject implements Tickable, Renderable {
	private BufferedImage spr = new LoadImage("/bush1.png").getImage();

	public Bush(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		setWidth(spr.getWidth() * 3);
		setHeight(spr.getHeight() * 3);
		setDepth(Depth.HIGHT);
		
		verifyAmount();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

		drawDefaultTex(g, spr);

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX(), getY() + getHeight() - 20, getWidth(), 20);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

	void verifyAmount() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject ee = Game.handler.object.get(i);
			if (ee == this)
				break;
			if (ee.getId() == ID.Tree) {
				if (ee.getBounds().intersects(getBounds())) {
					Game.handler.object.remove(ee);
				}
			}
		}
	}

	void verifyDepth() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject ee = Game.handler.object.get(i);
			if (ee.getId() == ID.Tree) {
				if (this.getBounds().getY() > ee.getBounds().getY()) {
					this.setDepth(ee.getDepth() + 2);
				} 
			}
		}
	}
}
