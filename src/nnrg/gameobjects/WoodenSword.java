package nnrg.gameobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import nnrg.gameobject.entitys.Player;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.LoadImage;
import nnrg.world.Depth;

public class WoodenSword extends GameObject implements Tickable, Renderable {

	private GameObject owner;
	private BufferedImage img;
	private double angle = 0, angle2;
	private boolean animation;

	public WoodenSword(int x, int y, ID id, GameObject owner) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.owner = owner;
		img = new LoadImage("/weapons/woodsword.png").getImage();
		setDepth(Depth.HIGHT);
		setWidth(20);
		setHeight(36);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(Math.toRadians(angle),getX()+getWidth()/2,getY()+getHeight());

		g.drawImage(img, getX(),getY(), getWidth(), getHeight(), null);

		g2.rotate(-Math.toRadians(angle),getX()+getWidth()/2,getY()+getHeight());
		
		
	}

	@Override
	public void Update() {
		if (((Player) owner).input.attack.down) {
			if (((Player) owner).stamina > 0)
				animation = true;
		}
		if (animation) {
			if(owner.getDir()==1) {
				angle+=40;
				if(angle>=360) {
					angle=0;
					animation=false;
				}
			}else if(getDir()!=1) {
				angle-=40;
				if(angle<=-360) {
					angle=0;
					animation=false;
				}
			}
		}

		if (owner.getDir() != 1)
			setX((int)((owner.getX()-12)+owner.getVelX()));
		else
			setX((int)((owner.getX() +36)+owner.getVelY()));

		setY((int)((owner.getY()+5)+owner.getVelY()));
		
	}

	



	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


}
