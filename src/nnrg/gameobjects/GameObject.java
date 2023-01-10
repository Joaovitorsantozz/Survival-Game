package nnrg.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import nnrg.gameobject.entitys.Enemy;
import nnrg.gameobjects.particles.Damageparticle;
import nnrg.main.Game;

public abstract class GameObject {
	protected int x, y,dir;
	protected int xr=6,yr=6;
	protected float velX = 0, velY = 0;
	protected ID id;
	private int depth;
	private int width, height;
	public BufferedImage spr;
	public double life,maxlife;
	public boolean attacked;
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}


	public abstract Rectangle getBounds();
	protected static Comparator<GameObject> nodeSorter = Comparator.comparingInt(n0 -> n0.depth);

	public double calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	public void DrawBounds(Graphics2D g2){
		g2.setColor(Color.red);
		g2.draw(getBounds());
	}
	public void drawDefaultTex(Graphics g, BufferedImage sprite){
		g.drawImage(sprite,getX(),getY(),getWidth(),getHeight(),null);
	}
	public void drawDefaultTexInv(Graphics g,BufferedImage sprite){
		g.drawImage(sprite,getX()+getWidth(),getY(),getWidth()*-1,getHeight(),null);
	}
	public boolean Move(){
		
		return velX!=0|velY!=0;
	}
	
	public void damage(int damage,GameObject damaged,Rectangle attackbound) {
		if(attackbound.intersects(damaged.getBounds())) {
			damaged.life-=damage;
			Game.handler.object.add(new Damageparticle(damaged.getX(),damaged.getY(), ID.Particle, "-" + damage));
			damaged.attacked = true;
		}
	}
	
	public void tick() {
		
	}
	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	public void setId(ID id) { this.id = id; }
	public int getDepth() { return depth; }
	public void setDepth(int depth) { this.depth = depth; }
	public int getX() { return this.x; }
	public void setX(int nx) { this.x = nx; }
	public int getY() { return  this.y; }
	public void setY(int ny) { this.y = ny; }
	public float getVelX() { return velX; }
	public void setVelX(float velX) { this.velX = velX; }
	public float getVelY() { return velY; }
	public void setVelY(float velY) { this.velY = velY; }
	public ID getId() { return id; }
	public int getDir() { return dir; }
	public void setDir(int dir) { this.dir = dir; }
	public double getLife() {return life;}
	public void setLife(double life) {this.life = life;}
	public double getMaxlife() {return maxlife;}
	public void setMaxlife(double maxlife) {this.maxlife = maxlife;}
	public BufferedImage getSpr(){return this.spr;}
}
