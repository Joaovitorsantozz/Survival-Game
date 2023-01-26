package nnrg.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import nnrg.gameobjects.GameObject;
import nnrg.gameobjects.ID;
import nnrg.interfaces.Renderable;
import nnrg.main.Game;

public class Tile extends GameObject implements Renderable {
	private BufferedImage spr;
	private TileType t;
	

	public Tile(int x, int y, ID id, TileType tt) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		t = tt;
		setDepth(Depth.LITTLE);
		spr=t.setImage(spr,this);
		setWidth(32);
		setHeight(32);
		verifyAmount();
	}



	public TileType getTileType() {
		return t;
	}

	public void setTileType(TileType tile) {
		t = tile;
	}

	void verifyAmount() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject ee = Game.handler.object.get(i);
			if (ee.getId()==ID.Floor) {
				if(ee==this) {
					break;
				}
				if (ee.getBounds().intersects(getBounds())) {
					
					Game.handler.object.remove(ee);
				}
			}
		}
	}
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(spr,getX(),getY(),getWidth(),getHeight(),null);
		Graphics2D g2=(Graphics2D)g;
	
		
	}
	

	@Override
	public Rectangle getBounds() {return new Rectangle(getX(),getY(),getWidth(),getHeight()); }
}
