package nnrg.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import nnrg.gameobjects.GameObject;
import nnrg.gameobjects.ID;
import nnrg.interfaces.Renderable;

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
	}



	public TileType getTileType() {
		return t;
	}

	public void setTileType(TileType tile) {
		t = tile;
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(spr,getX(),getY(),getWidth(),getHeight(),null);
	}

	@Override
	public Rectangle getBounds() {return new Rectangle(getX(),getY(),getWidth(),getHeight()); }
}
