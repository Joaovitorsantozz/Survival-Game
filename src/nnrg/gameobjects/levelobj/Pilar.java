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

public class Pilar extends GameObject implements Renderable,Tickable{
	private BufferedImage spr=new LoadImage("/pilar.png").getImage();
	public Pilar(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		setDepth(Depth.LITTLE);
		setWidth(96);
		setHeight(96+32);
		verify();
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		setDepth();
	}
	public void verify() {
		for(int i=0;i<Game.handler.object.size();i++) {
			GameObject ee=Game.handler.object.get(i);
			if(ee!=this)
				break;
			if(ee.getX()==getX()&&ee.getY()==getY()) {
				Game.handler.object.remove(ee);
			}
		}
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		drawDefaultTex(g,spr);
		Graphics2D g2=(Graphics2D)g;
	
	}
	public void setDepth() {
		for(int i=0;i<Game.handler.object.size();i++) {
			GameObject ee=Game.handler.object.get(i);
			if(ee.getId()==ID.Player) {
				if(ee.getY()<getY()+56) {
					setDepth(ee.getDepth()+1);
				}else if(ee.getY()>getY()) {
					setDepth(Depth.MEDIUM);
					ee.setDepth(Depth.HIGHT);
				}
				
			}else if(ee.getId()==ID.Enemy) {
				if(ee.getY()<getY()+56) {
					setDepth(ee.getDepth()+1);
				}else if(ee.getY()>getY()) {
					setDepth(Depth.MEDIUM);
					ee.setDepth(Depth.HIGHT);
				}
				
			}
		}

	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX()+44,getY()+getHeight()-30,5,10);
	}

}
