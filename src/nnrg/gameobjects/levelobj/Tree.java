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

public class Tree extends GameObject implements Renderable, Tickable {
	private BufferedImage spr = new LoadImage("/tree.png").getImage();

	public Tree(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		setWidth(spr.getWidth()*3);
		setHeight(spr.getHeight()*3);
		setDepth(Depth.HIGHT);
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		drawDefaultTex(g, spr);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX()+getWidth()/2-10, getY()+getHeight()-35,20,10);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		verifyDepth();
		
	}

	void verifyAmount() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject ee = Game.handler.object.get(i);
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
