package nnrg.gameobjects.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import nnrg.gameobjects.ID;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.Game;
import nnrg.main.others.FontStyle;
import nnrg.main.others.FontX;
import nnrg.world.Depth;

public class Damageparticle extends Particle implements Renderable,Tickable{
	private int life,maxLife=20;
	public double xx,yy,zz;
	public double xa,ya,za;
	private String text;
	public FontX font=new FontX();
	public Damageparticle(int x, int y, ID id,String text) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		xx=x;
		yy=y;
		zz=8;
		xa=new Random().nextGaussian()*1.02;
		ya=new Random().nextGaussian()*0.1;
		za=new Random().nextFloat()+1;
		this.text=text;
		setDepth(Depth.HIGHT+1);
		                       			
	}

	@Override
	public void render(Graphics g) {
		
		// TODO Auto-generated method stub
	
	
		font.draw(text, x+8,y-(int)zz,g,15,11);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		life++;
		if(life>30)Game.handler.DeleteObject(this);
		xx+=xa;
		yy+=ya;
		zz+=za;
		if(zz<-60) {
			zz=-60;
			za*=-0.6;
			xa*=0.6;
			ya*=0.6;
		}
		za-=0.2;
		x=(int)xx;
		y=(int)yy;
		
		
	
	}
	
}
