package nnrg.gameobjects.levelobj;

import java.awt.Color;
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
		verifyDepth();
		verifyAmount();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		drawDefaultTex(g, spr);
		Graphics2D g2=(Graphics2D)g;
		g.setColor(Color.red);
	
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX()+getWidth()/2-30, getY()+getHeight()/2+30,60,60);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
 

	}

	void verifyAmount() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject ee = Game.handler.object.get(i);
			if (ee.getId() == ID.Tree) {
				if(ee==this) {
					break;
				}
				if (ee.getBounds().intersects(getBounds())) {
					System.out.println("deletei");
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
